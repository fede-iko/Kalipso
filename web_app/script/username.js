$(document).ready(function() {

    $("#user_name").on("input", function() {
        if (isValidUsername()) {
            $("#user_name").css("color", "var(--primary)");
            $("#submit_name").show();
        } else {
            $("#user_name").css("color", "red");
            $("#submit_name").hide();
        }
    });

    $("#submit_name").on("click", function() {
        if (isValidUsername()) {
            name_inserted();
        }
    });

    $(document).on("keypress", function(e) {
        if (e.keyCode == 13 && isValidUsername()) {
            name_inserted();
        }
    });
});

function toTitleCase(str) {
    return str.replace(
        /\w\S*/g,
        function(txt) {
            return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
        }
    );
}

function isValidUsername() {
    var validName = /^(([a-zA-Z]{2,})\s{0,1})+$/;
    var userName = $("#user_name").val();
    return validName.test(userName);
}

function name_inserted() {
    $(".title").addClass("title_name");
    $(".title").text("Buona fortuna " + toTitleCase($("#user_name").val()) + "!");
    $("#user-input-container").remove();


    game_start();
}