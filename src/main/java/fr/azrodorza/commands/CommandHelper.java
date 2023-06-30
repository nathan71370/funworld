package fr.azrodorza.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface CommandHelper {
    void helpCommand(CommandSender sender, Command command, String label, String[] args);
}
