package fr.azrodorza.game;

import fr.azrodorza.FunWorld;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class Vampire {
    private static final Map<Player, BukkitTask> vampires = new HashMap<>();

    public static boolean isVampire(Player player) {
        return vampires.containsKey(player);
    }

    public static void setPlayerVampire(Player player) {
        if (!isVampire(player)) {
            BukkitTask bukkitTask = Bukkit.getScheduler().runTaskTimerAsynchronously(FunWorld.instance, () -> {
                if (isSunAbovePlayer(player)) {
                    player.setFireTicks(20);
                } else {
                    player.setFireTicks(0);
                }
            }, 0, 10);
            vampires.put(player, bukkitTask);
        }
    }

    public static void removePlayerFromVampire(Player player) {
        if (isVampire(player)) {
            vampires.get(player).cancel();
            vampires.remove(player);
            player.setFireTicks(0);
        }
    }

    public static void transformPlayerIntoBat(Player player) {
        if (DisguiseAPI.isDisguised(player)) {
            player.setAllowFlight(false);
            player.setFlying(false);
            DisguiseAPI.undisguiseToAll(player);
        } else {
            player.setAllowFlight(true);
            player.setFlying(true);
            DisguiseAPI.disguiseToAll(player, new MobDisguise(DisguiseType.BAT));
        }
    }

    private static boolean isSunAbovePlayer(Player player) {
        int playerY = player.getLocation().getBlockY();
        long time = player.getWorld().getTime();

        boolean isDaytime = time >= 0 && time < 12300; // Assuming the day starts at 0 and ends at 12300

        return isDaytime && playerY >= player.getWorld().getHighestBlockYAt(player.getLocation()) && !player.getWorld().hasStorm();
    }
}
