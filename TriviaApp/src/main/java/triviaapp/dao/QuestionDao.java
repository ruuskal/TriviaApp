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
        String options="";
        String answer="";
        this.questions=new ArrayList<>();
        
        Scanner reader = new Scanner(new File(this.file));
        
        while (reader.hasNextLine()){
            
        String row=reader.nextLine();
        String[]parts=row.split(";");
        content=parts[0];
        answer=parts[2];
        options=parts[1];
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
