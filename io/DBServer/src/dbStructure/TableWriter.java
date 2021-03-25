package dbStructure;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TableWriter {
    Table table;
    File file;
    public TableWriter(Table table, String path) {
        this.table = table;
        file = new File(path);
    }
    public void writeToTable() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        StringBuilder s = new StringBuilder();
        //write the column names to the table file;
        for(int i=0; i<table.getColNames().size(); i++){
            s.append(table.getColNames().get(i)).append("\t");
        }
        writer.write(s.toString());
        writer.write("\n");
        //write the rows to the table file
        StringBuilder row = new StringBuilder();
        for(int i=0; i<table.getRows().size();i++){
            row.append(table.getRows().get(i).toString());
            row.append('\n');
        }
        writer.write(row.toString());
        writer.close();
    }

}
