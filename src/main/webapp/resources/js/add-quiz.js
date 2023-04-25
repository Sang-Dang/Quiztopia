$(document).ready(function () {
    $(".container").on("click", ".create-answer", function()  {
        var $answerContainer = $('<div class="answer-container"></div>');
        var questionNumber = $(this).parent().attr("data-index");
        var answerNumber = $(this).closest('.question-container').find('.answer-container').length + 1;
        var $checkbox = $('<input type="checkbox" name="answer" value="' + 'question_' + questionNumber + '_answer_' + answerNumber + '">');
        var $textInput = $('<input type="text" name="question_' + questionNumber + '_answer_' + answerNumber + '" placeholder="Answer text">');
        $answerContainer.append($checkbox, $textInput);
        $(this).before($answerContainer);
    });

    $(".container").on("click", ".delete-answer", function() {
        $(this).closest('.question-container').find(".answer-container").last().remove();
    });

    var index = $('question-container').index(); // set initial data-index value

    $(".container").on("click", ".create-question", function() {
        index++; // increment data-index value
        var newQuestion = '<div class="question-container" data-index="' + index + '">' +
                'Question:<textarea  name="question" rows="1" cols="50"></textarea></br>' +
                '<div class="create-answer">Create Answer</div>' +
                '<div class="delete-answer">Delete Answer</div>' +
                '</div>';
        $(newQuestion).insertBefore(this); // insert new question container before the Create Question button
        $('.numQuestions').attr('value', index);
    });
    
    $(".container").on("click", ".delete-question", function() {
        index--;
        if(index < -1) {
            index = -1;
        }
        $(this).parent().children('.question-container').last().remove();
        $('.numQuestions').attr('value', index);
    });
});