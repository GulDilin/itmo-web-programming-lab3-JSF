function getDotCoor() {
    const plot_container = document.getElementById("svg-plot");
    let rect = plot_container.getBoundingClientRect();
    y_dot = (event.clientY - rect.top);
    x_dot = (event.clientX - rect.left);
    y = (150 - y_dot);
    x = (-150 + x_dot);

    // y = y / 120 * R;
    // x = x / 120 * R;
    // isDotDraw = true;
    // isDot = (document.getElementById("dot") != null);
    // if (!isDot) {
    let dot = document.createElementNS("http://www.w3.org/2000/svg", "circle");
    dot.setAttribute("r", "3");
    dot.setAttribute("cx", Math.round(x_dot));
    dot.setAttribute("cy", Math.round(y_dot));
    dot.setAttribute("id", "dot");
    // isDot = true;
    document.getElementById("svg-plot").appendChild(dot);
    dot.setAttribute("stroke", "#AD2D2D");
    dot.setAttribute("fill", "#AD2D2D");
    // } else {
    //     let dot = document.getElementById("dot");
    //     dot.setAttribute("r", "3");
    //     dot.setAttribute("cx", Math.round(x_dot));
    //     dot.setAttribute("cy", Math.round(y_dot));
    //     dot.setAttribute("stroke", "#AD2D2D");
    //     dot.setAttribute("fill", "#AD2D2D");
    // }
    // console.log("0");

    // sendRequest(x, y, R);
}

// function changeDotPos() {
//     for
//         }

document.getElementById("svg-plot").onclick = getDotCoor;

function sendRequest(x, y, r) {
    console.log("1");
    let http = new XMLHttpRequest();
    console.log("2");
    let url = "controller";
    let params = "x=" + x + "&y=" + y + "&r=" + r;

    http.open('GET', url + '?' + params);

    console.log("3");
    http.onload = function () {
        document.location.href = 'index.jsp';
    };
    http.send(null);
    console.log("4");
}