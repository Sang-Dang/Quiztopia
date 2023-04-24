let mainTagId = getTagId().id;
let selector = "#" + mainTagId + " .close";
$(selector).click(function() {
    $("#" + mainTagId).toggleClass("active");
});