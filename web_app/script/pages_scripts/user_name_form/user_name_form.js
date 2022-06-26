var userName;

$(document).ready(function() {
    //IF THE USERANSWERS SESSION IS SET MEANS THAT THE USER HAS ALREADY ANSWERED THE SENTENCES
    if (sessionStorage.getItem("username")) {
        $("#user_name").val(sessionStorage["username"]);
        name_inserted();
    }

    $("#loader-container").remove();

    //USER TYPED SOMETHING
    $("#user_name").unbind().on("input", function() {
        if (isValidUsername()) {
            $("#user_name").css("color", "var(--primary)");
            $("#submit_name").show();
            $(".alert-container").empty();
        } else {
            $("#user_name").css("color", "red");
            $("#submit_name").hide();

            var msg = "<div class='h5 my-5 mx-auto text-start'><p>Username invalid</p>" +
                "<ul><li>Username must be at least 3 characters long</li>" +
                "<li>Username can only container letters and numbers</li></ul></div>";
            $(".alert-container").html(msg);
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
    return /^[a-zA-Z0-9]{3,}$/.test(usr)
}

//VALID USER AND LOAD ROUNDS
function name_inserted() {
    sessionStorage.setItem("username", $("#user_name").val());
    userName = $("#user_name").val();

    $("#user-input-container").remove();

    loadMenuPage();
}