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

function hasNumber(str) {
    return /\d/.test(str);
}

function hasSymbols(str){
    return /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+|\s{2,}/.test(str);
}

function hasLetters(str){
    return /[a-zA-Z]+/.test(str);
}

function isValidUsername() {

    var userName = $("#user_name").val();

    if(hasNumber(userName)){
        return false;
    }

    if(!hasLetters(userName)){
        return false;
    }

    if(hasSymbols(userName)){
        return false;
    }

    return true;
}

function name_inserted() {
    $(".title").addClass("title_name");
    $(".title").text("Buona fortuna " + toTitleCase($("#user_name").val()) + "!");
    $("#user-input-container").remove();


    game_start();
}