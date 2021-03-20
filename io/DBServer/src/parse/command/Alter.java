package parse.command;

import reader.Table;

public class Alter extends CommandType {
    private Table table;
    private AlterationType type;
    private String columnName;
    private String tableName;

    public Alter(){
        identifier = "ALTER";
    }
    public enum AlterationType {
        ADD, DROP;
    }
    public void setTableName(String tableName){
        //if table name exist
        this.tableName = tableName;
    }
    public void setType(AlterationType type){
        this.type = type;
    }
    public void setColumnName(String columnName){
        this.columnName = columnName;
    }

}
