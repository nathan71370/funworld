package fr.azrodorza.commands.blockshuffle;

import fr.azrodorza.commands.CommandHelper;
import fr.azrodorza.commands.CommandMapper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import static fr.azrodorza.game.BlockShuffle.start;
import static fr.azrodorza.game.BlockShuffle.stop;

public class BlockShuffleCommand implements CommandHelper {

    @CommandMapper(cmd = "block", sub = "help")
    @Override
    public void helpCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Block shuffle command :\n" +
                "- block start : start a block shuffle" +
                "- block stop : stop the block shuffle");
    }

    @CommandMapper(cmd = "block", sub = "start")
    public void blockShuffleStart(CommandSender sender, Command command, String label, String[] args) {
        start();
    }

    @CommandMapper(cmd = "block", sub = "stop")
    public void blockShuffleStop(CommandSender sender, Command command, String label, String[] args) {
        stop();
    }
}
