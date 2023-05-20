package de.mecrytv.mc.jointp;

import commands.setSpawn;
import eventHandler.onPlayerJoin;
import fileHandling.File;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class JoinTP extends JavaPlugin implements Listener {

    private static JoinTP plugin;

    private static File spawnLocationConfig;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        if(!getDataFolder().exists()){
            getDataFolder().mkdirs();
        }

        spawnLocationConfig = new File();
        spawnLocationConfig.getFile(this.getDataFolder().toPath(),  "spawnLocation.yaml");

        getCommand("setSpawn").setExecutor(new setSpawn());

        Bukkit.getPluginManager().registerEvents(new onPlayerJoin(), this);
    }

    public static JoinTP getPlugin() {
        return plugin;
    }

    public static File getSpawnLocationConfig() {
        return spawnLocationConfig;
    }
}
