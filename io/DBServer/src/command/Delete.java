package command;

import command.Where.Condition;
import dbStructure.DatabaseManager;
import dbStructure.Row;
import dbStructure.Table;
import exception.CommandExecutionException;

import java.util.ArrayList;

public class Delete extends CommandType{
    String tableName;
    Table table;
    Condition condition;
    public Delete(){}

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public String getTableName() {
        return tableName;
    }

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void execute(DatabaseManager manager) throws CommandExecutionException {
        output=new String();
        table = manager.getCurrDB().getTable(tableName);
        StringBuilder s = new StringBuilder();
        for(String col:table.getColNames()){
            s.append(col).append('\t');
        }
        s.append('\n');
        ArrayList<Integer> deletedRowIds = condition.getRowList(table);
        for(int id: deletedRowIds){
            table.deleteARow(id);
        }
    }
    public String getOutput(){return output;}
}
