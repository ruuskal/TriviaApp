package domain;

import java.util.List;
import triviaapp.dao.QuestionDao;
import triviaapp.dao.Question;


public class FakeQuestionDao implements QuestionDao {
    
    List <Question> questions;
    
    public FakeQuestionDao(List list) {
        questions=list;
    }
    
    @Override
    public Question getQuestion(int i) {
        return questions.get(i);
    }
   
    @Override
    public int getQuestionSize() {
        return questions.size();
    }
}