
package triviaapp.domain;

import java.util.List;

/**
 * Kysymystä kuvaava luokka
 */
public class Question {
    
    private String content;
    private List<String> options;
    private String answer;
    private boolean answered;
    
    public Question(String content, List options, String answer) {
        this.answer = answer;
        this.content = content;
        this.options = options;
        this.answered = false;
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
    public void setAnswered() {
        this.answered = true;
    }
    
    public boolean isAnswered() {
        return this.answered;
    }
    
    public int getOptionsSize() {
        return this.options.size();
    }
}
