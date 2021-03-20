package reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {
    private String name;
    private ArrayList<Column> columns;
    private Map<String, Column> columnMap;


    private String tablePath;

    public Table(){}

    public void setName(String name){
        this.name = name;
    }
    public void setTablePath(){}

    public Column getColumnByName(String columnName){
        return columnMap.get(columnName);
    }
    public Column getColumnByIndex(int index){
        return columns.get(index);
    }
    public void addColumn(Column column){
        column.setTable(this);
        column.setIndex(columns.size());
        columns.add(column);
    }
    public ArrayList<Column> getColumns() {
        return columns;
    }

    public String getName() {
        return name;
    }

    public String getTablePath() {
        return tablePath;
    }
    public void setColumns(ArrayList<Column> columns){
        this.columns = columns;
        columnMap = new HashMap<String, Column>();
        for(int i=0; i<columns.size(); i++){
            Column target = columns.get(i);
            columnMap.put(target.getName(), target);
        }
    }
    public boolean containsColumn(String columnName) {
        return columnMap.containsKey(columnName);
    }

//    public List<String> getItems() {
//        List<Item> list = new LinkedList<Item>();
//        for (Attribute attribute : attributes) {
//            Value[] values = ValueConvertUtil.convertAttr(attribute);
//            IndexEntry tuple = new IndexEntry(values);
//            Item item = new Item(tuple);
//            list.add(item);
//        }
//        return list;
//    }
}
