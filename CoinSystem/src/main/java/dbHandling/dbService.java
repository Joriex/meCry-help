package dbHandling;

import fileHandling.File;
import mecry.joriex.coinsystem.CoinSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbService {

    private Connection con;
    private String host;
    private int port;
    private String db;
    private String user;
    private String password;
    private String url;
    private File config = CoinSystem.getDbConfig();

    public dbService() {
        try {
            this.host = config.getConfig("host");
            this.port = Integer.parseInt(config.getConfig("port"));
            this.db = config.getConfig("db");
            this.user = config.getConfig("username");
            this.password = config.getConfig("password");
            this.url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?autoReconnect=true";
        }catch (NullPointerException e){
            throw new RuntimeException(e);
        }
        connect();
    }

    public void connect(){
        try{
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void disconnect(){
        try{
            this.con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void reloadParameter(){
        try {
            this.host = config.getConfig("host");
            this.port = Integer.parseInt(config.getConfig("port"));
            this.db = config.getConfig("db");
            this.user = config.getConfig("username");
            this.password = config.getConfig("password");
            this.url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?autoReconnect=true";
        }catch (NullPointerException e){
            throw new RuntimeException(e);
        }
    }

    public Connection getCon() {
        return con;
    }
}
