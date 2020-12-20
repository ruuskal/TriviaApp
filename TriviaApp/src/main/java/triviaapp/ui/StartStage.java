package triviaapp.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import triviaapp.domain.GameService;

/**
 * Sisältää alkunäkymän.
 * 
 */

public class StartStage extends Stage {
     
    private GameService gameService;
    
    BorderPane startPane = new BorderPane();    
    HBox box = new HBox(20);
    Label label = new Label("Welcome to TriviaApp!");
    Button startButton = new Button("Start");
    Button scoreButton = new Button("Show scoreboard");
    Button exitButton = new Button("Exit TriviaApp");
        
    public StartStage (GameService service) {
            
        this.gameService = service;
            
        box.getChildren().add(startButton);
        box.setAlignment(Pos.CENTER);
        box.getChildren().add(scoreButton);
        startPane.setCenter(box);
        startPane.setTop(label);
        startPane.setPrefSize(400, 200);
        this.setScene(new Scene(startPane));
        this.show();
            
        startButton.setOnAction(new EventHandler<ActionEvent>() {
               
            /**
             * Siirtyy pelinäkymään ja sulkeutuu.
             */
            @Override
            public void handle(ActionEvent e) {
               
                new GameStage(gameService);
                stop();
                } 
            });
            
        scoreButton.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Siirtyy pistetilasto-näkymään, nykyinen ikkuna ei sulkeudu.
             */   
            @Override
            public void handle(ActionEvent e) {
                
                new ScoreStage(gameService.getSortedScoreBoard(), "Back to menu");  
                }
            });
         
        exitButton.setOnAction(eh -> {
            stop(); 
        });   
    }
        
        public void stop() {
            this.close();
        }
    
}
