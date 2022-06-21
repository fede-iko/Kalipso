//RETURN AN ARRAY WITH ALL THE CORRECT ANSWERS ID's
function getGoodAnswersId() {
    goodAnswersId = [];

    $.ajax({
        url: "http://localhost:8080/answer/good_answers",
        method: "GET",
        async: false,
        success: function(response) {
            goodAnswersId.push(...response);
        },
        error: function() {
            $msg = "Connection error! Please try again later.";
            $(".main-container").html($msg);
            throw new Error($msg);
        }
    });

    return goodAnswersId;
}

//PRINT THE RESULT PAGE
function showResult() {
    let goodAnswersId = getGoodAnswersId();

    let corrects = 0;
    let alls = 0;
    for (const [sentenceText, answer] of Object.entries(userAnswers)) {
        alls++;
        if (goodAnswersId.includes(answer[0])) {
            corrects++;
            answer[2] = true;
        } else {
            answer[2] = false;
        }
    }

    if (corrects / alls > 0.5) {
        $("#resultTitle").append("<p class='good-result'>Ben fatto " + userName + "!</p>");
        $("#resultPoints").addClass("good-result");
    } else {
        $("#resultTitle").append("<p class='bad-result'>Peccato " + userName + "! Riprova!</p>");
        $("#resultPoints").addClass("bad-result");
    }

    $("#resultPoints").append(corrects + "/" + alls);
}

$(document).ready(function() {

    showResult();

    //PLAY AGAIN EVENT LISTENER
    $("#backToMenu").unbind().on("click", function() {
        sessionStorage.removeItem("sentencesContainer");
        sessionStorage.removeItem("userAnswers");
        $("#end-container").remove();
        loadMenuPage();
    });

    //BACK TO HOME EVENT LISTENER
    $("#viewResultsBtn").unbind().on("click", function() {
        $("#end-container").remove();
        loadResultsPage();
    });
});