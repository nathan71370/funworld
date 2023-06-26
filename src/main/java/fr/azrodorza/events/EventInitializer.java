package fr.azrodorza.events;

import fr.azrodorza.events.blockshuffle.BlockUnderneathPlayerListener;
import fr.azrodorza.events.deathswapp.DeathListener;
import fr.azrodorza.events.itempick.ItemPickupListener;
import org.bukkit.event.Listener;

import static fr.azrodorza.FunWorld.instance;
import static org.bukkit.Bukkit.getServer;

public class EventInitializer {

    public static void initEvents() {
        register(new DeathListener());
        register(new ItemPickupListener());
        register(new BlockUnderneathPlayerListener());
    }

    private static void register(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, instance);
    }
}
