package triviaapp.ui;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import triviaapp.domain.GameService;

public class StartStage extends Stage {
     
    private GameService gameService;
    
        BorderPane startPane = new BorderPane();
        
        HBox box = new HBox(20);
        Label label = new Label("Welcome to TriviaApp!");
        Button startButton = new Button("Start");
        Button scoreButton = new Button("Show scoreboard");

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
                
                @Override
                public void handle(ActionEvent e) {
                    new GameStage(gameService);
                    stop();
                }
            });
            
            scoreButton.setOnAction(new EventHandler<ActionEvent>() {
                
                @Override
                public void handle(ActionEvent e) {
                    
                    BorderPane pane = new BorderPane();
                    Stage st = new Stage();
                    
                    List <String> l = gameService.getSortedScoreBoard();
                    
                    ObservableList<String> list = FXCollections.observableArrayList(l);
                    ListView <String> listView = new ListView<>(list);
                    
                    pane.setCenter(listView);
                    pane.setTop(new Label("Scoreboard"));
                    st.setScene(new Scene(pane));
                    st.show();
                    
                }
            });
        }
        
        public void stop() {
            this.close();
        }
    
}
