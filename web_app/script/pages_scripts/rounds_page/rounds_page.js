//IT PUT IN THE HTML THE LOADING 'SCREEN' SINCE THE DATA IS FETCHING
$(".main-container").append("<div id='loading-container'><p>Loading...</p></div>");

//CHECK IF IS STILL IN THE ROUNDS PAGE (USED FOR GENERAL EVENT LISTENER ex: ENTER CLICK)
var isRoundsPage = true;

var sentencesContainer = null;

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
            $(".main-container").text("Application error! Please try later.");
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

//CREATE NEXT BTN AND PREV BTN CLICK EVENTS
function createNextPrevBtnEvents() {
    $("#btnNext").on("click", function() {
        if(!isAnswered()){
            return;
        }
        sentencesContainer.currentSentence++;
        showSentence();
    });

    $("#btnPrev").on("click", function() {
        sentencesContainer.currentSentence--;
        showSentence();
    });
}

//ANSWER SELECT EVENT HANDLER
////
/*
TODO
    - When the user comes back to a previous round, the answer selected by the user is still marked as selected
    - The user can't skip a round    
*/
////
function answerSelectEventHandler() {
    for (let i = 1; i < 5; i++) {
        $("#contents-container").on("click", "#btn" + i, function() {                        
            this.classList.add("answer_selected");
            var actualSentenceText = sentencesContainer.sentences[sentencesContainer.currentSentence].sentenceText;
            var actualAnswer = sentencesContainer.sentences[sentencesContainer.currentSentence].answers[i - 1];
            userAnswers[actualSentenceText] = [actualAnswer.answerText, actualAnswer.isCorrect];
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
        if(isAnswered()){
            selectedClass = userAnswers[sentencesContainer.sentences[sentencesContainer.currentSentence].sentenceText][0]==answer.answerText ? "answer_selected" : "";
        }        
        answersTexts += "<div class='btn" + btnCount + "'><button id='btn" + btnCount + "' class='btn-rounds "+selectedClass+"'>" + answer.answerText + "</button></div>";
        btnCount++;
    });
    return answersTexts;
}

//GAME START
function game_start() {
    sentencesContainer = null;
    userAnswers = {}

    getData();

    $("#btnNext").show();

    createNextPrevBtnEvents();

    answerSelectEventHandler();

    //ENTER CLICK LISTENERS
    $(document).on("keypress",function(e){   
        if(!isRoundsPage){
            return;
        }     
        if(e.keyCode == 13){
            if(isAnswered()){
                sentencesContainer.currentSentence++;            
                showSentence();
            }else{
                alert("Devi selezionare una risposta!");
                return;
            }
        }
    });

    //KEYBOARD ARROW CLICK LISTENERS
    $(document).on("keydown",function(e){
        if(!isRoundsPage){
            return;
        }
        if(e.keyCode == 39){
            if(isAnswered()){
                sentencesContainer.currentSentence++;            
                showSentence();
            }else{
                alert("Devi selezionare una risposta!");
                return;
            }
        }
        if(e.keyCode == 37 && sentencesContainer.currentSentence > 0){
            sentencesContainer.currentSentence--;            
            showSentence();
        }        
    });
}

//RETURN TRUE IF THIS ROUND IS ANSERED
function isAnswered(){    
    console.log(userAnswers[sentencesContainer.sentences[sentencesContainer.currentSentence]]);
    console.log(userAnswers);
    return userAnswers[sentencesContainer.sentences[sentencesContainer.currentSentence].sentenceText] != undefined;
}

//SHOW SENTENCE, IT'S CALLED EVERYTIME THE NEXT BTN IS PRESSED
function showSentence() {    

    //IF THE CURRENT SENTENCE IS THE LAST ONE
    if (sentencesContainer.reachedEnd()) {
        isRoundsPage = false;
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
    var round = "<div class='round'>ROUND " + (sentencesContainer.currentSentence + 1) + "/"+(sentencesContainer.sentences.length)+"</div>";

    //SENTENCE TEXT
    var sentenceText = "<div class='sentence'><h2>" + sentencesContainer.sentences[sentencesContainer.currentSentence].sentenceText + "</h2></div>";

    //ANSWERS
    var answers = sentencesContainer.sentences[sentencesContainer.currentSentence].answers;
    answersTexts = getAnswersHTML(answers);

    $("#contents-container").empty();
    $("#contents-container").append(round + sentenceText + answersTexts);

}