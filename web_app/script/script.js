var questions = [];

$("#start-button").click(function() {

    $.ajax({
        url: "http://localhost:8080/sentence",
        method: "GET",
        success: function(response) {
            questions = response;
            $("#start-button").remove();
            game_started();
        }

    });

});

var page = 0;

function game_started() {
    if (page == questions.length) {
        game_ended();
        return;
    }

    $(".questions-container").empty();
    $(".questions-container").append("<p>" + questions[page].id_sentence + " | " + questions[page].sentence_text + "</p>");
    $(".questions-container").append("<button id='pageNext'> Next question </button>");

    page++;

}

function game_ended() {
    $(".questions-container").empty();
    $(".questions-container").append("<p> GIOCO FINITO! </p>");
}

$(".questions-container").click(("#pageNext"), function() {
    game_started();
});