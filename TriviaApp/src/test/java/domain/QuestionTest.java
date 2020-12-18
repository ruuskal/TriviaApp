package domain;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import triviaapp.dao.Question;

public class QuestionTest {
    
    Question question;
    
    @Before
    public void setUp(){
        List <String> options=new ArrayList<>();
        options.add("1");
        options.add("2");
        options.add("3");
        question=new Question ("Which is 2", options, "2");
    }
    
    @Test
    public void contentIsCorrect() {
        assertEquals("Which is 2", question.getContent());
    }
    
    @Test
    public void optionsAreCorrect(){
        List <String> options=new ArrayList<>();
        options.add("1");
        options.add("2");
        options.add("3");
        assertEquals(options, question.getOptions());
    }
    
    @Test
    public void answerIsCorrect(){
        assertEquals("2", question.getAnswer());
    }
    
}
