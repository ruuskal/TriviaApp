package triviaapp.ui;

import java.io.IOException;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import triviaapp.domain.GameService;

/**
 * Sisältää pelin loppunäkymän.
 * 
 */
public class EndStage extends Stage { 
    
    GameService gameService;
    Button addButton = new Button("Add my name to scoreboard!");
    Button exitButton = new Button("Close TriviaApp");
    TextField field = new TextField();    
    HBox box = new HBox(30);
    BorderPane endView = new BorderPane();
 
    
    public EndStage(GameService service) {
                
        this.gameService = service;

        endView.setPrefSize(500, 200);
        endView.setPadding(new Insets(10, 10, 10, 10));
        endView.setCenter(field);
        box.getChildren().add(addButton);
        box.getChildren().add(exitButton);
        
        
        int points = gameService.getPoints();
        Label label = new Label();
        endView.setRight(label);
        endView.setTop(new Label("Game Over! You got " + points + " points!"));
        endView.setBottom(box);
        
        this.setScene(new Scene(endView));
        this.show();
        
        addButton.setOnAction(a -> {
            /**
             * Ottaa parametreikseen käyttäjän syöttämän nimen ja lisää pistetilastoon.
             */
            try {
                String name = field.getText();
                if (name.length() > 20 || name.isEmpty() || name.contains(";")) {
                    field.clear();
                   label.setText("Insert a name with 1-20 characters (';' not allowed).");
                } else {
                    this.gameService.addScore(field.getText(), points);
                    List list = gameService.getSortedScoreBoard();
                    new ScoreStage(list, "Close TriviaApp");
                    this.stop();       
                }
            } catch (IOException ex) {
                System.out.println("Exception " + ex);
            } 
        });    
        
        exitButton.setOnAction(a -> {
            this.stop();
        });
    }
    
    public void stop() {
        this.close();
    }
    
}
