package triviaapp.ui;

import java.io.InputStream;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import triviaapp.dao.FileQuestionDao;
import triviaapp.domain.GameService;


public class TriviaAppUi extends Application{
    
    private GameService gameService;
    private Scene gameScene;
    private Scene startScene;
    private int currentQuestion;
     
    @Override
    public void init() throws Exception {
     
        InputStream input = TriviaAppUi.class.getResourceAsStream("/questions.txt");
        
        FileQuestionDao fileQuestion = new FileQuestionDao(input);
        gameService = new GameService(fileQuestion);
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        HBox startPane = new HBox(100);
      
        Button startButton = new Button("Start");
        
        startPane.getChildren().addAll(startButton);
        startScene = new Scene(startPane);
               
        currentQuestion = -1;
        startButton.setOnAction(e -> {
            
            if(gameService.isOver(currentQuestion)) {
                BorderPane endView = new BorderPane();
                endView.setCenter(new Label("Game Over! You got " + gameService.getPoints() + " points!"));
                
                Scene endScene=new Scene(endView);
                primaryStage.setScene(endScene);
             
            }
               currentQuestion++;
                               
               BorderPane gameView=new BorderPane();
               gameView.setPrefSize(300, 200);
               gameView.setPadding(new Insets(10, 10, 10, 10));
               gameView.setTop(new Label("Question: " + gameService.getNextQuestion(currentQuestion)));

               startButton.setText("Next!!");
               gameView.setRight(startButton);
               
               Label resultText =new Label("");
               
               GridPane options = new GridPane();  
               options.setVgap(10);
               options.setHgap(10);
               
                int optionIndex=0;  
            for(int x=0;x<2;x++){
                
                for(int y=0; y<2;y++){
                
                    Button optionButton=new Button();
                    options.add(optionButton, x, y);
                    optionButton.setText(gameService.getOption(currentQuestion, optionIndex));
                    
                    String correct =gameService.getCorrect(currentQuestion);
                 
                    optionButton.setOnAction(eh ->{
                        if(gameService.hasBeenAnswered(currentQuestion)==false){
                            if(gameService.isCorrect(optionButton.getText(), currentQuestion)){
                                resultText.setText("Correct!");
                            }else {
                                resultText.setText("Wrong! The correct answer is "+ correct);
                            }
                        }
                    });
                    optionIndex++;
                }
            }
            gameView.setBottom(resultText);
            gameView.setCenter(options);
            gameScene=new Scene (gameView);
            primaryStage.setScene(gameScene);
     
        });
        primaryStage.setScene(startScene);
        primaryStage.show();   
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
}
