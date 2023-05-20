package fileHandling;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class File {
    private java.io.File file;
    private  FileConfiguration config;
    //creates the yaml file
    public void getFile(Path path, String fileName){
        this.file = new java.io.File(path.toFile(), fileName);
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
       config = YamlConfiguration.loadConfiguration(file);
    }
    //function to save the yaml file
    public void saveFile(){
        try{
            config.save(file);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    // set new key-value-pair in the yaml file
    public void setConfig(String key , Object value){
        config.set(key, value);
    }
    //get a value from a specific key
    public String getConfig (String key){
        return config.getString(key);
    }
    // delete yaml file
    public void delFile(){
        file.delete();
    }
    //rename a yaml file
    public void renameFile(Path source, Path newSource) throws IOException {
        Files.move(source, newSource);
    }
}
