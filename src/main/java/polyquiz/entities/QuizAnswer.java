package polyquiz.entities;

public class QuizAnswer {
    private String id;
    private String fullName;
    private QuestionAnswer[] questionAnswers;

    public QuizAnswer() {
    }

    public QuizAnswer(String fullName, QuestionAnswer[] questionAnswers) {
	this.fullName = fullName;
	this.questionAnswers = questionAnswers;
    }

    public String getId() {
	return id;
    }

    public String getFullName() {
	return this.fullName;
    }

    public void setFullName(String fullName) {
	this.fullName = fullName;
    }

    public QuestionAnswer[] getQuestionAnswers() {
	return this.questionAnswers;
    }

    public void setQuestionAnswers(QuestionAnswer[] questionAnswers) {
	this.questionAnswers = questionAnswers;
    }

    @Override
    public String toString() {
	return "QuizAnswer [id=" + id + ", fullName=" + fullName +
	    ", questionAnswers=" + questionAnswers + "]";
    }
}
