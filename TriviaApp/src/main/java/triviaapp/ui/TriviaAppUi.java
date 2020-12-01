package triviaapp.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import triviaapp.dao.FileQuestionDao;
import triviaapp.dao.QuestionDao;
import triviaapp.domain.GameService;


public class TriviaAppUi extends Application{
    
    private GameService gameService;
    private Scene startScene;
    private Scene gameScene;
    private int i;
    private BorderPane borderPane;
    private Label resultText;
    
 
    
    @Override
    public void init() throws Exception{
       
        QuestionDao fileQuestion =new FileQuestionDao("questions.txt");
        gameService=new GameService(fileQuestion);
    }
    
    @Override
    public void start(Stage primaryStage){
                
        HBox startPane= new HBox(100);
      
        Button startButton=new Button("Start");
        
        startPane.getChildren().addAll(startButton);
        startScene=new Scene(startPane);
               
        Button buttonA=new Button();
        Button buttonB=new Button();
        Button buttonC=new Button();
        Button buttonD=new Button();
        i=-1;
        
        startButton.setOnAction(e->{
                               
            if (i>=gameService.getQuestionsSize()-1){
                HBox endPane=new HBox(10);
                endPane.getChildren().add(new Label("Game over!"));
                Scene endScene=new Scene(endPane);
                
                
                primaryStage.setScene(endScene);
                i=-1;
            }
            i++;  
            startButton.setText("Next question");
            BorderPane borderPane=new BorderPane();
            borderPane.setPrefSize(400, 300);
            GridPane options=new GridPane();
       
            Label questionText =new Label(gameService.getQuestionText(i));
            resultText=new Label("");
            borderPane.setTop(questionText);
            borderPane.setBottom(resultText);
            borderPane.setRight(startButton);
           

            
            questionText.setText(gameService.getQuestionText(i));
            buttonA.setText(gameService.getA(i));     
            buttonB.setText(gameService.getB(i));
            buttonC.setText(gameService.getC(i));
            buttonD.setText(gameService.getD(i));
   
            options.add(buttonA, 0, 0);
            options.add(buttonB, 1, 0);
            options.add(buttonC, 0, 1);
            options.add(buttonD, 1, 1);
            
            borderPane.setCenter(options);
            gameScene=new Scene(borderPane);
            primaryStage.setScene(gameScene);
           
        });
        
        buttonA.setOnAction(event -> {
            if(gameService.isCorrect(i, gameService.getA(i))){
                resultText.setText("Correct!");
                primaryStage.setScene(gameScene);
            }else {
                resultText.setText("Wrong! The correct answer is "+gameService.getCorrect(i));
                primaryStage.setScene(gameScene);
            }
        });
  
        buttonB.setOnAction(event -> {
            if(gameService.isCorrect(i, gameService.getB(i))){
                resultText.setText("Correct!");
                primaryStage.setScene(gameScene);
            }else {
                resultText.setText("Wrong! The correct answer is "+gameService.getCorrect(i));
                primaryStage.setScene(gameScene);
            }
        });
        
             
        buttonC.setOnAction(event -> {
            if(gameService.isCorrect(i, gameService.getC(i))){
                resultText.setText("Correct!");
                primaryStage.setScene(gameScene);
            }else {
                resultText.setText("Wrong! The correct answer is "+gameService.getCorrect(i));
                primaryStage.setScene(gameScene);
            }
        });
        
        buttonD.setOnAction(event -> {
            if(gameService.isCorrect(i, gameService.getD(i))){
                resultText.setText("Correct!");
                primaryStage.setScene(gameScene);
            }else {
                resultText.setText("Wrong! The correct answer is "+gameService.getCorrect(i));
                primaryStage.setScene(gameScene);
            }
        });
        
        primaryStage.setScene(startScene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
}
