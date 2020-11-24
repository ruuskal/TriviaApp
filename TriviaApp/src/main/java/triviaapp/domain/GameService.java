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
        
    public List getOptions(int i){
        List options=questions.get(i).getOptions();
        return options;
    }
    
        
    public String getA(int i){
        return questions.get(i).getOptions().get(0).toString();
    }
    
    public String getB(int i){
        return questions.get(i).getOptions().get(1).toString();
    }
    
    public String getC(int i){
        return questions.get(i).getOptions().get(2).toString();
    }
    
    public String getD(int i){
        return questions.get(i).getOptions().get(3).toString();
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
    
    public int getQuestionsSize(){
        return questions.size();
    }
    
    public String getNextQuestion(int i){
        String question=questions.get(i).getContent();
        return question;
    }
}
