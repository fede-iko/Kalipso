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

        let pRoundText = $("<span></span>");
        pRoundText.addClass("round-result h4");

        let roundText = sentenceText + "... " + answer[1];

        if (answer[2]) {
            roundText += "<i class='fa-solid fa-check result-symbol good-answer d-inline'></i>";
        } else {
            roundText += "<i class='fa-solid fa-x result-symbol bad-answer d-inline'></i>";
        }

        pRoundText.html(roundText);

        divResult.append(pRoundNumber);
        divResult.append(pRoundText);
        roundCounter++;
        $("#results-container").append(divResult);
    }
}