package fr.azrodorza.commands;

import fr.azrodorza.commands.blockshuffle.BlockShuffleCommand;
import fr.azrodorza.commands.deathswapp.DeathSwapCommand;
import fr.azrodorza.commands.itempick.ItemPickCommand;
import fr.azrodorza.commands.vampire.VampireCommand;
import org.bukkit.command.CommandExecutor;

import static fr.azrodorza.FunWorld.instance;

public class CommandInitializer {
    public static void initCommands() {
        register("death", new DeathSwapCommand());
        register("item", new ItemPickCommand());
        register("block", new BlockShuffleCommand());
        register("vampire", new VampireCommand());
    }

    private static void register(String commandName, CommandExecutor commandExecutor) {
        instance.getCommand(commandName).setExecutor(commandExecutor);
    }
}
