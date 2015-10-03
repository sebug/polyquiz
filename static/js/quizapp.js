(function () {
    'use strict';
    var getRequest,
	fetchQuizAnswers,
	fetchQuestions,
	viewModel,
	attachFaveletLink;

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

    attachFaveletLink = function (answer) {
	var invocation;
	answer.faveletLink = ko.observable('');
	invocation = new XMLHttpRequest();
	invocation.open('GET', '/quiz_answer/favelet/' + answer.id, true);
	invocation.onload = function () {
	    answer.faveletLink('javascript:' + this.responseText);
	};
	invocation.send();
    };

    fetchQuizAnswers(function (answers) {
	var i;
	for (i = 0; i < answers.length; i += 1) {
	    attachFaveletLink(answers[i]);
	}
	viewModel.quizAnswers(answers);
    });

    fetchQuestions(function (questions) {
	viewModel.questions(questions);
    });

    ko.applyBindings(viewModel);
}());
