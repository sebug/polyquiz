(function () {
    'use strict';
    var getRequest,
	fetchQuizAnswers,
	fetchQuestions,
	viewModel;

    getRequest = function (url) {
	return function (andThen) {
	    var invocation = new XMLHttpRequest();
	    invocation.open('GET', url, true);
	    invocation.setRequestHeader('Content-Type', 'application/json');
	    if (typeof andThen === 'function') {
		invocation.onload = function () {
		    andThen(JSON.parse(this.responseText));
		};
	    }
	    invocation.send();
	};
    };

    fetchQuizAnswers = getRequest('/quiz_answer/');
    fetchQuestions = getRequest('/question/');
    
    viewModel = {
	quizAnswers: ko.observableArray([]),
	questions: ko.observableArray([])
    };

    fetchQuizAnswers(function (answers) {
	viewModel.quizAnswers(answers);
    });

    fetchQuestions(function (questions) {
	viewModel.questions(questions);
    });

    ko.applyBindings(viewModel);
}());
