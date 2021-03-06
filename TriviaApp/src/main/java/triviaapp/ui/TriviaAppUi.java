package triviaapp.ui;


import java.io.InputStream;
import java.util.Properties;
import javafx.application.Application;
import javafx.stage.Stage;
import triviaapp.dao.FileQuestionDao;
import triviaapp.dao.FilePlayerDao;
import triviaapp.domain.GameService;

public class TriviaAppUi extends Application {
    
    private GameService gameService;
     
    /**
     * Alustaa GameService-olion.
     */
    @Override
    public void init() throws Exception {
         
        Properties properties = new Properties();
        InputStream stream = getClass().getClassLoader().getResourceAsStream("config.properties");
        properties.load(stream);
        String topPlayersFile = properties.getProperty("players");
        FilePlayerDao playerdao = new FilePlayerDao(topPlayersFile);
        
        InputStream input = TriviaAppUi.class.getResourceAsStream("/questions.txt");
        FileQuestionDao fileQuestion = new FileQuestionDao(input);
        gameService = new GameService(fileQuestion, playerdao);
    }
    
    @Override
    public void start(Stage primaryStage) {
      new StartStage(this.gameService);
    }
  
    public static void main(String[] args) {
        Application.launch(args);
    }
}
