
package triviaapp.domain;


public class Question {
    
    private String content;
    private String options;
    private String answer;
    
    public Question(String content, String options, String answer){
        this.answer=answer;
        this.content=content;
        this.options=options;
    }
    
    public String getContent(){
        return this.content;
    }
    public String getOptions(){
        return this.options;
    }
    public String getAnswer(){
        return this.answer;
    }
}
