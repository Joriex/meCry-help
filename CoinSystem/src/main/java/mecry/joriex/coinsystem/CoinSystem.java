package mecry.joriex.coinsystem;

import dbHandling.dbService;
import fileHandling.File;
import org.bukkit.plugin.java.JavaPlugin;

public final class CoinSystem extends JavaPlugin {
    private static CoinSystem plugin;
    private static File dbConfig;
    private static dbService db;
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        if(!getDataFolder().exists()){
            getDataFolder().mkdirs();
        }
        dbConfig = new File();
        dbConfig.getFile(this.getDataFolder().toPath(),  "dbConfig.yaml");

        db = new dbService();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        db.disconnect();
    }

    public static CoinSystem getPlugin() {
        return plugin;
    }

    public static File getDbConfig() {
        return dbConfig;
    }
}
