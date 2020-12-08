package triviaapp.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class PlayerDao {
    private String file;
    
    public PlayerDao(String file) {
        this.file = file;
    }
   
    public void writeToFile(String name, int points) throws IOException {
        FileWriter writer = new FileWriter(this.file, true);
        writer.write(name + ";" + points + "\n");
        writer.close();
    }
    
    public String readFile(int howMany) throws FileNotFoundException, IOException {
        String line = "";
        String lineToAdd = "";
        FileReader reader = new FileReader(this.file);
        BufferedReader br = new BufferedReader(reader);
        int i = 0;
        while (i <= howMany) {
            lineToAdd = br.readLine();
            line = line + lineToAdd + "\n";
            i++;
        }
        return line;
//        FileReader fr = new FileReader(this.file);
//        BufferedReader br = new BufferedReader(fr);  
//        StringBuffer sb =new StringBuffer();    
//        String line = "";  
//        
//        while((line = br.readLine()) != null) {  
//            sb.append(line);  
//            sb.append("\n");      
//        }      
//          return line;
    }
    
}
