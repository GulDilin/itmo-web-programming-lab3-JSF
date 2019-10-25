function getDotCoor() {
    const plot_container = document.getElementById("svg-plot");
    let rect = plot_container.getBoundingClientRect();
    let y_dot = (event.clientY - rect.top);
    let x_dot = (event.clientX - rect.left);
    let y = (150 - y_dot);
    let x = (-150 + x_dot);

    let isDotAllowed = validCB();
    let result = "";
    if (isDotAllowed) {
        let R = getR();
        y = y / 120 * R;
        x = x / 120 * R;
        let dot = document.createElementNS("http://www.w3.org/2000/svg", "circle");
        dot.setAttribute("r", "3");
        dot.setAttribute("cx", Math.round(x_dot));
        dot.setAttribute("cy", Math.round(y_dot));
        document.getElementById("svg-plot").appendChild(dot);
        if (!check(x, y, R)) {
            dot.setAttribute("stroke", "#AD2D2D");
            dot.setAttribute("fill", "#AD2D2D");
        } else {
            dot.setAttribute("stroke", "green");
            dot.setAttribute("fill", "green");
        }
        dot.setAttribute("class", R);
        result = x + " " + y + " " + R;
        sendDot([{name: 'x', value: x}, {name: 'y', value: y}, {name: 'r', value: R}]);

    }
}

function changeDotPos() {
    if (validCB()) {
        let dotsList = document.querySelectorAll("circle");
        console.log(dotsList);
        dotsList.forEach(function (dot) {
            let x_dot = parseInt(dot.getAttribute("cx"));
            let y_dot = dot.getAttribute("cy");
            console.log(x_dot);
            let R = dot.getAttribute("class");
            let y = (150 - y_dot);
            let x = (-150 + x_dot);
            y = y * R;
            x = x * R;
            R = getR();
            y = y / R;
            x = x / R;
            x_dot = x + 150;
            y_dot = 150 - y;
            if (!check((-150 + x_dot) / 120 * R, (150 - y_dot) / 120 * R, R)) {
                dot.setAttribute("stroke", "#AD2D2D");
                dot.setAttribute("fill", "#AD2D2D");
            } else {
                dot.setAttribute("stroke", "green");
                dot.setAttribute("fill", "green");
            }
            dot.setAttribute("cx", Math.round(x_dot));
            dot.setAttribute("cy", Math.round(y_dot));
            dot.setAttribute("class", R);

        })
    }
}

function check(x, y, r) {
    return checkCircle(x, y, r) || checkRectangle(x, y, r) || checkTriangle(x, y, r);

}

function checkRectangle(x, y, r) {
    return (x >= -r) && (x <= 0) && (y >= -0.5 * r) && (y <= 0);
}

function checkCircle(x, y, r) {
    return (x * x + y * y <= r * r / 4) && (x <= 0) && (y >= 0);
}

function checkTriangle(x, y, r) {
    return (-2 * x + r >= y) && (x >= 0) && (y >= 0);
}