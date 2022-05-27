var sentences = [];

$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/sentence",
        method: "GET",
        success: function(response) {
            sentences = response;
        }
    });

    $("#start-button").click(function() {
        if (sentences.length == 0) {
            return;
        }
        $("#start-button").remove();
        next_sentence();
    });

    var page = 0;

    function next_sentence() {
        if (page == sentences.length) {
            game_ended();
            return;
        }

        $(".sentences-container").empty();
        let str =
            '<div class="input-group mb-3">' +
            '<span class="input-group-text" id="basic-addon3">' + sentences[page].sentence_text + '...</span>' +
            '<input type="text" class="form-control" id="completed_sentence" placeholder="Completa la frase...">' +
            '<button class="btn btn-success" id="pageNext"><i class="fa-solid fa-arrow-right"></i></button></div>';

        $(".sentences-container").append(str);

        page++;

    }

    function game_ended() {
        $(".sentences-container").empty();
        $(".sentences-container").append("<p> GIOCO FINITO! </p>");
    }

    $(".sentences-container").unbind().on("click", "#pageNext", function() {
        if ($("#completed_sentence").val() == "") {
            alert("Inserisci la frase completa!");
            return;
        }

        next_sentence();
    });

});