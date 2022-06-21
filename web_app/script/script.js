$(document).ready(function() {
    loadRightBackground();

    loadUserNameForm();
});

function loadRightBackground() {

    if (sessionStorage.getItem("settings")) {
        var settings = JSON.parse(sessionStorage.getItem("settings"));
        $("body").css("background", "url(" + settings.bgImage + ")");
    } else {
        var src = "./assets/bg-images/bg1.jpg";
        $("body").css("background", "url(" + src + ")");
    }
    $("body").css("background-size", "cover");
    $("body").css("background-repeat", "no-repeat");
    $("body").css("background-attachment","fixed");
}