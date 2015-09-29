package polyquiz.entities;

public class Question {
    private String id;
    private String question;
    private Answer[] answers;

    public Question() {
    }

    public Question(String question, Answer[] answers) {
	this.question = question;
	this.answers = answers;
    }

    public String getId() {
	return id;
    }

    public String getQuestion() {
	return question;
    }

    public void setQuestion(String question) {
	this.question = question;
    }

    public Answer[] getAnswers() {
	return this.answers;
    }

    public void setAnswers(Answer[] answers) {
	this.answers = answers;
    }

    @Override
    public String toString() {
	return "Question [id=" + id + ", question=" + question +
	    ", answers=" + answers + "]";
    }
}
