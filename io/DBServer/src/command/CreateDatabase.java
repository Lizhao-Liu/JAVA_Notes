package command;

import dbStructure.DatabaseManager;
import exception.CommandExecutionException;

public class CreateDatabase extends CommandType{
    private String DbName;
    public void setDbName(String name){
        this.DbName=name;
    }
    @Override
    public void execute(DatabaseManager manager) throws CommandExecutionException {
        manager.addDatabase(DbName);
        System.out.println("[OK]");
    }
}
