
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
import triviaapp.domain.Player;
import triviaapp.domain.Question;

public class GameServiceTest {

    GameService gameService;
    Question questionZero;
    Question questionOne;
    Player player;
    
    @Before
    public void setUp(){
        player = new Player ("test");
        
        List <Question> questions = new ArrayList<>();
        List <String> optionsZero = new ArrayList<>();
        optionsZero.add("1");
        optionsZero.add("2");
        optionsZero.add("3");
        this.questionZero = new Question ("Which is 2", optionsZero, "2");
        ArrayList<String>optionsOne = new ArrayList <>();
        optionsOne.add("a");
        optionsOne.add("b");
        optionsOne.add("c");
        this.questionOne = new Question("Which is a", optionsOne, "a");
        
        List <String> topScores = new ArrayList<>();
        FakePlayerDao playerdao = new FakePlayerDao(topScores);
                
        questions.add(questionZero);
        questions.add(questionOne);
        
        gameService = new GameService(new FakeQuestionDao(questions), player, playerdao);
    }

    @Test
    public void isCorrectAddsPointsWhenRight() {
        gameService.isCorrect("2", 0);
        assertEquals(10, player.getPoints());
    }
    
    @Test
    public void isCorrectDoesntAddPointsWhenNotCorrect() {
        gameService.isCorrect("5", 0);
        assertEquals(0, player.getPoints());
    }
    
    @Test
    public void isCorrectSetsQuestionAnswered() {
        gameService.isCorrect("2", 0);
        assertEquals(true, questionZero.isAnswered());
    }
    
    @Test 
    public void questionListsizeIsCorrect() {
        assertEquals(2, gameService.getQuestionsSize());
    }
    
    @Test
    public void isOverReturnsFalseWhenQuestionIndexIsTooBig() {
        assertEquals(true, gameService.isOver(2));
    }
    
    
    
}
