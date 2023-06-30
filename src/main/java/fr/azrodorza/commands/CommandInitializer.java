package fr.azrodorza.commands;

import org.apache.commons.lang3.tuple.Triple;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static fr.azrodorza.FunWorld.instance;

public class CommandInitializer {
    public static void initCommands() throws RuntimeException {
        Map<String, Map<String, Triple<String, Boolean, Method>>> commands = new HashMap<>();
        Reflections reflections = new Reflections(instance, new MethodAnnotationsScanner());
        Set<Method> annotatedMethods = reflections.getMethodsAnnotatedWith(CommandMapper.class);
        populateCommandWithAnnotations(commands, annotatedMethods);
        registerCommands(commands);
    }

    private static void populateCommandWithAnnotations(Map<String, Map<String, Triple<String, Boolean, Method>>> commands, Set<Method> annotatedMethods) {
        annotatedMethods.forEach(method -> {
            String cmd = method.getAnnotation(CommandMapper.class).cmd();
            String sub = method.getAnnotation(CommandMapper.class).sub();
            String permission = method.getAnnotation(CommandMapper.class).permission();
            Boolean canBeExecutedByConsole = method.getAnnotation(CommandMapper.class).canBeExecutedByConsole();
            if (commands.containsKey(cmd)) {
                Map<String, Triple<String, Boolean, Method>> subToPair = commands.get(cmd);
                subToPair.put(sub, Triple.of(permission, canBeExecutedByConsole, method));
            } else {
                Map<String, Triple<String, Boolean, Method>> subToPair = new HashMap<>();
                subToPair.put(sub, Triple.of(permission, canBeExecutedByConsole, method));
                commands.put(cmd, subToPair);
            }
        });
    }

    private static void registerCommands(Map<String, Map<String, Triple<String, Boolean, Method>>> commands) {
        commands.forEach((cmd, subPair) -> {
            instance.getCommand(cmd).setTabCompleter((sender, command, label, args) -> args.length == 1
                    ? subPair.keySet().stream().toList()
                    : Bukkit.getOnlinePlayers().stream().map(Player::getName).toList());

            instance.getCommand(cmd).setExecutor((sender, command, label, args) -> {
                String subCommand = args.length == 0 ? "help" : args[0];
                Triple<String, Boolean, Method> method = subPair.get(subCommand);
                if (method != null) {
                    try {
                        Object instance = method.getRight().getDeclaringClass().getDeclaredConstructor().newInstance();
                        if (method.getLeft().isEmpty() || sender.hasPermission(method.getLeft())) {
                            if (method.getMiddle().equals(true) || (sender instanceof Player)) {
                                method.getRight().invoke(instance, sender, command, label, args);
                            }
                        }
                    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                             InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    sender.sendMessage("Command not found");
                }
                return true;
            });
        });
    }
}
