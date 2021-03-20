package reader;

public class Column {
    //the name of the column
    private String name;
    private Table table;
    //the ordinal position of this column in a row
    private int index;
    //the following fields are optional
    private boolean isPrimaryKey;
    private String type;
    private boolean isNullable;

    public Column(String name){
        this.name = name;
    }
    public void setTable(Table table){
        this.table = table;
    }
    public void setIndex(int index){
        this.index = index;
    }
    public Table getTable(){
        return table;
    }
    public int getIndex(){
        return index;
    }
    public String getName(){
        return name;
    }
    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }
    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }
}
