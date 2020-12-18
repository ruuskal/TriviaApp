

import java.util.List;
import triviaapp.dao.PlayerDao;


public class FakePlayerDao implements PlayerDao{
    private List<String> topScores;
    
    public FakePlayerDao (List list){
        this.topScores = list;
    }
    
        
    public String readFile(int howMany){
        return "file";
    }
    
    public void writeToFile(String s, int i){
        
    }
}
