package polyquiz;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.data.mongodb.core.query.Query;

import polyquiz.entities.QuizAnswer;
import java.util.List;

@Component
public class QuizAnswerStoreBean {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public QuizAnswerStoreBean(MongoTemplate mongoTemplate) {
	this.mongoTemplate = mongoTemplate;
    }

    public List<QuizAnswer> getAllQuizAnswers() {
	return this.mongoTemplate.findAll(QuizAnswer.class);
    }

    public List<QuizAnswer> getQuizAnswersByFullName(String fullName) {
	return this.mongoTemplate.find(new Query(where("fullName").is(fullName)), QuizAnswer.class);
    }

    public QuizAnswer getQuizAnswerById(String id) {
	return this.mongoTemplate.findById(id, QuizAnswer.class);
    }

    public void storeQuizAnswer(QuizAnswer qa) {
	this.mongoTemplate.insert(qa);
    }
}
