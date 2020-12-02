package triviaapp.domain;

import java.util.List;
import triviaapp.dao.FileQuestionDao;

public class GameService {
    
    private FileQuestionDao questiondao;
    private Player player;
    
    public GameService(FileQuestionDao questiondao) {
        this.questiondao = questiondao;
        this.player = new Player("Test");
    }
    
    public boolean isOver(int i) {
        if(i == this.questiondao.getQuestionSize()){
            return true;
        }else { 
            return false;
        }
    }
    
        
    public int getPoints(){
        return player.getPoints();
    }
    
    public String getContent(int i) {
        String questionContent = questiondao.getQuestion(i).getContent();
        return questionContent;
    }
        
    public List getOptions(int i) {
        List options = questiondao.getQuestion(i).getOptions();
        return options;
    }
    
        
    public String getNextQuestion(int i) {
        String question = questiondao.getQuestion(i).getContent();
        return question;
    }
       
    public String getOption(int questionInt, int optionInt ) {
        return questiondao.getQuestion(questionInt).getOptions().get(optionInt).toString();
    }
         
    public boolean hasBeenAnswered(int i) {
        return questiondao.getQuestion(i).isAnswered();
    }
        
    public boolean isCorrect(String answer, int i) {
        questiondao.getQuestion(i).setAnswered();
        String rigthAnswer = questiondao.getQuestion(i).getAnswer();
        if (answer.trim().matches(rigthAnswer)) {
            player.addPoints(10);
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
