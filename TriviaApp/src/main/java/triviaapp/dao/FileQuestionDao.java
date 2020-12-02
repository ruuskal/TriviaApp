package triviaapp.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import triviaapp.domain.Question;


public class FileQuestionDao implements QuestionDao {
    
    private String file;
    private List<Question> questions;
    
    public FileQuestionDao(String file) {
        this.file = file;
        this.questions = new ArrayList<>();
        
       
    try 
    {
        Scanner reader = new Scanner(new File(this.file));
        
        while (reader.hasNextLine()) {
            List<String> options = new ArrayList<>();
             
            String row = reader.nextLine();
            String [] parts = row.split(";");
            String content = parts[0];
            String answer = parts[2];
            String [] optionParts = parts[1].split(",");
            
            for (int i = 0; i < 4; i++) {
                options.add(optionParts[i]);
            }
            questions.add(new Question(content, options, answer));
        }
    } catch (Exception e){
        System.out.println("Exception: " + e.getMessage());
    }
    }
    
    public List getQuestions() {
        return this.questions;
    }
    
    @Override
    public int getQuestionSize() {
        return this.questions.size();
    }
    
    @Override
    public Question getQuestion(int i) {
        return questions.get(i);
    }
    
    
    
}
