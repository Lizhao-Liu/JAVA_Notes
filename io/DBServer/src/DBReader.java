import java.io.*;

public class DBReader {

    public DBReader(File fileToOpen)
    {
        try {
            BufferedReader buffReader = new BufferedReader(new FileReader(fileToOpen));
            String line;
            while((line = buffReader.readLine())!=null){
                System.out.println(line);
            }
            buffReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        String filename = "resources/contact-details.tab" ;
        File fileToOpen = new File(filename);
        DBReader reader = new DBReader(fileToOpen);
    }
}
