package triviaapp.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FilePlayerDao implements PlayerDao {
    private String file;
    
    public FilePlayerDao(String file) {
        this.file = file;
    }
   
    @Override
    public void writeToFile(String name, int points) {
        try {
            FileWriter writer = new FileWriter(this.file, true);
            writer.write(name + ";" + points + "\n");
            writer.close();
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
    }
    
    @Override
    public String readFile(int howMany) {
        try {
            String line = "";
            String lineToAdd = "";
            FileReader reader = new FileReader(this.file);
            BufferedReader br = new BufferedReader(reader);
            int i = 0;
            
            while (i < howMany) {
                lineToAdd = br.readLine();
                line = line + lineToAdd + "\n";
                i++;
            }
            return line;
        
        } catch (Exception e) {
            return "Exception "+ e;
        }
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
