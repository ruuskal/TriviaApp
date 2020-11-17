package triviaapp.domain;

import java.util.List;
import triviaapp.dao.QuestionDao;

public class GameService {
    
    private List <Question> questions;
    private QuestionDao questiondao;
    
    public GameService (QuestionDao questiondao){
        this.questiondao=questiondao;
        this.questions=questiondao.getQuestions();
    }
    
    public String getContent(int i){
        String question=questions.get(i).getContent();
        return question;
    }
        
    public String getOptions(int i){
        String options=questions.get(i).getOptions();
        return options;
    }
        
    public boolean isCorrect(int i, String answer){
        String rigthAnswer=questions.get(i).getAnswer();
        if (answer.trim().matches(rigthAnswer) ){
            return true;
        }else {
            return false;
        }
    }
        
    public String getCorrect(int i){
        String correct=questions.get(i).getAnswer();
        return correct;
    }
}
