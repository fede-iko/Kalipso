$(document).ready(function() {
    if (sessionStorage.getItem("userAnswers")) {
        $("#title").remove();
        $("#menu-container").remove();
        loadRoundsPage();
    }

    $("#title").text("Benvenuto " + sessionStorage["username"] + "!");

    $("#start-btn").unbind().on("click", function() {
        $("#title").remove();
        $("#menu-container").remove();
        loadRoundsPage();
    });

    $("#logout-btn").unbind().on("click", function() {
        sessionStorage.removeItem("username");
        location.reload();
    });

    $("#instruction-btn").unbind().on("click", function() {
        //OPEN MODAL
    });

    $("#settings-btn").unbind().on("click", function() {
        //OPEN MODAL
    });

});