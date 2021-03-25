package command;

import dbStructure.DatabaseManager;
import exception.CommandExecutionException;

abstract public class CommandType {
    String output;
    public void execute(DatabaseManager manager) throws CommandExecutionException {};
    public String getOutput(){
        output = new String();
        return output;
    }
}
