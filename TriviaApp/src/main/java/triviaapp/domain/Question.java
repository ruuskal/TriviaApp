
package triviaapp.domain;

import java.util.List;


public class Question {
    
    private String content;
    private List<String> options;
    private String answer;
    
    public Question(String content, List options, String answer) {
        this.answer = answer;
        this.content = content;
        this.options = options;
    }
    
    public String getContent() {
        return this.content;
    }
    public List getOptions() {
        return this.options;
    }
    public String getAnswer() {
        return this.answer;
    }
}
