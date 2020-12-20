package domain;

import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import triviaapp.domain.GameService;
import triviaapp.domain.Player;

public class GameServiceTest {

    GameService gameService;
    FakePlayerDao playerDao;
    FakeQuestionDao questionDao;
    
    @Before
    public void setUp(){
        
        this.questionDao = new FakeQuestionDao();
        this.playerDao = new FakePlayerDao();
        this.gameService = new GameService(questionDao, playerDao);
        gameService.moveToNextQuestion();
    }

    @Test
    public void getSortedScoreBoardSortsCorrectly() throws IOException {
        gameService.addScore("Riks", 30);
        gameService.addScore("Raks", 50);
        gameService.addScore("Poks", 10);
        List<Player> list = gameService.getSortedScoreBoard();
        assertEquals(50, list.get(0).getPoints());
        assertEquals(30, list.get(1).getPoints());
        assertEquals(10, list.get(2).getPoints());
    }
    
    
    @Test
    public void playerHasZeroPointsAtFirst() {
        assertEquals(0, gameService.getPoints());
    }
    
    @Test
    public void firstQuestionIsAskedFirst() {
        assertEquals("Which is 2", gameService.getContent());
    }
    
    @Test
    public void gameEndsWhenQuestionsEnd() {
        gameService.moveToNextQuestion();
        assertEquals(false, gameService.areThereMoreQuestions());
    }
    
    @Test
    public void gameDoesNotEndBeforeLastQuestion() {
        assertEquals(true, gameService.areThereMoreQuestions());
    }
    
    @Test
    public void gameShowsAllOptions() {
        assertEquals("1", gameService.getOption(0));
        assertEquals("2", gameService.getOption(1));
        assertEquals("3", gameService.getOption(2));
        assertEquals("4", gameService.getOption(3));
        
    }
   
    @Test
    public void onlyMovesToSecondQuestionWhenCalledToDoSo() {
        gameService.getContent();
        gameService.getCorrect();
        gameService.isCorrect("test");
        assertEquals("Which is 2", gameService.getContent());
        gameService.moveToNextQuestion();
        assertEquals("Which is a", gameService.getContent());
    }
    
    @Test
    public void answerQuestionDosNotLetAnswerSameQuestionTwice() {
        assertEquals(true, gameService.answerQuestion("2"));
    }
    
        
    @Test
        public void answerQuestionAddsPointsWhenRight() {
        gameService.answerQuestion("2");
        assertEquals(10, gameService.getPoints());
    }
    
    @Test
    public void answerQuestionDoesntAddPointsTwiceForSameQuestion() {
        gameService.answerQuestion("2");
        gameService.answerQuestion("2");
        assertEquals(10, gameService.getPoints());
    }
    
    @Test
    public void newPlayerIsInScoreBoard() {
        try {
            gameService.addScore("Tester", 50);
        } catch (IOException ex) {
            System.out.println("Exception " + ex);
        }
        List <Player> list = gameService.getSortedScoreBoard();
        Player best = list.get(0);
        assertEquals(50, best.getPoints());
    }
}