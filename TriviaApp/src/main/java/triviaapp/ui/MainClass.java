
package triviaapp.ui;

import java.util.Scanner;
import triviaapp.dao.QuestionDao;
import triviaapp.domain.GameService;


public class MainClass {

   
    public static void main(String[] args) throws Exception {
        
        Scanner sc=new Scanner (System.in);
        QuestionDao questiondao=new QuestionDao("questions.txt");
        GameService gameService=new GameService(questiondao);
        
        int size=questiondao.getQuestionSize();
        
        for(int i=0;i<size;i++){
            System.out.println(gameService.getContent(i));
            System.out.println(gameService.getOptions(i));
            String answer=sc.nextLine();
            if (gameService.isCorrect(i, answer)==true){
                System.out.println("Correct!");
            }else{
                System.out.println("Wrong! The answer is "+gameService.getCorrect(i));
            }
            System.out.println("-------------");
            
        }
    }
    
}
