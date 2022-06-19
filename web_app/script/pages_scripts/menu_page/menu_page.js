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

    $(".carousel-item").unbind().on("click", function(e) {
        console.log(e);
        $("body").css("background", "url(" + e.currentTarget.childNodes[1].attributes[0].nodeValue + ")");
        $("body").css("background-size", "cover");
        $("body").css("background-repeat", "no-repeat");
    });
});