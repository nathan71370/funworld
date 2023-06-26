package fr.azrodorza.game;

import fr.azrodorza.FunWorld;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.bukkit.Bukkit.broadcastMessage;

public class Deathswap {
    private static BukkitTask bukkit_task;
    private static final Map<Player, DamageCause> player_death = new HashMap<>();
    private static boolean gameEnded = true;
    private static final long TIME_IN_MINUTES = 5;

    public static void start() {
        if (bukkit_task != null)
            bukkit_task.cancel();
        broadcastMessage("§A§LEt c'est partiiiiiiit pour un death swap!");
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
        DamageCause[] damageCauses = DamageCause.values();
        for (Player player : Bukkit.getOnlinePlayers()) {
            DamageCause randomCause = damageCauses[random.nextInt(damageCauses.length)];

            player_death.put(player, randomCause);
            player.sendMessage("§C" + "Tu dois mourir de cette manière (démerde toi pour traduire) : §L" + randomCause.name().replace("_", " ").toLowerCase() + "§r§C en moins de 5 minutes !");
        }

        bukkit_task = Bukkit.getScheduler().runTaskLaterAsynchronously(FunWorld.instance, Deathswap::showLooser, TIME_IN_MINUTES * 60 * 20);
    }

    private static void showLooser() {
        for (Player player : player_death.keySet()) {
            broadcastMessage("§C§L" + player.getDisplayName() + " : LOOSER oh le nullos bouuuuh t'as perdu grosse merde");
            gameEnded = true;
        }
    }

    public static void verify() {
        if (player_death.size() == 0
                && isGameRunning()) {
            generate();
        }
    }

    public static boolean isGameRunning() {
        return !gameEnded;
    }

    public static DamageCause getDamageCause(Player player) {
        return player_death.get(player);
    }

    public static void removeCause(Player player) {
        player_death.remove(player);
    }
}
