package parse.command;

public class CreateDatabase extends CommandType{
    private String DbName;
    public void setDbName(String name){
        this.DbName=name;
    }
}
