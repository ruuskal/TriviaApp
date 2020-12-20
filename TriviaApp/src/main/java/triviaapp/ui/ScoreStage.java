package triviaapp.ui;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Sisältää listankymän pistetilastosta.
 * 
 */

public class ScoreStage extends Stage {
    
    BorderPane pane = new BorderPane();
    
    Button menuButton = new Button();
    
    public ScoreStage(List scoreBoard, String text) {
                    
        menuButton.setText(text);
        menuButton.setOnAction(a -> {
            this.close();
        });
                    
        ObservableList<String> list = FXCollections.observableArrayList(scoreBoard);
        ListView<String> listView = new ListView<>(list);
                    
        pane.setRight(menuButton);
        pane.setCenter(listView);
        pane.setTop(new Label("Scoreboard"));
        this.setScene(new Scene(pane));
        this.show();
    }
}
