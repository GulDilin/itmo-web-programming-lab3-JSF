function valid(element) {
    console.log("try valid")
    const errmsg = document.getElementById("error-message");
    const submit_btn = document.getElementById("submit-btn");
    let X = element.value.replace(/,/, '.');
    let isValid = isNumber(X);
    isValid = isValid && (X < 5) && (X > -5);
    if (!isNumber(X)) {
        element.style.borderColor = "red";
    } else {
        element.style.borderColor = "green";
    }

    if (!isValid) {
        errmsg.textContent = "Error";
        submit_btn.disabled = true;

    } else {
        errmsg.textContent = "";
    }
    submit_btn.disabled = !validCB();
}

function isNumber(n) {
    return !isNaN(parseFloat(n)) && !isNaN(n - 0)
}

function validCB() {
    let checkboxList = document.querySelectorAll("input[type=checkbox]");
    let count = 0;
    const errmsg = document.getElementById("error-message");
    const submit_btn = document.getElementById("submit-btn");
    checkboxList.forEach(function (checkbox) {
        if (checkbox.checked){
            count++;
        }
    })
    if (count == 1){
        errmsg.textContent = "";
        return true;
    } else if (count == 0){
        errmsg.textContent = "R IS REQUIRED";
        return false;
    } else if (count > 0) {
        errmsg.textContent = "CHOOSE ONLY ONE";
        return false;

    }
}
