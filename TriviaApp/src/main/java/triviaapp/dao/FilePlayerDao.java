package triviaapp.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import triviaapp.domain.Player;

/**
 * Vastaa Player-olioiden tiedostosta lukemisesta ja tallentamisesta.
 * 
 */
public class FilePlayerDao implements PlayerDao {
    private String file;
    ArrayList<Player> players;
    
    public FilePlayerDao(String file) {
        this.file = file;
        players = new ArrayList<>();
    }
   
    @Override
    public void writeToFile(String name, int points) {
        try {
            FileWriter writer = new FileWriter(this.file, true);
            writer.write("\n" + name + ";" + points);
            writer.close();
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
    }
    
    @Override
    public List readFile() {

        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String [] parts = reader.nextLine().split(";");
                Player p = new Player();
                p.setName(parts[0]);
                if (parts[1].isEmpty()) {
                    p.setPoints(0);
                } else {
                    p.setPoints(Integer.valueOf(parts[1]));
                }
                players.add(p);
            
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Exception : " + ex);
        }
        return this.players;
    }  
}