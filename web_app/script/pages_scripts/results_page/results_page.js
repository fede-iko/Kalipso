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

    let divResultHeader = $("<div></div>");
    divResultHeader.addClass("h1 fw-bold text-center mb-5");
    divResultHeader.text("RISULTATI");
    $("#results-container").append(divResultHeader);

    for (const [sentenceText, answer] of Object.entries(userAnswers)) {
        let divResult = $("<div tabindex=0></div>");
        divResult.addClass("result-container m-auto my-3 p-2");

        let pRoundNumber = $("<div></div>");
        pRoundNumber.addClass("round-number h2 fw-800 mb-4");
        pRoundNumber.text("Round " + roundCounter);

        let pRoundText = $("<div></div>");
        pRoundText.addClass("round-result h4");
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