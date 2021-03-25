package command;

import dbStructure.Column;
import dbStructure.DatabaseManager;
import dbStructure.Table;
import exception.CommandExecutionException;

import java.util.ArrayList;

public class CreateTable extends CommandType{

    String tableName;
    ArrayList<Column> attrList;
    boolean hasAttrList;
    public CreateTable(){hasAttrList = false;attrList = new ArrayList<>();}
    public void setTableName(String name){
        this.tableName = name;
    }
    public void setAttrList(ArrayList<String> list){
        for(int i=0 ; i < list.size(); i++){
            hasAttrList = true;
            Column column = new Column(list.get(i));
            attrList.add(column);
        }
    }
    @Override
    public void execute(DatabaseManager manager) throws CommandExecutionException {
        if(manager.getCurrDB()==null) throw new CommandExecutionException("please choose a database to work on at first");
        Table tb = manager.getCurrDB().addTable(tableName);
        if(hasAttrList){
            tb.setColumns(attrList);
        }
        System.out.println("[OK]");
    }
}
