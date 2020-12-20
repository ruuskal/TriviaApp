package triviaapp.domain;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import triviaapp.dao.PlayerDao;
import triviaapp.dao.QuestionDao;

/**
 * Sovelluslogiikasta vastaava luokka.
 */

public class GameService {
    
    private QuestionDao questiondao;
    private int playersPoints;
    private PlayerDao playerdao;
    private int currentQuestionIdx;
    private Question currentQuestion;
    
    public GameService(QuestionDao questiondao, PlayerDao playerdao) {
        this.questiondao = questiondao;
        this.playersPoints = 0;
        this.playerdao = playerdao;
        this.currentQuestionIdx = -1;
    }
    
  /**
   * Hakee pistetilaston ja järjestää sen parhaimmasta huonoimpaan pelaajaan
   * @return pistetilasto listana
   */
    public List getSortedScoreBoard() {
        
        List<Player> list = this.playerdao.readFile();
        List<Player> sortedList = list.stream()
                .sorted(Comparator.comparingInt(Player::getPoints))
                        .collect(Collectors.toList());
        sortedList.stream().forEach(p -> p.toString());
        Collections.reverse(sortedList);
        
        return sortedList;
    }
        
    public void addScore(String name, int points) throws IOException {
        playerdao.writeToFile(name, points);
    }

    /**
     * Tarkistaa, onko QuestionDao-olennolla kysymyksiä jäljellä.
     * @return true jos kysymyksiä on, muuten false
     */
    public boolean areThereMoreQuestions() {
        if (this.currentQuestionIdx < questiondao.getQuestionSize() - 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public int getPoints() {
        return this.playersPoints;
    }
    
    /**
     * hakee kysymysosan kysymykselle.
     * @return kysymyksen kysymysosa
     */
    public String getContent() {
        String questionContent = this.currentQuestion.getContent();
        return questionContent;
    }
    
    /**
     * Hakee yhden vastausvaihtoehdon.
     * @param optionInt vaihtoehdon indeksi
     * @return  vastausvaihtoehto
     */  
    public String getOption(int optionInt) {
        return this.currentQuestion.getOptions().get(optionInt).toString();
    }
  
    /**
     * Asettaa kysymyksen vastatuksi, jos siihen ei ole jo vastattu.
     * Palauttaa true vain jos vastaus on oikein ja siihen ei ole jo vastattu. 
     * Muuten palauttaa false.
     * @param answer pelaajan vastaus
     * @return false, jos on jo vastattu tai jos vastaus on väärin, muuten true.
     */
    public boolean answerQuestion(String answer) {
        if (this.currentQuestion.isAnswered() == true) {
            return false;
        } else {
            this.currentQuestion.setAnswered();
            if (isCorrect(answer) == true) {
                this.playersPoints += 10;
                return true;
            } else {
                return false;
            }
        }
    }
    
    /**
     * Tarkistaa onko vastaus oikein.
     * 
     */
    public boolean isCorrect(String answer) {
        String rigthAnswer = currentQuestion.getAnswer();
        if (answer.trim().matches(rigthAnswer)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Hakee oikean vastauksen. 
     * 
     */
    public String getCorrect() {
        String correct = currentQuestion.getAnswer();
        return correct;
    }   
    
    /**
     * Hakee seuraavan kysymyksen QuestionDaolta.
     */
    public void moveToNextQuestion() {
        this.currentQuestionIdx++;
        this.currentQuestion = questiondao.getQuestion(currentQuestionIdx);
        
    }
    
    public int getOptionSize() {
        return this.currentQuestion.getOptionsSize();
    }
    
    
    
}