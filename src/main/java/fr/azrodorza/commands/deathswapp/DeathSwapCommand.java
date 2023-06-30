package fr.azrodorza.commands.deathswapp;

import fr.azrodorza.commands.CommandHelper;
import fr.azrodorza.commands.CommandMapper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import static fr.azrodorza.game.Deathswap.start;
import static fr.azrodorza.game.Deathswap.stop;

public class DeathSwapCommand implements CommandHelper {

    @CommandMapper(cmd = "death", sub = "help")
    @Override
    public void helpCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Death swapp command :\n" +
                "- death start : start a death swap" +
                "- death stop : stop the death swap");
    }
    @CommandMapper(cmd = "death", sub = "start")
    public void deathSwapStart(CommandSender sender, Command command, String label, String[] args) {
        start();
    }

    @CommandMapper(cmd = "death", sub = "stop")
    public void deathSwapStop(CommandSender sender, Command command, String label, String[] args) {
        stop();
    }
}
