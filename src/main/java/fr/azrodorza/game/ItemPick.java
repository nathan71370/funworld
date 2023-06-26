package fr.azrodorza.game;

import fr.azrodorza.FunWorld;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.bukkit.Bukkit.broadcastMessage;

public class ItemPick {

    private static BukkitTask bukkit_task;
    private static final Map<Player, Material> player_item = new HashMap<>();
    private static boolean gameEnded = true;
    private static final long TIME_IN_MINUTES = 5;

    public static void start() {
        if (bukkit_task != null)
            bukkit_task.cancel();
        broadcastMessage("§A§LEt c'est partiiiiiiit pour un item pickup!");
        gameEnded = false;
        generate();
    }

    public static void stop() {
        if (bukkit_task != null)
            bukkit_task.cancel();

        broadcastMessage("§C§LFIN DU FUN.");
    }

    private static void generate() {
        Random random = new Random();
        Material[] materials = Material.values();
        for (Player player : Bukkit.getOnlinePlayers()) {
            Material material = materials[random.nextInt(materials.length)];
            while(!material.isItem()) {
                material = materials[random.nextInt(materials.length)];
            }

            player_item.put(player, material);
            player.sendMessage("§C" + "Tu dois obtenir : §L" + material.name().replace("_", " ").toLowerCase() + "§r§C en moins de 5 minutes !");
        }

        bukkit_task = Bukkit.getScheduler().runTaskLaterAsynchronously(FunWorld.instance, ItemPick::showLooser, TIME_IN_MINUTES * 60 * 20);
    }

    private static void showLooser() {
        for (Player player : player_item.keySet()) {
            broadcastMessage("§C§L" + player.getDisplayName() + " : LOOSER oh le nullos bouuuuh t'as perdu grosse merde");
            gameEnded = true;
        }
    }

    public static void verify() {
        if (player_item.size() == 0
                && isGameRunning()) {
            generate();
        }
    }

    public static boolean isGameRunning() {
        return !gameEnded;
    }

    public static Material getItem(Player player) {
        return player_item.get(player);
    }

    public static void removeItem(Player player) {
        player_item.remove(player);
    }
}
