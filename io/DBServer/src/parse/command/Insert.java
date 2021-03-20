package parse.command;


import parse.command.common.Value;

import java.util.ArrayList;

public class Insert extends CommandType{
    String tableName;
    ArrayList<Value> valueList;

    public void setTableName(String name){
        tableName = name;
    }
    public void setValueList(ArrayList<Value> list){
        valueList = list;
    }


}
