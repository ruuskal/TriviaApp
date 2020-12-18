package domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import triviaapp.domain.GameService;
import triviaapp.domain.Player;
import triviaapp.dao.Question;

public class GameServiceTest {

    GameService gameService;
    Question questionZero;
    Question questionOne;
    Player player;
    FakePlayerDao playerDao;
    FakeQuestionDao questionDao;
    
    @Before
    public void setUp(){
        player = new Player();
        
        List <Question> questions = new ArrayList<>();
        List <String> optionsZero = new ArrayList<>();
        optionsZero.add("1");
        optionsZero.add("2");
        optionsZero.add("3");
        optionsZero.add("4");
        this.questionZero = new Question ("Which is 2", optionsZero, "2");
        ArrayList<String>optionsOne = new ArrayList <>();
        optionsOne.add("a");
        optionsOne.add("b");
        optionsOne.add("c");
        optionsOne.add("d");
        this.questionOne = new Question("Which is a", optionsOne, "a");
        
        List <String> scoreBoard = new ArrayList<>();
        this.playerDao = new FakePlayerDao(scoreBoard);
        this.questionDao = new FakeQuestionDao(questions);
                
        questions.add(questionZero);
        questions.add(questionOne);
        
        this.gameService = new GameService(questionDao, player, playerDao);
    }

    @Test
    public void getSortedScoreBoardSortsCorrectly() throws IOException {
        gameService.addScore("Riks", 30);
        gameService.addScore("Raks", 50);
        gameService.addScore("Poks", 10);
        List <Player> list = gameService.getSortedScoreBoard();
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
        gameService.moveToNextQuestion();
        assertEquals(false, gameService.areThereMoreQuestions());
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
        assertEquals(false, gameService.answerQuestion("2"));
    }
    
        
    @Test
        public void answerQuestionAddsPointsWhenRight() {
        gameService.answerQuestion("2");
        assertEquals(10, player.getPoints());
    }
    
    @Test
    public void answerQuestionDoesntAddPointsTwiceForSameAnswer() {
        gameService.answerQuestion("2");
        gameService.answerQuestion("2");
        assertEquals(10, gameService.getPoints());
    }
    
    @Test
    public void answerQuestionSetsQuestionAnswered() {
        gameService.answerQuestion("7");
        assertEquals(true, questionZero.isAnswered());
    }
    
}