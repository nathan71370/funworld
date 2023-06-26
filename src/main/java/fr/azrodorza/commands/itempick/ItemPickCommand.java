package fr.azrodorza.commands.itempick;

import fr.azrodorza.game.ItemPick;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static fr.azrodorza.game.ItemPick.start;
import static fr.azrodorza.game.ItemPick.stop;

public class ItemPickCommand implements CommandExecutor {
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
