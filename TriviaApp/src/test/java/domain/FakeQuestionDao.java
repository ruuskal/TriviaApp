package domain;

import java.util.ArrayList;
import java.util.List;
import triviaapp.dao.QuestionDao;
import triviaapp.domain.Question;


public class FakeQuestionDao implements QuestionDao {
    
    List <Question> questions;
    
    public FakeQuestionDao() {
    
        this.questions = new ArrayList<>();
        List <String> optionsZero = new ArrayList<>();
        optionsZero.add("1");
        optionsZero.add("2");
        optionsZero.add("3");
        optionsZero.add("4");
        Question questionZero = new Question ("Which is 2", optionsZero, "2");
        ArrayList <String> optionsOne = new ArrayList <>();
        optionsOne.add("a");
        optionsOne.add("b");
        optionsOne.add("c");
        optionsOne.add("d");
        Question questionOne = new Question("Which is a", optionsOne, "a");
        questions.add(questionZero);
        questions.add(questionOne);
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