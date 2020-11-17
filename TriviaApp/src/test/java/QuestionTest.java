import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import triviaapp.domain.Question;

public class QuestionTest {
    
    Question question;
    
    @Before
    public void setUp(){
        question=new Question ("Which is 2", "1,2,3,4", "2");
    }
    
    @Test
    public void contentIsCorrect() {
        assertEquals("Which is 2", question.getContent());
    }
    
    @Test
    public void optionsAreCorrect(){
        assertEquals("1,2,3,4", question.getOptions());
    }
    
    @Test
    public void answerIsCorrect(){
        assertEquals("2", question.getAnswer());
    }
}
