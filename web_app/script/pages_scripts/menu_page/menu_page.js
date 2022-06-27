//SETTINGS FOR THE MENU PAGE, FOR OTHER PAGES YOU USE THE SESSION STORAGE
var settings = {
    bgImage: "",
};


$(document).ready(function() {

    //IF SESSION CONTAINS THE SETTINGS, LOAD IT
    if (sessionStorage.getItem("settings")) {
        settings = JSON.parse(sessionStorage["settings"]);
    }


    //IF THE USER IS ALREADY IN THE MIDDLE OF THE GAME
    if (sessionStorage.getItem("userAnswers")) {
        $("#title").remove();
        $("#menu-container").remove();
        loadRoundsPage();
    }

    //THE TITLE
    $("#title").text("Benvenuto " + sessionStorage["username"] + "!");

    //GAME START BTN
    $("#start-btn").unbind().on("click", function() {
        $("#title").remove();
        $("#menu-container").remove();
        loadRoundsPage();
    });

    //LOGOUT BTN
    $("#logout-btn").unbind().on("click", function() {
        //DELETE SESSION
        sessionStorage.clear();
        location.reload();
    });

    //SETTINGS SAVE BTN
    $("#settingsSave").unbind().on("click", function() {
        sessionStorage.setItem("settings", JSON.stringify(settings));
    });

    loadModalBackgroundsImagesChoices();
});

function loadModalBackgroundsImagesChoices() {
    var bgsContainer = $(".bgimages-container");

    for (var i = 1; i <= 8; i++) {

        var bgImageContainer = $("<div></div>");
        bgImageContainer.addClass("d-flex flex-column m-3 justify-content-center align-items-center");

        var bgImageDiv = $("<div></div>");

        var imageLabel = $("<label></label>");
        imageLabel.attr("for", "bgImage" + i);

        var imageUrl = "./assets/bg-images/bg" + i + ".jpg";

        //IF IS THE REMOVE IMAGE BUTTON
        if (i != 8) {
            var image = $("<img>");
            image.attr("src", imageUrl);
            imageLabel.append(image);
        } else {
            imageLabel.html("<h5>Nessuna immagine</h5>");
        }

        bgImageDiv.append(imageLabel);

        var radioDiv = $("<div></div>");

        var radio = $("<input>");
        radio.attr("type", "radio");
        radio.attr("name", "bgImage");
        radio.attr("id", "bgImage" + i);
        radio.attr("value", imageUrl);


        if (imageUrl == settings.bgImage) {
            radio.attr("checked", "checked");
        }
        radio.addClass("form-check-input");

        radioDiv.append(radio);

        bgImageContainer.append(bgImageDiv);
        bgImageContainer.append(radioDiv);

        bgsContainer.append(bgImageContainer);

        createRadioEventHandler(radio.attr("id"));
    }

}

function createRadioEventHandler(id) {
    $("#" + id).unbind().on("click", function(e) {
        var src = $(this).val();
        $("body").css("background", "url(" + src + ")");
        $("body").css("background-size", "cover");
        $("body").css("background-repeat", "no-repeat");
        $("body").css("background-attachment", "fixed");
        settings.bgImage = src;
    });
}