package com.thoughtworks.controller;

import com.thoughtworks.model.*;

public class BlackJackGame {
    private Deck deck;
    private Hand host;
    private Hand player;
    private Hand speaker;
    private Hand winner;
    private int hostScore;
    private int playerScore;
    private boolean endFlag;

    public BlackJackGame(Hand host, Hand player) {
        this.host = host;
        this.player = player;
        this.speaker = this.player;
        this.winner = null;
        this.endFlag = false;
        this.deck = new Deck();
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Hand getHost() {
        return host;
    }

    public Hand getPlayer() {
        return player;
    }

    public Hand getSpeaker() {
        return speaker;
    }

    public void setHostScore(int hostScore) {
        this.hostScore = hostScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getHostScore() {
        return hostScore;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public Hand getWinner() {
        return winner;
    }

    public void setWinner(Hand winner) {

        this.winner = winner;
    }


    public void start() {
        host.refresh();
        player.refresh();
        speaker = player;
        winner = null;
        endFlag = false;

        deck.shuffle();
        host.draw(deck.deal());
        player.draw(deck.deal());
        host.draw(deck.deal());
        player.draw(deck.deal());
    }

    public Card hit() {
        Card card = deck.deal();
        speaker.draw(card);
        if (speaker.isBreak()) endGame();
        if (speaker.equals(player) && speaker.getPoints() == 21) endGame();
        return card;
    }

    private void endGame() {
        setHostScore(host.getPoints());
        setPlayerScore(player.getPoints());
        if (player.isBreak()) setWinner(host);
        else if (getPlayerScore() == 21) setWinner(player);
        else if (host.isBreak()) setWinner(player);
        else if (getPlayerScore() > getHostScore()) setWinner(player);
        else if (getPlayerScore() < getHostScore()) setWinner(host);
        else if (getPlayerScore() == getHostScore()) setWinner(null);
        endFlag = true;
    }

    public void stand() {
        if (speaker.equals(host)) endGame();
        if (speaker.equals(player)) speaker = host;
    }

    public String showResult() {
        if (!isEnd()) return "Game isn't over!";
        if (host.equals(getWinner())) return "Host Win!";
        if (player.equals(getWinner())) return "Player Win!";
        return "In a Tie!";
    }

    public boolean isEnd() {
        return endFlag;
    }
}
