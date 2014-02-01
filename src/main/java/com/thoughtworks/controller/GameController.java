package com.thoughtworks.controller;

import com.thoughtworks.model.Card;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GameController {
    private BlackJackGame blackJackGame;

    public void setBlackJackGame(BlackJackGame blackJackGame) {
        this.blackJackGame = blackJackGame;
    }

    @RequestMapping("/")
    public String printHello() {
        return "index";
    }

    @RequestMapping("/start")
    public @ResponseBody String start() throws JSONException {
        blackJackGame.getDeck().shuffle();
        blackJackGame.start();
        List<Card> playerCards = blackJackGame.getSpeaker().getCardsInHand();
        List<Card> hostCards = blackJackGame.getHost().getCardsInHand();
        JSONObject obj = new JSONObject();
        obj.put("playerCard1", playerCards.get(0).getImagePath());
        obj.put("playerCard2", playerCards.get(1).getImagePath());
        obj.put("hostCard1", hostCards.get(0).getImagePath());
        obj.put("hostCard2", hostCards.get(1).getImagePath());
        obj.put("playerScore", blackJackGame.getSpeaker().getPoints());
        obj.put("playerName", blackJackGame.getSpeaker().getName());
        return obj.toString();
    }

    @RequestMapping("/hit")
    public @ResponseBody String hit() throws JSONException {
        boolean isHost = false;
        if (blackJackGame.getSpeaker().equals(blackJackGame.getHost())) isHost = true;
        JSONObject obj = new JSONObject();
        obj.put("card", blackJackGame.hit().getImagePath());
        obj.put("isHost", isHost);
        obj.put("speakerScore", blackJackGame.getSpeaker().getPoints());
        obj.put("speakerName", blackJackGame.getSpeaker().getName());

        obj.put("isEnd", blackJackGame.isEnd());
        obj.put("playerName", blackJackGame.getPlayer().getName());
        obj.put("playerScore", blackJackGame.getPlayer().getPoints());
        obj.put("hostName", blackJackGame.getHost().getName());
        obj.put("hostScore", blackJackGame.getHost().getPoints());
        obj.put("result", blackJackGame.showResult());

        return obj.toString();
    }

    @RequestMapping("/stand")
    public @ResponseBody String stand() throws JSONException {
        blackJackGame.stand();
        JSONObject obj = new JSONObject();
        obj.put("isEnd", blackJackGame.isEnd());
        obj.put("playerName", blackJackGame.getPlayer().getName());
        obj.put("playerScore", blackJackGame.getPlayer().getPoints());
        obj.put("hostName", blackJackGame.getHost().getName());
        obj.put("hostScore", blackJackGame.getHost().getPoints());
        obj.put("result", blackJackGame.showResult());
        return obj.toString();
    }

}
