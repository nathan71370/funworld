package fr.azrodorza;

import org.bukkit.plugin.java.JavaPlugin;

import static fr.azrodorza.initializer.Registerer.registerAll;

public class FunWorld extends JavaPlugin {
    public static FunWorld instance;
    @Override
    public void onEnable() {
        instance = this;
        registerAll();
        getLogger().info("FunWorld me enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("FunWorld me disabled!");
    }
}