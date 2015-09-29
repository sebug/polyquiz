(function () {
    'use strict';
    var viewModel = {
	quizAnswers: ko.observableArray([]),
	questions: ko.observableArray([])
    };

    ko.applyBindings(viewModel);
}());
