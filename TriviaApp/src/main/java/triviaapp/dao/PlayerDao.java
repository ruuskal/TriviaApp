package triviaapp.dao;

import java.util.List;

public interface PlayerDao {
    
    List readFile();
    
    void writeToFile(String s, int i);
}
