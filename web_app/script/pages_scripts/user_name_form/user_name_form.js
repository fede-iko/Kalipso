var userName;

$(document).ready(function() {
    //IF THE USERNAME IS ALREADY INSERTED IN THE SESSION STORAGE LOAD THE ROUNDS PAGE
    if (sessionStorage.getItem("username")) {
        $("#user_name").val(sessionStorage["username"]);
        name_inserted();
    }

    //USER TYPED SOMETHING
    $("#user_name").unbind().on("input", function() {
        if (isValidUsername()) {
            $("#user_name").css("color", "var(--primary)");
            $("#submit_name").show();
            sessionStorage.setItem("username", $("#user_name").val());

        } else {
            $("#user_name").css("color", "red");
            $("#submit_name").hide();
        }
    });

    //USER PRESSED THE BUTTON
    $("#submit_name").unbind().on("click", function() {
        name_inserted();
    });

    //USER PRESSED ENTER
    $(document).unbind().on("keypress", function(e) {
        if (e.keyCode == 13 && isValidUsername()) {
            name_inserted();
        }
    });
});

//CHANGE THE STRING TO TITLE CASE
//EX: eXaMple nUmber -> Example Number
function toTitleCase(str) {
    return str.replace(
        /\w\S*/g,
        function(txt) {
            return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
        }
    );
}

//RETURN TRUE IF THE USERNAME IS VALID
function isValidUsername() {
    var usr = $("#user_name").val();
    return /^[a-zA-Z0-9]+$/.test(usr)
}

//VALID USER AND LOAD ROUNDS
function name_inserted() {
    userName = $("#user_name").val();
    $(".title").addClass("smaller_title");
    $(".title").text("Buona fortuna " + toTitleCase(userName) + "!");
    $("#user-input-container").remove();

    loadRoundsPage();
}