package parse.command;

public class Use extends CommandType{
    String identifier;
    String dbName;
    public void setDbName(String name){
        this.dbName = name;
    }
}
