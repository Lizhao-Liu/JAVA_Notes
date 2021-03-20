package reader;


import java.util.ArrayList;

public class Row {
    private ArrayList<String> row;
    private Table table;
    private int id;

    public Row(Table table){
        this.table = table;
        row = new ArrayList<>(table.getColumns().size());
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
//    public String getValueByColumn(Column column){
//
//    }

}
