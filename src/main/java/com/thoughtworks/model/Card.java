package com.thoughtworks.model;

public class Card {
    private Suit suit;
    private Rank rank;
    private int value;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        switch (rank) {
            case ACE:   this.value = 1;
                break;
            case KING:  this.value = 10;
                break;
            case QUEEN: this.value = 10;
                break;
            case JACK:  this.value = 10;
                break;
            case TEN:   this.value = 10;
                break;
            case NINE:  this.value = 9;
                break;
            case EIGHT: this.value = 8;
                break;
            case SEVEN: this.value = 7;
                break;
            case SIX:   this.value = 6;
                break;
            case FIVE:  this.value = 5;
                break;
            case FOUR:  this.value = 4;
                break;
            case THREE: this.value = 3;
                break;
            case TWO:   this.value = 2;
                break;
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", rank=" + rank +
                '}';
    }

    public String getImagePath() {
        String rankString = new String();
        String suitString = new String();
        switch (rank) {
            case ACE:   rankString = "1";
                break;
            case KING:  rankString = "K";
                break;
            case QUEEN: rankString = "Q";
                break;
            case JACK:  rankString = "J";
                break;
            case TEN:   rankString = "10";
                break;
            case NINE:  rankString = "9";
                break;
            case EIGHT: rankString = "8";
                break;
            case SEVEN: rankString = "7";
                break;
            case SIX:   rankString = "6";
                break;
            case FIVE:  rankString = "5";
                break;
            case FOUR:  rankString = "4";
                break;
            case THREE: rankString = "3";
                break;
            case TWO:   rankString = "2";
                break;
        }
        switch (suit) {
            case CLUB:   suitString = "C";
                break;
            case HEART:  suitString = "H";
                break;
            case DIAMOND: suitString = "D";
                break;
            case SPADE:  suitString = "S";
                break;
        }
        return "../resources/img/" + rankString + suitString + ".gif";
    }
}
