
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import triviaapp.dao.FileQuestionDao;
import triviaapp.domain.GameService;
import triviaapp.domain.Question;

public class GameServiceTest {

    GameService gameService;
    FakeQuestionDao questionDao;
    
    @Before
    public void setUp(){
        List <Question> questions=new ArrayList<>();
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
        
        gameService=new GameService(new FakeQuestionDao(questions));
    }
      
  
//    @Test
//    public void isCorrectReturnsTrueWhenCorrect(){
//        assertTrue(gameService.isCorrect(0, "2"));
//    }
    
    
    @Test 
    public void questionListsizeIsCorrect(){
        assertEquals(2, gameService.getQuestionsSize());
    }
  

}
