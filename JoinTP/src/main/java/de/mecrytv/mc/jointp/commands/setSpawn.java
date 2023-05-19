package de.mecrytv.mc.jointp.commands;

import de.mecrytv.mc.jointp.File;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setSpawn implements CommandExecutor {

    private File config;
    public setSpawn(File spawnConfiguration){
        this.config = spawnConfiguration;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (command.getName().equalsIgnoreCase("setspawn")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                Location currentLocation = player.getLocation();
                setSpawnLocation(currentLocation);
                player.sendMessage("Spawn erfolgreich gesetzt!");
            } else {
                sender.sendMessage("Dieser Befehl kann nur von einem Spieler verwendet werden.");
            }
            return true;
        }

        return false;
    }

    private void setSpawnLocation(Location location) {
        config.setConfig("spawn.world", location.getWorld().getName());
        config.setConfig("spawn.x", location.getX());
        config.setConfig("spawn.y", location.getY());
        config.setConfig("spawn.z", location.getZ());
        config.setConfig("spawn.yaw", location.getYaw());
        config.setConfig("spawn.pitch", location.getPitch());
        config.saveFile();
    }
}
