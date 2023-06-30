package fr.azrodorza.commands.vampire;

import fr.azrodorza.commands.CommandHelper;
import fr.azrodorza.commands.CommandMapper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static fr.azrodorza.game.Vampire.*;
import static org.bukkit.Bukkit.broadcastMessage;

public class VampireCommand implements CommandHelper {

    @CommandMapper(cmd = "vampire", sub = "help")
    public void helpCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Vampire command :\n" +
                "- add <player> : transform a player into a Vampire" +
                "- remove <player> : put the given player back to a simple human" +
                "- transform : transform yourself into a bat");
    }

    @CommandMapper(cmd = "vampire", sub = "add")
    public void vampireAddCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 2) {
            return;
        }
        Player player = Bukkit.getPlayer(args[1]);
        if (player == null) {
            sender.sendMessage("Player not found!");
            return;
        }
        if (isVampire(player)) {
            sender.sendMessage("This player is already a Vampire!");
            return;
        }
        setPlayerVampire(player);
        broadcastMessage("§C" + player.getDisplayName() + " is now a Vampire!");
    }

    @CommandMapper(cmd = "vampire", sub = "remove")
    public void vampireRemoveCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 2) {
            return;
        }
        Player player = Bukkit.getPlayer(args[1]);
        if (player == null) {
            sender.sendMessage("Player not found!");
            return;
        }
        if (!isVampire(player)) {
            sender.sendMessage("This player is not a Vampire!");
            return;
        }
        removePlayerFromVampire(player);
        broadcastMessage("§C" + player.getDisplayName() + " is not a vampire anymore!");
    }

    @CommandMapper(cmd = "vampire", sub = "transform")
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (isVampire(player))
            transformPlayerIntoBat(player);
        else
            player.sendMessage("You are not a Vampire!");
        return true;
    }
}
