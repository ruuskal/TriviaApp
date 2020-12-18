package triviaapp.ui;

import java.io.IOException;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import triviaapp.domain.GameService;


public class EndStage extends Stage { 
    
    GameService gameService;
    Button addButton = new Button("Add my name to scoreboard!");
    TextField field = new TextField();    
    BorderPane endView = new BorderPane();
 
    
    public EndStage(GameService service) {
                
        this.gameService = service;

        endView.setPrefSize(500, 200);
        endView.setPadding(new Insets(10, 10, 10, 10));
        endView.setCenter(field);
        endView.setBottom(addButton);
        int points = gameService.getPoints();
        Label label = new Label();
        endView.setRight(label);
        endView.setTop(new Label("Game Over! You got " + points + " points!"));
        
        this.setScene(new Scene(endView));
        this.show();
        
        addButton.setOnAction(a -> {
            try {
                String name = field.getText();
                if (name.length() > 20 || name.isEmpty() || name.contains(";")) {
                    field.clear();
                   label.setText("Insert a name with 1-20 letters or numbers");
                } else {
                    this.gameService.addScore(field.getText(), points);
                    this.close();
                }
            } catch (IOException ex) {
                System.out.println("Exception " + ex);
            }
            
        });
        
    }
    
}
