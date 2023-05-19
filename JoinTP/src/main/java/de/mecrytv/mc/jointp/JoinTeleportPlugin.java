package de.mecrytv.mc.jointp;

import de.mecrytv.mc.jointp.commands.setSpawn;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class JoinTeleportPlugin extends JavaPlugin implements Listener {

    private Location spawnLocation;
    private JoinTeleportPlugin plugin;

    private File spawnLocationConfig;

    @Override
    public void onEnable() {
        this.plugin = this;

        // Plugin startup logic
        plugin = this;
        if(!getDataFolder().exists()){
            getDataFolder().mkdirs();
        }

        spawnLocationConfig = new File();
        spawnLocationConfig.createFile(this.getDataFolder().toPath(),  "spawnLocation.yaml");
        getCommand("setSpawn").setExecutor(new setSpawn(spawnLocationConfig));
    }

    private void loadSpawnLocation() {
        // Lädt die Spawn-Position aus der Konfiguration
        if (getConfig().contains("spawn.world") && getConfig().contains("spawn.x")
                && getConfig().contains("spawn.y") && getConfig().contains("spawn.z")) {

            String worldName = getConfig().getString("spawn.world");
            double x = Double.parseDouble(spawnLocationConfig.getConfig("spawn.x"));
            double y = Double.parseDouble(spawnLocationConfig.getConfig("spawn.y"));
            double z = Double.parseDouble(spawnLocationConfig.getConfig("spawn.z"));

            double yawDouble = Double.parseDouble(spawnLocationConfig.getConfig("spawn.yaw"));
            float yaw = (float) yawDouble;
            double pitchDouble = Double.parseDouble(spawnLocationConfig.getConfig("spawn.pitch"));
            float pitch = (float) pitchDouble;

            assert worldName != null;
            spawnLocation = new Location(getServer().getWorld(worldName), x, y, z, yaw, pitch);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        loadSpawnLocation();
        Player player = event.getPlayer();
        player.teleport(spawnLocation);
    }
}
