package triviaapp.ui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import triviaapp.domain.GameService;

public class GameStage extends Stage {
    
    GameService gameService;
  
    BorderPane gameView = new BorderPane();
    Button nextButton = new Button("Next!");
    GridPane options;
    
    
    public GameStage (GameService service) {
        
        this.gameService = service;
       
        gameView.setRight(nextButton);        
        gameView.setPrefSize(500, 200);
        gameView.setPadding(new Insets(10, 10, 10, 10));
        
        nextButton.fire();

        this.setScene(new Scene(gameView));
        this.show();
        
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent e) {
                boolean gameOver = gameService.areThereMoreQuestions();
                if (gameOver == false) {
                    
                    new EndStage(gameService);
                    stop();

                    
                } else if (gameOver == true ) {
                
                    gameService.moveToNextQuestion();
                    gameView.setCenter(setQuestion());
                        
                }
            }
        });
    }
    
    
    public void stop() {
        this.close();
    }
    
    public GridPane setQuestion() {
        
        options = new GridPane();
        options.setVgap(10);
        options.setHgap(10);
        gameView.setCenter(options);
        
        Label question = new Label(gameService.getContent());
        Label resultText = new Label();
        options.addRow(0, question);
        options.addRow(5, resultText);
        SimpleBooleanProperty isDisabled = new SimpleBooleanProperty();
        
        for (int i = 0; i < 4; i++) {

            String optionText = gameService.getOption(i);
            Button option = new Button(optionText);
            options.addRow(i+1, option);
            option.disableProperty().bind(isDisabled);
            
            option.setOnAction(eh ->{
                isDisabled.setValue(true);
                if(gameService.answerQuestion(optionText) == true) {
                    resultText.setText("Correct!");
                } else {
                        resultText.setText("Wrong! The correct answer is " + gameService.getCorrect() );
                    }
                
            });
            
        }
                
        return this.options;
    }
   
}
