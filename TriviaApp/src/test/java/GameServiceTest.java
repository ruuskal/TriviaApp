
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import triviaapp.dao.QuestionDao;
import triviaapp.domain.GameService;
import triviaapp.domain.Question;

public class GameServiceTest {
    
    private List <Question> questions;
    private GameService gameService;
    private QuestionDao questionDao;
    
    @Before
    public void setUp(){
        List <String> options=new ArrayList<>();
        options.add("1");
        options.add("2");
        options.add("3");
        Question questionOne=new Question ("Which is 2", options, "2");
        ArrayList<String>optionsTwo=new ArrayList <>();
        optionsTwo.add("a");
        optionsTwo.add("b");
        optionsTwo.add("c");
        Question questionTwo=new Question("Which is a", optionsTwo, "a");
                
        questions.add(questionOne);
        questions.add(questionTwo);
        
        gameService=new GameService(questionDao);
    }
    
    @Test
    public void gameServiceIsNotEmpty(){
        
    }
    
  
//    @Test
//    public void isCorrectReturnsTrueWhenCorrect(){
//        assertTrue(gameService.isCorrect(0, "2"));
//    }
//    


 
}
