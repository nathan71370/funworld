package fr.azrodorza.commands.itempick;

import fr.azrodorza.commands.CommandHelper;
import fr.azrodorza.commands.CommandMapper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import static fr.azrodorza.game.ItemPick.start;
import static fr.azrodorza.game.ItemPick.stop;

public class ItemPickCommand implements CommandHelper {

    @CommandMapper(cmd = "item", sub = "help")
    @Override
    public void helpCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Item shuffle command :\n" +
                "- item start : start an item shuffle" +
                "- item stop : stop the item shuffle");
    }

    @CommandMapper(cmd = "item", sub = "start")
    public void itemPickStart(CommandSender sender, Command command, String label, String[] args) {
        start();
    }

    @CommandMapper(cmd = "item", sub = "stop")
    public void itemPickStop(CommandSender sender, Command command, String label, String[] args) {
        stop();
    }
}