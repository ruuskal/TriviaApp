package triviaapp.domain;

import java.util.List;
import triviaapp.dao.QuestionDao;

public class GameService {
    
    private QuestionDao questiondao;
    
    public GameService(QuestionDao questiondao) {
        this.questiondao = questiondao;
    }
    
    public String getContent(int i) {
        String questionContent = questiondao.getQuestion(i).getContent();
        return questionContent;
    }
        
    public List getOptions(int i) {
        List options = questiondao.getQuestion(i).getOptions();
        return options;
    }
    
        
    public String getA(int i) {
        return questiondao.getQuestion(i).getOptions().get(0).toString();
    }
    
    public String getB(int i) {
        return questiondao.getQuestion(i).getOptions().get(1).toString();
    }
    
    public String getC(int i) {
        return questiondao.getQuestion(i).getOptions().get(2).toString();
    }
    
    public String getD(int i) {
        return questiondao.getQuestion(i).getOptions().get(3).toString();
    }
        
    public boolean isCorrect(int i, String answer) {
        String rigthAnswer = questiondao.getQuestion(i).getAnswer();
        if (answer.trim().matches(rigthAnswer)) {
            return true;
        } else {
            return false;
        }
    }
        
    public String getCorrect(int i) {
        String correct = questiondao.getQuestion(i).getAnswer();
        return correct;
    }
    
    public int getQuestionsSize() {
        return questiondao.getQuestionSize();
    }
    
    public String getQuestionText(int i) {
        String question = questiondao.getQuestion(i).getContent();
        return question;
    }
}
