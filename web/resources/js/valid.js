var isXvalid = false;

function valid(element) {
    console.log("try valid")
    const errmsg = document.getElementById("error-message");
    let X = element.value.replace(/,/, '.');
    let isValid = isNumber(X);
    isValid = isValid && (X < 5) && (X > -5);
    if (!isValid) {
        element.style.borderColor = "red";
    } else {
        element.style.borderColor = "green";
    }
    if (!isValid) {
        errmsg.textContent = "Error";
    } else {
        errmsg.textContent = " ";
    }
    isXvalid = isValid;
    console.log("x valid: " + isXvalid);
    isValid = isValid && validCB();
    disableButtons(!isValid);
}

function isNumber(n) {
    return !isNaN(parseFloat(n)) && !isNaN(n - 0)
}

function validCB() {
    let checkboxList = document.querySelectorAll("input[type=checkbox]");
    let count = 0;
    const errmsg = document.getElementById("error-message-R");
    checkboxList.forEach(function (checkbox) {
        if (checkbox.checked) {
            count++;
        }
    });
    if (count === 1) {
        errmsg.textContent = " ";
        console.log("x valid: " + isXvalid);
        disableButtons(!isXvalid);
        return true;
    } else if (count === 0) {
        errmsg.textContent = "R IS REQUIRED";
        disableButtons(true);
        return false;
    } else if (count > 0) {
        errmsg.textContent = "CHOOSE ONLY ONE";
        disableButtons(true);
        return false;
    }
}

function chooseCB(elem) {
    let checkboxList = document.querySelectorAll("input[type=checkbox]");
    checkboxList.forEach(function (checkbox) {
        if (checkbox !== elem) {
            checkbox.checked = false;
        }
    })
}

function getR() {
    let checkboxList = document.querySelectorAll("input[type=checkbox]");
    let num = 0;
    let isCheck = false;
    checkboxList.forEach(function (checkbox) {
        if(!isCheck) {
            num++;
        }
        if (checkbox.checked) {
            isCheck = true;
        }
    });
    return num;
}

function disableButtons(isDisabled) {
    let buttonsList = document.querySelectorAll("input[type=submit]");
    console.log(" ");
    buttonsList.forEach(function (button) {
        button.disabled = isDisabled;
        console.log("disabled = " + isDisabled);
    });
}
