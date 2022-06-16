// userAnswers

function showResult() {
    let corrects = 0;
    let alls = 0;
    for (const [sentenceText, answer] of Object.entries(userAnswers)) {
        alls++;
        if (answer[1]) {
            corrects++;
        }
    }

    if(corrects/alls > 0.5){
        $("#resultTitle").append("<p class='good-result'>Ben fatto "+userName+"!</p>");
        $("#resultPoints").addClass("good-result");
    }else{
        $("#resultTitle").append("<p class='bad-result'>Peccato "+userName+"! Riprova!</p>");
        $("#resultPoints").addClass("bad-result");
    }

    $("#resultPoints").append(corrects+"/"+alls);
}
$(document).ready(function(){
    showResult();

    $("#playAgainBtn").on("click",function(){
        $("#end-container").remove();
        loadRoundsPage();
    });

});
