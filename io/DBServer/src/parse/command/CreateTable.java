package parse.command;

import java.util.ArrayList;

public class CreateTable extends CommandType{

    String tableName;
    ArrayList<String> attrList;
    boolean hasAttrList;
    public CreateTable(){hasAttrList = false;}
    public void setTableName(String name){
        this.tableName = name;
    }
    public void setAttrList(ArrayList<String> list){
        this.attrList = list;
        hasAttrList = true;
    }
}
