$(document).ready(function() {

    loadResults();

    //BACK TO END PAGE EVENT LISTENER
    $("#backToEndPage").on("click", function() {
        $("#results-page-container").remove();
        loadEndPage();
    });
});

//LOAD THE RESULTS HTML
function loadResults() {
    let roundCounter = 1;
    for (const [sentenceText, answer] of Object.entries(userAnswers)) {
        let divResult = $("<div></div>");
        divResult.addClass("result-container");

        let pRoundNumber = $("<p></p>");
        pRoundNumber.addClass("round-number");
        pRoundNumber.text("Round " + roundCounter);

        let pRoundText = $("<p></p>");
        pRoundText.addClass("round-result");
        pRoundText.text(sentenceText + "... " + answer[1]);

        let pSymbol = $("<span></span>");
        pSymbol.addClass("result-symbol");
        if (answer[2]) {
            pSymbol.addClass("good-answer");
            pSymbol.append("<i class='fa-solid fa-check'></i>");
        } else {
            pSymbol.addClass("bad-answer");
            pSymbol.append("<i class='fa-solid fa-x'></i>");
        }

        divResult.append(pRoundNumber);
        pRoundText.append(pSymbol);
        divResult.append(pRoundText);
        roundCounter++;
        $("#results-container").append(divResult);
    }
}