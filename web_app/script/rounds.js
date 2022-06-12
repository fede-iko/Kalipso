var game = null;

function getData() {

    $.ajax({
        url: "http://localhost:8080/sentence",
        method: "GET",
        success: function(response) {
            game = new Game(response);

            showSentence();
        }
    });

}

function game_start() {

    getData();

    $("#next-btn-container").append("<button id='next-btn'>Next</button>");

    $("#next-btn").on("click", function() {
        game.currentSentence++;
        showSentence();
    });
}



function showSentence() {

    if (game.currentSentence >= game.sentences.length) {
        $("#next-btn-container").remove();
        $("#round-container").text("You won!");
        return;
    }

    var round = "<p>ROUND " + (game.currentSentence + 1) + "</p>";

    var sentenceText = "<p>" + game.sentences[game.currentSentence].sentence_text + "</p>";

    var answers = game.sentences[game.currentSentence].answers;
    var answersTexts = "";

    answers.forEach(function(answer) {
        answersTexts += "<p>" + answer.answer_text + "</p>";
    });

    $("#contents-container").empty();
    $("#contents-container").append(round + sentenceText + answersTexts);

}