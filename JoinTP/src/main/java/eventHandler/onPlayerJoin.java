package eventHandler;

import de.mecrytv.mc.jointp.JoinTP;
import fileHandling.File;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onPlayerJoin implements Listener {
    private final File spawnLocationConfig;
    public onPlayerJoin(){
        this.spawnLocationConfig = JoinTP.getSpawnLocationConfig();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws Exception {
        Player player = event.getPlayer();
        player.teleport(loadSpawnLocation(player));
    }

    private Location loadSpawnLocation(Player player) throws Exception {

            // Lädt die Spawn-Position aus der Konfiguration
            String worldName = spawnLocationConfig.getConfig("spawn.world");
            double x = Double.parseDouble(spawnLocationConfig.getConfig("spawn.x"));
            double y = Double.parseDouble(spawnLocationConfig.getConfig("spawn.y"));
            double z = Double.parseDouble(spawnLocationConfig.getConfig("spawn.z"));

            double yawDouble = Double.parseDouble(spawnLocationConfig.getConfig("spawn.yaw"));
            float yaw = (float) yawDouble;
            double pitchDouble = Double.parseDouble(spawnLocationConfig.getConfig("spawn.pitch"));
            float pitch = (float) pitchDouble;

            assert worldName != null;
            return new Location(JoinTP.getPlugin().getServer().getWorld(worldName), x, y, z, yaw, pitch);

    }
}
