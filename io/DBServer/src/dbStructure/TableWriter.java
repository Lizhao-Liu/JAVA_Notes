package dbStructure;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TableWriter {
    Table table;
    File file;
    public TableWriter(Table table) {
        this.table = table;
        file = new File(table.getPath());
    }
    public void writeToTable() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        StringBuilder s = new StringBuilder();
        for(int i=0; i<table.getColNames().size(); i++){
            s.append(table.getColNames().get(i)).append("\t");
        }
        writer.write(s.toString());
        writer.write("\n");
        StringBuilder row = new StringBuilder();
        for(int i=0; i<table.getRows().size();i++){
            row.append(table.getRows().get(i).toString());
            row.append('\n');
        }
        writer.write(row.toString());
        writer.close();
    }

}
