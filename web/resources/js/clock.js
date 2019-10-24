window.onload = function(){
    var d = new Date();
    document.getElementById("clock").innerHTML = d.toLocaleTimeString();
    window.setInterval(
        function(){
            d = new Date();
            document.getElementById("clock").innerHTML = d.toLocaleTimeString();
        }
        , 8000);
}