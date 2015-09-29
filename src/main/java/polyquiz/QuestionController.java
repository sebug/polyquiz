package polyquiz;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

import polyquiz.entities.Question;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

@RestController
@RequestMapping(value="question")
public class QuestionController {
    @Autowired
    private QuestionStoreBean questionStoreBean;

    private Log log = LogFactory.getLog(QuestionController.class);

    @RequestMapping(value="/")
    public List<Question> getAllQuestions() {
	log.info("Calling getAllQuestions");
	return this.questionStoreBean.getAllQuestions();
    }

    @RequestMapping(value="/create")
    public Question create(@RequestBody Question content) {
	log.info(content);
	this.questionStoreBean.storeQuestion(content);
	return content;
    }
}
