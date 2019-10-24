function chooseCB(elem) {
    var checkboxList = document.querySelectorAll("input[type=checkbox]");
    checkboxList.forEach(function (checkbox) {
        if (checkbox != elem) {
            checkbox.checked = false;
        }
    })
}


