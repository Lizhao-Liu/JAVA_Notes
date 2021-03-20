package parse.command;

public class Drop extends CommandType{

    private StructureType type;
    private String name;
    public Drop(){}
    public void setType(StructureType type){
        this.type = type;
    }
    public void setName(String name){
        this.name = name;
    }
    public enum StructureType{table, database}

}
