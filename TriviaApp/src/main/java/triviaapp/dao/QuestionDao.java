
package triviaapp.dao;

import triviaapp.domain.Question;

public interface QuestionDao {
    
    Question getQuestion(int i);
    
    int getQuestionSize();
    
}
