package dbStructure;

import exception.CommandExecutionException;

import java.io.File;
import java.util.*;

public class Database {
    private static final String tableExtension = ".tb";
    private static final String rootDir = "./database/";
    private static DatabaseManager manager;
    private String dbName;
    private String dbPath;
    private ArrayList<Table> tables;
    private Map<String, Integer> tableMap;

    public Database(String name)
    {
        this.dbName = name;
        this.dbPath = rootDir + dbName + '/';
        File dbDir = new File(this.dbPath);
        if (!dbDir.exists()) {
            dbDir.mkdir();
        }
        tableMap = new HashMap<>();
        tables = new ArrayList<>();
    }


    public void setManager(DatabaseManager manager){
        this.manager = manager;
    }

    public boolean containsTable(String tableName){
        if(!tableMap.containsKey(tableName)) return false;
        return true;
    }

    public Table addTable(String name) throws CommandExecutionException
    {
        if(containsTable(name)) throw new CommandExecutionException("table " + name + " already exists");
        Table tb = new Table(name);
        tb.setDatabase(this);
        tb.setPath(dbPath + name + tableExtension);
        tb.create();
        tableMap.put(name, tables.size());
        tables.add(tb);
        return tb;
    }

    public Table getTable(String tableName) throws CommandExecutionException
    {
        if(!tableMap.containsKey(tableName)) throw new CommandExecutionException("Table "+ tableName +" doesn't exist");
        return tables.get(tableMap.get(tableName));
    }

    public void dropTable(String tableName) throws CommandExecutionException
    {
        File target = new File(dbPath+tableName+".tb");
        if(target.exists()){
            if(!target.delete()) throw new CommandExecutionException("Fail to delete Table "+ tableName+"due to i/o issues");
        }
        if(!containsTable(tableName)) throw new CommandExecutionException("Table " + tableName +" doesn't exist");
        tables.remove(tables.get(tableMap.get(tableName)));
        tableMap.remove(tableName);
    }
    public String toString(){
        return dbName;
    }
    public String getDbName(){
        return dbName;
    }


}
