function valid(element) {


    const X_field = document.getElementById("x");
    check_length(element)
    const errmsg = document.getElementById("error-message");
    const submit_btn = document.getElementById("submit-btn");
    var X = X_field.value.replace(/,/, '.');
    // X.replace(/-/, '');

    var isValid = isNumber(R) && isNumber(X);
    var isValid = isValid && (R < 4) && (R > 1) && (X < 3) && (X > -5);
    if (!isNumber(X)) {
        X_field.style.borderColor = "red";
    } else {
        X_field.style.borderColor = "green";
    }
    // alert(isValid);
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

function check_length(element) {
    const MAX = 8;
    if (element.value.length > MAX) {
        element.value = element.value.substr(0, MAX);
    }
}