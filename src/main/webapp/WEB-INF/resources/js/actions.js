function doStart() {
    $("#playerPanel").slideDown("slow");
    $("#hostPanel").slideUp();

    $.ajax({
        type : "Get",
        url : "start",
        success : function(response) {
            var jsonObject= eval("("+response+")");
            var playerCard1 = jsonObject.playerCard1;
            var playerCard2 = jsonObject.playerCard2;
            var hostCard1 = jsonObject.hostCard1;
            var hostCard2 = jsonObject.hostCard2;
            var playerName = jsonObject.playerName;
            var playerScore = jsonObject.playerScore;
            document.getElementById("hit").disabled = "";
            document.getElementById("stand").disabled = "";
            $("#playerPanel").html("<div class='cards'><img src=" + playerCard1 + "></div>" +
                "<div class='cards'><img src=" + playerCard2 + "></div>").fadeIn("slow");
            $("#hostPanel").html("<div class='cards'><img src=" + hostCard1 + "></div>" +
                "<div class='cards'><img src=" + hostCard2 + "></div>");
            $("#playerName").text(playerName);
            $("#score").text(playerScore);
            $("#hostName").text("");
            $("#hostScore").text("");
            $("#result").text("");
        },
        error : function(e) {
            alert('Error: ' + e);
        }
    });
}

function doHit() {
    $.ajax({
        type : "Get",
        url : "hit",
        success : function(response) {
            var jsonObject= eval("("+response+")");
            var card = jsonObject.card;
            var isHost = jsonObject.isHost;
            var speakerScore = jsonObject.speakerScore;
            var speakerName = jsonObject.speakerName;

            if (isHost == false) {
                $("#playerPanel").append("<div class='cards'><img src=" + card + "></div>").fadeIn("slow");
            }
            else {
                $("#hostPanel").append("<div class='cards'><img src=" + card + "></div>").fadeIn("slow");
            }
            $("#playerName").text(speakerName);
            $("#score").text(speakerScore);

            var isEnd = jsonObject.isEnd;
            if (isEnd == true) {
                var playerName = jsonObject.playerName;
                var playerScore = jsonObject.playerScore;
                var hostName = jsonObject.hostName;
                var hostScore = jsonObject.hostScore;
                var result = jsonObject.result;
                document.getElementById("hit").disabled = "disabled";
                document.getElementById("stand").disabled = "disabled";
                $("#playerPanel").slideDown();
                $("#hostPanel").slideDown();
                $("#playerName").text(playerName);
                $("#score").text(playerScore);
                $("#hostName").text(hostName);
                $("#hostScore").text(hostScore);
                $("#result").text(result);
            }
        },
        error : function(e) {
            alert('Error: ' + e);
        }
    });
}

function doStand() {
    $("#playerPanel").slideToggle("slow");
    $("#hostPanel").slideDown("slow");
    $.ajax({
        type : "Get",
        url : "stand",
        success : function(response) {
            var jsonObject= eval("("+response+")");
            var isEnd = jsonObject.isEnd;
            var playerName = jsonObject.playerName;
            var playerScore = jsonObject.playerScore;
            var hostName = jsonObject.hostName;
            var hostScore = jsonObject.hostScore;
            var result = jsonObject.result;
            if (isEnd == true) {
                document.getElementById("hit").disabled = "disabled";
                document.getElementById("stand").disabled = "disabled";
                $("#playerPanel").slideDown("slow");
                $("#playerName").text(playerName);
                $("#score").text(playerScore);
                $("#hostName").text(hostName);
                $("#hostScore").text(hostScore);
                $("#result").text(result);
            }
            else {
                document.getElementById("hit").disabled = "";
                document.getElementById("stand").disabled = "";
                $("#playerName").text(hostName);
                $("#score").text(hostScore);
                $("#result").text("");
            }
        },
        error : function(e) {
            alert('Error: ' + e);
        }
    });

}