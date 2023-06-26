package fr.azrodorza.commands.deathswapp;

import fr.azrodorza.game.Deathswap;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static fr.azrodorza.game.Deathswap.start;
import static fr.azrodorza.game.Deathswap.stop;

public class DeathSwapCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        if (args.length != 0 && args[0].equals("start")) {
            start();
        }

        if (args.length != 0 && args[0].equals("stop")) {
            stop();
        }
        return true;
    }
}
