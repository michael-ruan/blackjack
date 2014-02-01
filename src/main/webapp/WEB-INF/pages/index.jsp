<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Black Jack</title>

      <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
      <script src="../resources/js/actions.js"></script>
      <link rel="stylesheet" type="text/css" href="../resources/css/style.css" />

  </head>
  <body>
      <h1 class="title">Black Jack Game</h1>
      <div class="buttons">
          <input type="button" value="Start" class="button" id="start" onclick="doStart();">
          <input type="button" value="Hit" class="button" id="hit" onclick="doHit();">
          <input type="button" value="Stand" class="button" id="stand" onclick="doStand();">
      </div>

      <div class="centerPanel">
      <p class="player" id="player">Player</p>
      <div class="panel" id="playerPanel">
          <p></p>
      </div>

      <p class="player" id="host">Host</p>
      <div class="panel" id="hostPanel">
          <p></p>
      </div>
      </div>
      <div class="board">
          <p class="scoreTitle">Scores</p>

          <div id="playerName" class="boardItem"></div>
          <div id="score" class="boardItem"></div>
          <div id="hostName" class="boardItem"></div>
          <div id="hostScore" class="boardItem"></div>
          <div id="result"class="boardItem"></div>

      </div>

  </body>
</html>
