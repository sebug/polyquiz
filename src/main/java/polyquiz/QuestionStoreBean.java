package polyquiz;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.data.mongodb.core.query.Query;

import polyquiz.entities.Question;
import java.util.List;

@Component
public class QuestionStoreBean {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public QuestionStoreBean(MongoTemplate mongoTemplate) {
	this.mongoTemplate = mongoTemplate;
    }

    public List<Question> getAllQuestions() {
	return this.mongoTemplate.findAll(Question.class);
    }

    public void storeQuestion(Question q) {
	this.mongoTemplate.insert(q);
    }
}
