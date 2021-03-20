package parse.command;

import parse.command.Where.Condition;

import java.util.ArrayList;

public class Select extends CommandType{
    boolean isSelectAllCols;
    boolean hasWhere;
    ArrayList<String> columnList;
    String tableName;
    //Condition condition;
    Condition condition;
    public Select(){
        isSelectAllCols=false;
        hasWhere=false;
    };

    public void setColumnList(ArrayList<String> columnList) {
        this.columnList = columnList;
    }

    public ArrayList<String> getColumnList() {
        return columnList;
    }

    public Condition getCondition() {
        return condition;
    }
    public void setCondition(Condition condition){
        this.condition = condition;
    }

    //    public Condition getCondition() {
//        return condition;
//    }

    public boolean isHasCondition() {
        return hasWhere;
    }

    public boolean isSelectAllCols() {
        return isSelectAllCols;
    }

    public String getTableName() {
        return tableName;
    }

//    public void setCondition(Condition condition) {
//        this.condition = condition;
//    }


    public void setSelectAllCols() {
        isSelectAllCols = true;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
