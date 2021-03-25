package dbStructure;

import exception.CommandExecutionException;
import command.common.Value;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Table {
    private TableWriter writer;
    private Database database;
    private String path;
    private String name;
    private ArrayList<Column> columns;
    private Map<String, Integer> columnMap;
    private ArrayList<Row> rows;
    private Map<Row, Integer> rowToIdMap;
    private Map<Integer, Row> idToRowMap;
    private int nextRowId;
    public static final String extension = ".tb";
    public static final String pkName = "id";


    public Table(String name) {
        this.name = name;
        columnMap = new HashMap<>();
        rowToIdMap = new HashMap<>();
        idToRowMap = new HashMap<>();
        columns= new ArrayList<>();
        rows = new ArrayList<>();
        setUpPk();
    }

    public void create() throws CommandExecutionException {
        this.path = database.getDbPath() + name + extension;
        File tb = new File(this.path);
        if (!tb.exists()) {
            try {
                tb.createNewFile();
            } catch (IOException e) {
                throw new CommandExecutionException("fail to create the table" + name + " due to I/O problem");
            }
        } else {
            throw new CommandExecutionException("please delete the existing files first");
        }
        writer = new TableWriter(this);
    }

    public String getPath(){
        return path;
    }

    public void setDatabase(Database db){
        this.database = db;
    }

    public Column getColumn(String columnName) throws CommandExecutionException {
        if(containsColumn(columnName)){
            return columns.get(columnMap.get(columnName));
        }
        throw new CommandExecutionException("column " + columnName + " doesn't exist");
    }

    public Integer getColumnIndex(String columnName) throws CommandExecutionException {
        if(containsColumn(columnName)){
            return columnMap.get(columnName);
        }
        throw new CommandExecutionException("column " + columnName + " doesn't exist");
    }
    public Column getColumnByIndex(int index){
        return columns.get(index);
    }

    public void addColumn(String name) throws CommandExecutionException {
        if(containsColumn(name)) throw new CommandExecutionException("column "+ name+" already exists");
        Column column = new Column(name);
        column.setTable(this);
        column.setIndex(columns.size());
        columnMap.put(name, columns.size());
        columns.add(column);
        writeToTable();
    }
    public void writeToTable() throws CommandExecutionException {
        try {
            writer.writeToTable();
        } catch (IOException e) {
            throw new CommandExecutionException("fail to save the data to table due to i/o problems");
        }
    }

    public void setColumns(ArrayList<Column> attrList) throws CommandExecutionException {
        for(int i=0; i<attrList.size(); i++){
            Column target = attrList.get(i);
            if(!containsColumn(target.getName())){
                columnMap.put(target.getName(), columns.size());
                columns.add(target);
            }
            else{
                throw new CommandExecutionException("column "+ target.getName()+" already exists");
            }
        }
        writeToTable();
    }

    public void setUpPk(){
        Column primary = new Column(pkName);
        primary.setType(Value.ValueType.IntegerLiteral);
        primary.setTable(this);
        primary.setIndex(0);
        columnMap.put(pkName, columns.size());
        columns.add(primary);
        nextRowId = 1;
    }

    public void dropColumn(String columnName) throws CommandExecutionException
    {
        if(!containsColumn(columnName)) throw new CommandExecutionException("Column "+ columnName + " doesn't exist");
        int index = columnMap.get(columnName);
        for(int i = 0; i<rows.size();i++){
            rows.get(i).removeValue(index);
        }
        columns.remove(index);
        columnMap.remove(columnName);
        writeToTable();
    }

    public void addaRow(ArrayList<Value> values) throws CommandExecutionException {
        if((values.size()+1)!=columns.size()) throw new CommandExecutionException("the length of value list " +
                "doesn't match the length of columns in this table");
        //the first insertion of values determines the type of the columns;
        Row row;
        if(nextRowId==1){
            for(int i=0; i<values.size(); i++){
                columns.get(i+1).setType(values.get(i).getValueType());
            }
            row = new Row(this, nextRowId, values);
        }
        else{
            row = new Row(this, nextRowId);
            for(int i=0; i<values.size();i++){
                row.addValue(i+1, values.get(i));
            }
        }
        rows.add(row);
        rowToIdMap.put(row, nextRowId);
        idToRowMap.put(nextRowId, row);
        nextRowId++;
        writeToTable();
    }
    public void deleteARow(int id) throws CommandExecutionException {
        Row row = getRowById(id);
        row.deleteRow();
        idToRowMap.remove(id);
        rows.remove(row);
        rowToIdMap.remove(row);
        writeToTable();
    }
    public Row getRowById(int id) throws CommandExecutionException {
        if(id<=0 || id>nextRowId) throw new CommandExecutionException("Row at id "+ id + " doesn't exist");
        return idToRowMap.get(id);
    }

    public ArrayList<Row> getRows(){
        return rows;
    }

    public ArrayList<Column> getColumns(){
        return columns;
    }

    public boolean containsColumn(String columnName) {
        return columnMap.containsKey(columnName);
    }
    public ArrayList<String> getColNames(){
        ArrayList<String> names= new ArrayList<>();
        for(int i =0; i < columns.size(); i++){
            names.add(columns.get(i).getName());
        }
        return names;
    }

}
