package polyquiz;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

import polyquiz.entities.QuizAnswer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

@RestController
@RequestMapping(value="quiz_answer")
public class QuizAnswerController {
    @Autowired
    private QuizAnswerStoreBean quizAnswerStoreBean;

    private Log log = LogFactory.getLog(QuizAnswerController.class);
    
    @RequestMapping(value="/")
    public List<QuizAnswer> getAllQuizAnswers() {
	log.info("Calling getAllQuizAnswers");
	return this.quizAnswerStoreBean.getAllQuizAnswers();
    }

    @RequestMapping(value="/create")
    public QuizAnswer create(@RequestBody QuizAnswer content) {
	log.info(content);
	this.quizAnswerStoreBean.storeQuizAnswer(content);
	return content;
    }
}
