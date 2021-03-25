package dbStructure;

import exception.CommandExecutionException;
import command.common.Value;

import java.util.ArrayList;

public class Row {
    private ArrayList<Value> row;
    private Table table;
    public boolean isDeleted;
    int id;

    public Row(Table table, int id, ArrayList<Value> values)
    {
        this.table = table;
        this.id = id;
        this.isDeleted=false;
        row = new ArrayList<>();
        //set up primary key as the first element of the row;
        setUpPk();
        row.addAll(values);
    }

    public Row(Table table, int id)
    {
        this.table = table;
        this.id = id;
        this.isDeleted=false;
        row = new ArrayList<>();
        //set up primary key as the first element of the row;
        setUpPk();
    }

    public int getId(){
        return id;
    }
    public void setUpPk(){
        Value pk = new Value();
        pk.setValueType(Value.ValueType.IntegerLiteral);
        pk.setContent(Integer.toString(id));
        row.add(pk);
    }

    public void setValue(int index, Value value)
    {
        if(index >= row.size()) {
            row.add(index, value);
        }
        else{
            row.set(index, value);
        }
    }

    public void addValue(int columnIndex, Value value)
    {
        row.add(columnIndex, value);
    }

    public void delete(){
        isDeleted = true;
    }

    public void removeValue(int index)
    {
        if(index >= row.size()) return;
        else{
            row.remove(index);
        }
    }

    public Value getValue(Integer index) throws CommandExecutionException{
        if(index >= row.size()) throw new CommandExecutionException("fail to update the value of column "+
                table.getColumnByIndex(index).getName());
        return row.get(index);
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for (Value value : row) {
            s.append(value.getContent()).append("\t");
        }
        return s.toString();
    }

}
