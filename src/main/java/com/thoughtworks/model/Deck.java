package com.thoughtworks.model;

import com.google.common.collect.Lists;


import java.util.List;
import java.util.Stack;

public class Deck {
    private Stack<Card> cards;

    public Deck() {
        cards = new Stack<Card>();
        newDeck();
    }

    private Stack<Card> newDeck() {
        cards.removeAllElements();
        for(Suit suit : Suit.values()) {
            for(Rank rank : Rank.values()) {
                Card card = new Card(suit, rank);
                cards.push(card);
            }
        }
        return cards;
    }

    public Stack<Card> getCards() {
        return cards;
    }

    public int getCardCount() {
        return cards.size();
    }

    public Card deal() {
        return cards.pop();
    }

    public void shuffle() {
        List<Card> cardsForShuffling = Lists.newArrayList(newDeck());
        cards.removeAllElements();
        int count = cardsForShuffling.size();
        for(int i = 0; i < count; i++) {
            int randomCard = (int) (Math.random() * cardsForShuffling.size());
            cards.push(cardsForShuffling.remove(randomCard));
        }
    }
}
