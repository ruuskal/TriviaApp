package triviaapp.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import triviaapp.domain.Question;


public class QuestionDao {
    
    private String file;
    private List <Question> questions;
    
    public QuestionDao (String file) throws Exception{
        this.file=file;
        String content="";
        String answer="";
        this.questions=new ArrayList<>();
        
        Scanner reader = new Scanner(new File(this.file));
        
        while (reader.hasNextLine()){
             List <String> options=new ArrayList<>();
             
            String row=reader.nextLine();
            String[]parts=row.split(";");
            content=parts[0];
            System.out.println(content);
            answer=parts[2];
            System.out.println(answer);
            String [] optionParts=parts[1].split(",");
            
            for (int i=0;i<4;i++){
                options.add(optionParts[i]);
            }
     
            questions.add( new Question(content, options, answer));
            
        }
            
    
    }
    
    public List getQuestions(){
        return this.questions;
    }
    
    public int getQuestionSize(){
        return this.questions.size();
    }
    
}
