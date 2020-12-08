package triviaapp.domain;

import java.io.IOException;
import triviaapp.dao.FilePlayerDao;
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
  
        //            String [] parts = playerdao.readFile(10).split("\n");
//            for (int i = 0; i < 5; i++ ) {
//                String name = parts[i].split(";")[0];
//                String points = parts[i].split(";")[1].trim();
//                int p = Integer.valueOf(points);
//                topScores.put(points, name);
//            }
        return playerdao.readFile(10);

    }
    
//    public void sortTopScores(Map map){
//        
//        List <String> list = new ArrayList <>(map.keySet());
//        Collections.sort(list);
//        System.out.println(list);
//    }
        
    public void addScore(String name, int points) throws IOException {
        
        playerdao.writeToFile(name, points);
//
//        for (int j = 0; j < 5; j++) {
//            String[] best = playerdao.readFile(j).split(";");
//            String p = best[1].trim();
//            int bestPoints = Integer.valueOf(p);
//            if (points >= bestPoints) {
//                playerdao.writeToFile(name, points);
//                break;
//            } else {
//                System.out.println("ei parhaita pisteitä"); //väliaikainen ratkaisu
//            }
//        }
   
      
    }
    
    /**
     * 
     * @param i viimeksi kysytyn kykymyksen indeksi
     * @return true, jos kysymyksiä ei ole enempää
     */
    public boolean isOver(int i) {
        if (i >= this.questiondao.getQuestionSize() - 1) {
            return true;
        } else { 
            return false;
        }
    }
    
    /**
     * 
     * @return pelaajan ansaitsemat pisteet
     */
    public int getPoints() {
        return player.getPoints();
    }
    
    /**
     * hakee kysymysosan tietylle kysymykselle
     * @param i kysymyksen indeksi
     * @return kysymyksen kysymysosa
     */
    public String getContent(int i) {
        String questionContent = questiondao.getQuestion(i).getContent();
        return questionContent;
    }


    /**
     * 
     * @param questionInt kysymyksen indeksi
     * @param optionInt vaihtoehdon indeksi
     * @return  vastausvaihtoehto
     */  
    public String getOption(int questionInt, int optionInt) {
        return questiondao.getQuestion(questionInt).getOptions().get(optionInt).toString();
    }
    
    /**
     * 
     * @param i kysymyksen indeksi
     * @return true, jos kysymykseen on jo vastattu
     */
    public boolean hasBeenAnswered(int i) {
        return questiondao.getQuestion(i).isAnswered();
    }
    
    /**
     * 
     * @param answer syötetty vastaus
     * @param i kysymyksen indeksi
     * @return  true, jos vastaus on oikea
     */
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
    
    /**
     * Hakee oikean vastauksen kysymykseen
     * @param i kysymyksen indeksi
     * @return oikea vastaus
     */
    public String getCorrect(int i) {
        String correct = questiondao.getQuestion(i).getAnswer();
        return correct;
    }
    /**
     *
     * @return Pelin kysymysten määrä 
     */
    public int getQuestionsSize() {
        return questiondao.getQuestionSize();
    }
    
}
