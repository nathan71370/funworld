package fr.azrodorza.events.itempick;

import fr.azrodorza.game.ItemPick;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import static fr.azrodorza.game.ItemPick.*;
import static org.bukkit.Bukkit.broadcastMessage;

public class ItemPickupListener implements Listener {

    @EventHandler
    public void onPlayerPickupItem(EntityPickupItemEvent event) {
        if(!isGameRunning())
            return;
        if (event.getEntity() instanceof Player player
                && event.getItem().getItemStack().getType().equals(getItem(player))) {
            broadcastMessage("§C§L" + player.getDisplayName() + " a trouvé son item!");
            removeItem(player);
        }
        verify();
    }
}
