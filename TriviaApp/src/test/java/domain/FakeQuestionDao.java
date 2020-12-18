import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import triviaapp.dao.QuestionDao;
import triviaapp.domain.Question;


public class FakeQuestionDao implements QuestionDao {
    
    List <Question> questions;
    
    public FakeQuestionDao(List list ) {
        questions=list;
    }
    
    @Override
    public Question getQuestion(int i){
        return questions.get(i);
    }
   
    @Override
    public int getQuestionSize(){
        return questions.size();
    }
}
