package parse.command;

public class Join extends CommandType{
    String tableName1;
    String tableName2;
    String columnName1;
    String columnName2;
    public void setTableName1(String name){
        tableName1 = name;
    }
    public void setTableName2(String name){
        tableName2 = name;
    }
    public void setColumnName1(String name){
        columnName1 = name;
    }
    public void setColumnName2(String name){
        columnName2 = name;
    }
    public String getTableName1(){
        return tableName1;
    }
    public String getTableName2(){
        return tableName2;
    }
    public String getColumnName1(){
        return columnName1;
    }
    public String getColumnName2(){
        return columnName2;
    }
}
