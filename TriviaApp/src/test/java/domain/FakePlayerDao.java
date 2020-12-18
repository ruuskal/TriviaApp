package domain;



import java.util.List;
import org.junit.Before;
import triviaapp.dao.PlayerDao;
import triviaapp.domain.Player;


public class FakePlayerDao implements PlayerDao{
    private List<Player> scoreBoard;
    
    public FakePlayerDao (List list){
        this.scoreBoard = list;
    }
       
    @Override
    public List readFile(){
        return this.scoreBoard;
    }
    
    @Override
    public void writeToFile(String name, int points){
        Player p = new Player();
        p.setPoints(points);
        p.setName(name);
        this.scoreBoard.add(p);
    }
}
