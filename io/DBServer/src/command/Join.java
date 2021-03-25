package command;

import command.Where.SingleExpr;
import command.common.Value;
import dbStructure.DatabaseManager;
import dbStructure.Row;
import dbStructure.Table;
import exception.CommandExecutionException;

import java.util.ArrayList;

public class Join extends CommandType{
    String tableName1;
    String tableName2;
    Table table1;
    Table table2;
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

    @Override
    public void execute(DatabaseManager manager) throws CommandExecutionException {
        table1=manager.getCurrDB().getTable(tableName1);
        table2=manager.getCurrDB().getTable(tableName2);
        StringBuilder s = new StringBuilder();
        s.append("id").append('\t');
        setUpCols(s);
        setUpRows(s);
        output = s.toString();
    }
    void setUpCols(StringBuilder s)
    {
        for(String col : table1.getColNames()){
            s.append(tableName1).append('_').append(col).append('\t');
        }
        for(String col: table2.getColNames()){
            s.append(tableName2).append('_').append(col).append('\t');
        }
        s.append('\n');
    }
    void setUpRows(StringBuilder s) throws CommandExecutionException {
        int lines = 1;
        for(Row row : table1.getRows()){
            SingleExpr expression = new SingleExpr();
            expression.setOperator(SingleExpr.OperatorType.EQUAL);
            expression.setColumnName(columnName2);
            expression.setValue(row.getValue(columnName1));
            ArrayList<Integer> matchRows = expression.getRowIds(table2);
            for(int id:matchRows){
                s.append(lines++).append('\t');
                s.append(row);
                s.append(table2.getRowById(id));
                s.append('\n');
            }
        }
    }
    public String getOutput(){return output;}
}
