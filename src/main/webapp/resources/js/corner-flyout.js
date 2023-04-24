function closeFlyout(flyoutid) {
    $("#" + flyoutid).removeClass("active");
}

function openFlyout(flyoutid) {
    $("#" + flyoutid).addClass("active");
}