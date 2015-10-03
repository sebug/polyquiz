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
import java.util.ArrayList;
import java.util.HashSet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;

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

    @RequestMapping(value="/duplicates/")
    public List<String> getDuplicates() {
	List<String> duplicateNames = new ArrayList<String>();
	HashSet<String> names = new HashSet<String>();
	List<QuizAnswer> allAnswers = this.quizAnswerStoreBean.getAllQuizAnswers();
	for (QuizAnswer answer : allAnswers) {
	    if (names.contains(answer.getFullName())) {
		duplicateNames.add(answer.getFullName());
	    } else {
		names.add(answer.getFullName());
	    }
	}

	return duplicateNames;
    }

    @RequestMapping(value="/favelet/{id}")
    public String getFavelet(@PathVariable String id) throws JsonProcessingException, UnsupportedEncodingException {
	log.info("Calling getFavelet " + id);
	QuizAnswer qa = this.quizAnswerStoreBean.getQuizAnswerById(id);
	ObjectMapper mapper = new ObjectMapper();
	String jsonString = mapper.writeValueAsString(qa);
	
	return URLEncoder.encode(faveletPlaceholder.replace("%%ENTRY%%", jsonString), "UTF-8");
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

    private String faveletPlaceholder = "(function () {" +
	"    'use strict';" +
	"    var mainFrame = document.getElementsByTagName('frame')[1]," +
	"	tr," +
	"	trs," +
	"	i," +
	"	j," +
	"	q," +
	"	a," +
	"	selectedQuestion," +
	"	questionText," +
	"	answerText," +
	"	inputs," +
	"	nameInput," +
	"	confirmName," +
	"	auswertenButton," +
	"	entry = %%ENTRY%%;" +
	"" +
	"    inputs = mainFrame.contentDocument.getElementsByTagName('input');" +
	"" +
	"    if (inputs.length > 1 && inputs[0].type === 'text') {" +
	"	nameInput = inputs[0];" +
	"	confirmName = inputs[1];" +
	"    }" +
	"" +
	"    if (mainFrame.contentDocument.getElementsByTagName('b').length > 0) {" +
	"	questionText = mainFrame.contentDocument.getElementsByTagName('b')[0].innerText;" +
	"    }" +
	"" +
	"    // Find answer" +
	"    selectedQuestion = undefined;" +
	"    for (i = 0; i < entry.questionAnswers.length && !selectedQuestion; i += 1) {" +
	"	q = entry.questionAnswers[i];" +
	"	if (q.question === questionText) {" +
	"	    selectedQuestion = q;" +
	"	}" +
	"    }" +
	"" +
	"    if (selectedQuestion) {" +
	"	console.log(\"Found question\");" +
	"	trs = mainFrame.contentDocument.getElementsByTagName('tr');" +
	"	for (i = 0; i < trs.length; i += 1) {" +
	"	    tr = trs[i];" +
	"	    answerText = tr.getElementsByTagName('td')[1].innerText.replace(/\\./g,'');" +
	"	    for (j = 0; j < selectedQuestion.answer.length; j += 1) {" +
	"		a = selectedQuestion.answer[j];" +
	"		a = a.replace(/\\./g, '');" +
	"		if (a === answerText) {" +
	"		    tr.getElementsByTagName('td')[0].getElementsByTagName('input')[0].click();" +
	"		}" +
	"	    }" +
	"	}" +
	"" +
	"	inputs = mainFrame.contentDocument.getElementsByTagName('input');" +
	"	for (i = 0; i < inputs.length; i += 1) {" +
	"	    if (inputs[i].value === \" Evaluer \") {" +
	"		inputs[i].click();" +
	"	    }" +
	"	}" +
	"    }" +
	"" +
	"    if (nameInput && confirmName) {" +
	"	nameInput.value = entry.fullName;" +
	"	confirmName.click();" +
	"    }" +
	"}());";
}
