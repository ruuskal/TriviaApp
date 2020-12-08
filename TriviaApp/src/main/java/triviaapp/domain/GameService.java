package triviaapp.domain;

import java.io.IOException;
import java.util.List;
import triviaapp.dao.PlayerDao;
import triviaapp.dao.QuestionDao;

public class GameService {
    
    private QuestionDao questiondao;
    private Player player;
    private PlayerDao playerdao;
    
    public GameService(QuestionDao questiondao, Player player, PlayerDao playerdao) {
        this.questiondao = questiondao;
        this.player = player;
        this.playerdao = playerdao;
    }
    
    public String getTopScore()  {
  
        try {
            //            String [] parts = playerdao.readFile(10).split("\n");
//            for (int i = 0; i < 5; i++ ) {
//                String name = parts[i].split(";")[0];
//                String points = parts[i].split(";")[1].trim();
//                int p = Integer.valueOf(points);
//                topScores.put(points, name);
//            }
            return playerdao.readFile(10);
        } catch (IOException ex) {
            return "Exception : " + ex;
        }

    }
    
//    public void sortTopScores(Map map){
//        
//        List <String> list = new ArrayList <>(map.keySet());
//        Collections.sort(list);
//        System.out.println(list);
//    }
        
    public void addScore(String name, int points) throws IOException {

        for (int j = 0; j<5; j++){
            String[] best = playerdao.readFile(j).split(";");
            String p = best[1].trim();
            int bestPoints = Integer.valueOf(p);
            if(points >= bestPoints) {
                playerdao.writeToFile(name, points);
                break;
            } else {
                System.out.println("ei parhaita pisteitä"); //väliaikainen ratkaisu
            }
        }
   
      
    }
    
    public boolean isOver(int i) {
        if (i >= this.questiondao.getQuestionSize() - 1) {
            return true;
        } else { 
            return false;
        }
    }
    
    public int getPoints() {
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
       
    public String getOption(int questionInt, int optionInt) {
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
