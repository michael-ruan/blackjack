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
}
