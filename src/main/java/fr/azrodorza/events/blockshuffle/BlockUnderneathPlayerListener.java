package fr.azrodorza.events.blockshuffle;

import fr.azrodorza.game.BlockShuffle;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import static fr.azrodorza.game.BlockShuffle.*;
import static org.bukkit.Bukkit.broadcastMessage;

public class BlockUnderneathPlayerListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if(!BlockShuffle.isGameRunning())
            return;
        Player player = event.getPlayer();
        if (getBlockMaterial(player).equals(getItem(player))) {
            broadcastMessage("§C§L" + player.getDisplayName() + " a trouvé son bloc!");
            removeItem(player);
        }
        verify();
    }


    private Material getBlockMaterial(Player player) {
        Location loc = player.getLocation();
        loc.setY(loc.getY() - 1);
        return loc.getBlock().getType();
    }
}
