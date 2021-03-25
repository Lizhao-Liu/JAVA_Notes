package dbStructure;

import exception.CommandExecutionException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class DatabaseManager {
    public static final String rootPath = "./database/";
    private Database currDB;
    private ArrayList<Database> databases;
    private Map<String, Integer> databaseMap;

    public DatabaseManager()
    {
        File rootDir = new File(rootPath);
        if (!rootDir.exists()) {
            rootDir.mkdir();
        }
        databases = new ArrayList<>();
        databaseMap = new HashMap<>();
    }

    public void useDB(String name) throws CommandExecutionException {
        if(!databaseMap.containsKey(name)) throw new CommandExecutionException("Database "+ name + " doesn't exist");
        this.currDB = databases.get(databaseMap.get(name));
    }

    public Database getCurrDB() throws CommandExecutionException {
        if(currDB==null){
            throw new CommandExecutionException("please choose the database first");
        }

        return currDB;
    }

    public boolean containsDB(String name){
        if(!databaseMap.containsKey(name)) return false;
        return true;
    }

    public void dropDatabase(String name) throws CommandExecutionException
    {
        String dbPath = rootPath+name+'/';
        File dir = new File(dbPath);
        if(dir.exists()){
            try {
                //delete the database folder recursively
                Path dirPath = Paths.get(dbPath);
                Files.walk(dirPath).map(Path::toFile)
                        .sorted(Comparator.comparing(File::isDirectory))
                        .forEach(File::delete);
            } catch(IOException e){
                throw new CommandExecutionException("Fail to delete the folder "+ name);
            }
        }
        if(!databaseMap.containsKey(name)) throw new CommandExecutionException("Database "+ name + " doesn't exist");
        databases.remove(databaseMap.get(name));
        databaseMap.remove(name);
        if(currDB!=null && currDB.getDbName().equals(name)){
            currDB=null;
        }
    }

    public void addDatabase(String name) throws CommandExecutionException
    {
        if(containsDB(name)) throw new CommandExecutionException("Database "+ name + "already exits");
        Database db = new Database(name);
        db.setManager(this);
        databaseMap.put(name, databases.size());
        databases.add(db);
    }

    public ArrayList<Database> getDatabases() {
        return databases;
    }

    public void setDatabases(ArrayList<Database> databases) {
        this.databases = databases;
    }
}
