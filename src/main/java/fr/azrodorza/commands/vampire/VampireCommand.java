package fr.azrodorza.commands.vampire;

import fr.azrodorza.game.BlockShuffle;
import fr.azrodorza.game.Vampire;
import net.minecraft.world.entity.EntityTypes;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static fr.azrodorza.game.Vampire.*;
import static org.bukkit.Bukkit.broadcastMessage;

public class VampireCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        if (args.length == 2 && args[0].equals("add")) {
            Player player = Bukkit.getPlayer(args[1]);
            if (player != null) {
                setPlayerVampire(player);
                broadcastMessage("§C" + player.getDisplayName() + " est maintenant un vampire!");
            }
        }

        if (args.length == 2 && args[0].equals("remove")) {
            Player player = Bukkit.getPlayer(args[1]);
            if (player != null) {
                removePlayerFromVampire(player);
                broadcastMessage("§C" + player.getDisplayName() + " n'est plus un vampire!");
            }
        }

        if (args.length == 1 && args[0].equals("transform")) {
            Player player = (Player) sender;
            transformPlayerIntoBat(player);
            broadcastMessage("§C" + player.getDisplayName() + " n'est plus un vampire!");
        }
        return true;
    }
}
