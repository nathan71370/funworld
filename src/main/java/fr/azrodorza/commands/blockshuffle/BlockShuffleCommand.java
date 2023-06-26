package fr.azrodorza.commands.blockshuffle;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static fr.azrodorza.game.BlockShuffle.start;
import static fr.azrodorza.game.BlockShuffle.stop;

public class BlockShuffleCommand implements CommandExecutor {

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
