//IT PUT IN THE HTML THE LOADING 'SCREEN' SINCE THE DATA IS FETCHING
$(".container").append("<div id='loading-container'><p>Loading...</p></div>");

var sentencesContainer = null;

//FETCH DATA AND PUT IN THE GAME OBJECT
function getData() {

    $.ajax({
        url: "http://localhost:8080/sentence",
        method: "GET",
        async: false,
        success: function(response) {
            sentencesContainer = createSentences(response);
        },
        //IF THERE IS AN ERROR, IT'S SHOWED IN THE SCREEN
        error: function() {
            $(".container").text("Application error! Please try later.");
        },
        //WHEN THE DATA IS FETCHED, THE LOADING SCREEN IS REMOVED AND THE GAME IS STARTED
        complete: function() {
            //IF THERE IS AN ERROR, THE GAME IS NOT STARTED
            if (sentencesContainer != null) {
                $("#loading-container").remove();
                sentencesContainer.shuffleSentences();
                sentencesContainer.shuffleAnswers();
                showSentence();
            }
        }

    });

}

//CREATE SENTENCES FROM THE JSON DATA
function createSentences(response) {
    var sentences = [];
    for (var i = 0; i < response.length; i++) {
        sentences.push(new Sentence(response[i].id_sentence, response[i].sentence_text, createAnswers(response[i].answers)));
    }
    return new SentencesContainer(sentences);
}

//CREATE ANSWERS FROM THE JSON DATA
function createAnswers(answers) {
    var answersArray = [];
    for (var i = 0; i < answers.length; i++) {
        answersArray.push(new Answer(answers[i].id_answer, answers[i].answer_text, answers[i].correct));
    }
    return answersArray;
}

$(document).ready(function() {
    game_start();
});

//GAME START
function game_start() {

    getData();

    $("#next-btn-container").append("<button id='next-btn'>NEXT</button>");

    $("#next-btn").on("click", function() {
        sentencesContainer.currentSentence++;
        showSentence();
    });
}

//SHOW SENTENCE, IT'S CALLED EVERYTIME THE NEXT BTN IS PRESSED
function showSentence() {

    if (sentencesContainer.reachedEnd()) {
        $("#next-btn-container").remove();
        $("#round-container").text("You won!");
        return;
    }

    var round = "<p>ROUND " + (sentencesContainer.currentSentence + 1) + "</p>";

    var sentenceText = "<p>" + sentencesContainer.sentences[sentencesContainer.currentSentence].sentenceText + "</p>";

    var answers = sentencesContainer.sentences[sentencesContainer.currentSentence].answers;
    var answersTexts = "";

    answers.forEach(function(answer) {
        answersTexts += "<p>" + answer.answerText + "</p>";
    });

    $("#contents-container").empty();
    $("#contents-container").append(round + sentenceText + answersTexts);

}