package triviaapp.domain;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import triviaapp.dao.PlayerDao;
import triviaapp.dao.QuestionDao;

public class GameService {
    
    private QuestionDao questiondao;
    private Player player;
    private PlayerDao playerdao;
    private int currentQuestionIdx;
    
    public GameService(QuestionDao questiondao, Player player, PlayerDao playerdao) {
        this.questiondao = questiondao;
        this.player = player;
        this.playerdao = playerdao;
        this.currentQuestionIdx = 0;
    }
  
    public List getSortedScoreBoard() {
        
        List <Player> list = this.playerdao.readFile();
        
        List <Player> sortedList = list.stream()
                .sorted(Comparator.comparingInt(Player::getPoints))
                        .collect(Collectors.toList());
        sortedList.stream().forEach(p -> p.toString());
        Collections.reverse(sortedList);
        
        return sortedList;
    }
        
    public void addScore(String name, int points) throws IOException {
        playerdao.writeToFile(name, points);
    }

    
    public boolean isOver() {
        if (this.currentQuestionIdx < questiondao.getQuestionSize() - 1 ) {
            return false;
        } else {
            return true;
        }
    }
    
    public int getPoints() {
        return player.getPoints();
    }
    
    /**
     * hakee kysymysosan tietylle kysymykselle
     * @return kysymyksen kysymysosa
     */
    public String getContent() {
        String questionContent = questiondao.getQuestion(currentQuestionIdx).getContent();
        return questionContent;
    }
    
    /**
     * 
     * @param questionInt kysymyksen indeksi
     * @param optionInt vaihtoehdon indeksi
     * @return  vastausvaihtoehto
     */  
    public String getOption(int optionInt) {
        return questiondao.getQuestion(currentQuestionIdx).getOptions().get(optionInt).toString();
    }
    
    /**
     * 
     *
     * @return true, jos kysymykseen on jo vastattu
     */
    public boolean hasBeenAnswered() {
        return questiondao.getQuestion(currentQuestionIdx).isAnswered();
    }
    
    /**
     * 
     * @param answer syötetty vastaus
     * @return  true, jos vastaus on oikea
     */
    public boolean isCorrect(String answer) {
        questiondao.getQuestion(currentQuestionIdx).setAnswered();
        String rigthAnswer = questiondao.getQuestion(currentQuestionIdx).getAnswer();
        
        if (answer.trim().matches(rigthAnswer)) {
            player.addPoints(10);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Hakee oikean vastauksen kysymykseen
     * @return oikea vastaus
     */
    public String getCorrect() {
        String correct = questiondao.getQuestion(currentQuestionIdx).getAnswer();
        return correct;
    }
    /**
     *
     * @return Pelin kysymysten määrä 
     */
    public int getQuestionsSize() {
        return questiondao.getQuestionSize();
    }
    
    public void moveToNextQuestion() {
        this.currentQuestionIdx++;
    }
    
}