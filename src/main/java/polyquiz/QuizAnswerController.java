package polyquiz;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(value="quiz_answer")
public class QuizAnswerController {
    @RequestMapping(value="/")
    public String getAllQuizAnswers() {
	return "Ohai";
    }
}
