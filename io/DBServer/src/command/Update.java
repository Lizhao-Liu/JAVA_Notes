package command;

import command.Where.Condition;
import command.common.NameValuePair;
import dbStructure.DatabaseManager;
import dbStructure.Row;
import dbStructure.Table;
import exception.CommandExecutionException;

import java.util.ArrayList;

public class Update extends CommandType{
    String tableName;
    ArrayList<NameValuePair> nameValuePairs;
    Condition condition;
    Table table;

    public Update()
    {
        tableName = "";
        nameValuePairs = new ArrayList<>();
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public void setNameValuePairs(ArrayList<NameValuePair> nameValuePairs) {
        this.nameValuePairs = nameValuePairs;
    }
    public void setCondition(Condition condition) {
        this.condition = condition;
    }
    public void execute(DatabaseManager manager) throws  CommandExecutionException {
        table = manager.getCurrDB().getTable(tableName);
        ArrayList<Integer> targetRowIds = condition.getRowList(table);
        for(int id : targetRowIds){
            Row row = table.getRowById(id);
            for(NameValuePair pair:nameValuePairs){
                table.setRowValueByCol(row, pair.getColumnName(), pair.getValue());
            }
        }
    }
}
