//CONTAINER FOR SENTENCES LOADED FROM API
var sentencesContainer = null;

//ALL THE USER ANSWERS
var userAnswers = {};

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
            $msg = "Connection error! Please try again later.";
            $("#loading-container").html($msg);
            throw new Error($msg);
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
        answersArray.push(new Answer(answers[i].id_answer, answers[i].answer_text));
    }
    return answersArray;
}

$(document).ready(function() {
    game_start();
});

//CREATE NEXT BTN AND PREV BTN CLICK EVENTS
function createNextPrevBtnEvents() {
    $("#btnNext").unbind().on("click", function() {
        if (!isAnswered()) {
            alert("Devi selezionare una risposta!");
            return;
        }
        sentencesContainer.currentSentence++;
        showSentence();
    });

    $("#btnPrev").unbind().on("click", function() {
        sentencesContainer.currentSentence--;
        showSentence();
    });
}

//ANSWER SELECT EVENT HANDLER
function answerSelectEventHandler() {
    $("#contents-container").unbind();
    for (let i = 1; i < 5; i++) {
        $("#contents-container").on("click", "#btn" + i, function() {
            this.classList.add("answer_selected");
            var actualSentenceText = sentencesContainer.sentences[sentencesContainer.currentSentence].sentenceText;
            var actualAnswer = sentencesContainer.sentences[sentencesContainer.currentSentence].answers[i - 1];
            userAnswers[actualSentenceText] = [actualAnswer.id, actualAnswer.answerText];

            //SAVE IN SESSION
            sessionStorage.setItem("userAnswers", JSON.stringify(userAnswers));

            //TOGGLE THE BUTTONS
            for (var j = 1; j < 5; j++) {
                if (j != this.id.substring(3)) {
                    $("#btn" + j).removeClass("answer_selected");
                }
            }
        });
    }
}

//GET ANSWERS HTML FROM THE ANSWERS ARRAY
function getAnswersHTML(answers) {
    var answersTexts = "";
    var btnCount = 1;
    answers.forEach(function(answer) {
        var selectedClass = "";
        if (isAnswered()) {
            selectedClass = userAnswers[sentencesContainer.sentences[sentencesContainer.currentSentence].sentenceText][0] == answer.answerText ? "answer_selected" : "";
        }
        answersTexts += "<div class='btn" + btnCount + "'><button id='btn" + btnCount + "' class='btn-rounds " + selectedClass + "'>" + answer.answerText + "</button></div>";
        btnCount++;
    });
    return answersTexts;
}

//GAME START
function game_start() {

    //IF THE USERANSWERS ARE NOT LOADED IN THE SESSION, IT MEANS THAT IS A NEW GAME
    if (!sessionStorage.getItem("userAnswers")) {
        sentencesContainer = null;
        userAnswers = {}
        getData();
        sessionStorage.setItem("sentencesContainer", JSON.stringify(sentencesContainer));
    } else {
        var sessionSentencesContainer = JSON.parse(sessionStorage["sentencesContainer"]);
        var sessionUserAnswers = JSON.parse(sessionStorage["userAnswers"]);

        userAnswers = sessionUserAnswers;
        sentencesContainer = new SentencesContainer(sessionSentencesContainer['sentences']);
        sentencesContainer.currentSentence = sessionSentencesContainer["currentSentence"];

        $("#loading-container").remove();
        showSentence();
    }

    $("#btnNext").show();

    createNextPrevBtnEvents();

    answerSelectEventHandler();

    //KEYBOARD ARROW CLICK AND ENTER LISTENERS
    $(document).unbind().on("keydown", function(e) {
        if (e.keyCode == 39 || e.keyCode == 13) {
            if (isAnswered()) {
                sentencesContainer.currentSentence++;
                showSentence();
            } else {
                alert("Devi selezionare una risposta!");
                return;
            }
        }
        if (e.keyCode == 37 && sentencesContainer.currentSentence > 0) {
            sentencesContainer.currentSentence--;
            showSentence();
        }
    });
}

//RETURN TRUE IF THIS ROUND IS ANSWERED
function isAnswered() {
    return userAnswers[sentencesContainer.sentences[sentencesContainer.currentSentence].sentenceText] != undefined;
}

//SHOW SENTENCE, IT'S CALLED EVERYTIME THE NEXT BTN IS PRESSED
function showSentence() {
    sessionStorage.setItem("sentencesContainer", JSON.stringify(sentencesContainer));

    //IF THE CURRENT SENTENCE IS THE LAST ONE
    if (sentencesContainer.reachedEnd()) {
        $("#round-page-container").remove();
        loadEndPage()
        return;
    }

    //IF THE CURRENT SENTENCE IS THE FIRST ONE
    if (sentencesContainer.currentSentence > 0) {
        $("#btnPrev").show();

    } else {
        $("#btnPrev").hide();
    }

    //ROUND TEXT
    var round = "<div class='round'>ROUND " + (sentencesContainer.currentSentence + 1) + "/" + (sentencesContainer.sentences.length) + "</div>";

    //SENTENCE TEXT
    var sentenceText = "<div class='sentence'><h2>" + sentencesContainer.sentences[sentencesContainer.currentSentence].sentenceText + "</h2></div>";

    //ANSWERS
    var answers = sentencesContainer.sentences[sentencesContainer.currentSentence].answers;
    answersTexts = getAnswersHTML(answers);

    $("#contents-container").empty();
    $("#contents-container").append(round + sentenceText + answersTexts);
}