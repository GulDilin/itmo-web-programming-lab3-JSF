function valid(element) {
    console.log("try valid")
    const errmsg = document.getElementById("error-message");
    const submit_btn = document.getElementById("submit-btn");
    var X = element.value.replace(/,/, '.');
    var isValid = isNumber(X);
    var isValid = isValid && (X < 5) && (X > -5);
    if (!isNumber(X)) {
        element.style.borderColor = "red";
    } else {
        element.style.borderColor = "green";
    }

    if (!isValid) {
        errmsg.textContent = "Error";
        submit_btn.disabled = true;

    } else {
        submit_btn.disabled = false;
        errmsg.textContent = "";
    }

}

function isNumber(n) {
    return !isNaN(parseFloat(n)) && !isNaN(n - 0)
}