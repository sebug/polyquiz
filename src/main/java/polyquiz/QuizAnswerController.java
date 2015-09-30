package polyquiz;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value="/byid/{id}")
    public QuizAnswer getQuizAnswerById(@PathVariable String id) {
	log.info("Calling getQuizAnswerById " + id);
	return this.quizAnswerStoreBean.getQuizAnswerById(id);
    }

    @RequestMapping(value="/byname/{fullname}")
    public List<QuizAnswer> getQuizAnswersByFullName(@PathVariable String fullname) {
	log.info("Calling getQuizAnswersByFullName " + fullname);
	return this.quizAnswerStoreBean.getQuizAnswersByFullName(fullname);
    }

    @RequestMapping(value="/create")
    public QuizAnswer create(@RequestBody QuizAnswer content) {
	log.info(content);
	List<QuizAnswer> existingEntries = this.quizAnswerStoreBean.getQuizAnswersByFullName(content.getFullName());
	if (existingEntries.size() > 0) {
	    log.info("Already found an entry.");
	    return existingEntries.get(0);
	}
	this.quizAnswerStoreBean.storeQuizAnswer(content);
	return content;
    }
}
