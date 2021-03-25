package dbStructure;

import exception.CommandExecutionException;
import command.common.Value;


import java.util.ArrayList;

public class Row {
    private ArrayList<Value> row;
    private Table table;
    public boolean isDeleted;
    int id;

    public Row(Table table, int id, ArrayList<Value> values){
        this.table = table;
        this.id = id;
        this.isDeleted=false;
        row = new ArrayList<>();
        //set up primary key as the first element of the row;
        Value pk = new Value();
        pk.setValueType(Value.ValueType.IntegerLiteral);
        pk.setContent(Integer.toString(id));
        row.add(pk);
        row.addAll(values);
    }

    public Row(Table table, int id){
        this.table = table;
        this.id = id;
        this.isDeleted=false;
        row = new ArrayList<>();
        //set up primary key as the first element of the row;
        Value pk = new Value();
        pk.setValueType(Value.ValueType.IntegerLiteral);
        pk.setContent(Integer.toString(id));
        row.add(pk);
    }

    public int getId(){
        return id;
    }

    public void setValue(String columnName, Value value) throws CommandExecutionException
    {
        Column column = table.getColumn(columnName);
        int index = table.getColumnIndex(columnName);
        if(column.getType()!=null){
            if(column.getType()!=value.getValueType()){
                throw new CommandExecutionException("type conflict: Column " + columnName+ " accepts " +
                        column.getType()+"; Value "+ value.getContent()+" is of "+ value.getValueType()+" type");
            }
        }
        else{
            column.setType(value.getValueType());
        }
        if(index >= row.size()) {
            row.add(index, value);
        }
        else{
            row.set(index, value);
        }
        table.writeToTable();
    }
    public void addValue(int columnIndex, Value value) throws CommandExecutionException
    {
        Column column = table.getColumnByIndex(columnIndex);
        if(column.getType()!=value.getValueType()){
            throw new CommandExecutionException("type conflict: Column " + column.getName()+ " accepts " +
                    column.getType()+"; Value "+ value.getContent()+" is of "+ value.getValueType()+" type");
        }
        row.add(columnIndex, value);
    }

    public void deleteRow(){
        isDeleted = true;
    }

    public void removeValue(int index) throws CommandExecutionException{
        if(index >= table.getColumns().size()) throw new CommandExecutionException("fail to delete the value of column "+ table.getColumnByIndex(index).getName());
        if(index >= row.size()) return;
        else{
            row.remove(index);
        }
    }

    public Value getValue(String columnName) throws CommandExecutionException{
        int index = table.getColumnIndex(columnName);
        if(index >= row.size()) throw new CommandExecutionException("fail to update the value of column "+columnName);
        return row.get(index);
    }
    public Value getValue(Integer index) throws CommandExecutionException{
        if(index >= row.size()) throw new CommandExecutionException("fail to update the value of column "+table.getColumnByIndex(index).getName());
        return row.get(index);
    }
    public String toString(){
        String s = "";
        for(int i=0; i<row.size();i++){
            s+=row.get(i).getContent()+"\t";
        }
        return s;
    }

}
