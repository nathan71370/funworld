package fr.azrodorza.events.deathswapp;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import static fr.azrodorza.game.Deathswap.*;
import static org.bukkit.Bukkit.broadcastMessage;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(EntityDamageEvent event) {
        if(!isGameRunning())
            return;
        if (event.getEntity() instanceof Player player
                && player.getHealth() <= event.getFinalDamage()
                && getDamageCause(player).equals(event.getCause())) {
            broadcastMessage("§C§L" + player.getDisplayName() + " a trouvé sa mort!");
            removeCause(player);
        }
        verify();
    }
}
