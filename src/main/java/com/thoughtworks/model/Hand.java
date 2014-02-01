package com.thoughtworks.model;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private int points;
    private String name;
    private List<Card> cardsInHand = Lists.newArrayList();

    public Hand() {
        this.points = 0;
    }

    public Hand(String name) {
        super();
        this.name = name;
    }

    public List<Card> getCardsInHand() {
        return cardsInHand;
    }

    public Card draw(@NotNull Card card) {
        cardsInHand.add(card);
        points += card.getValue();
        return card;
    }

    public int getPoints() {
        return points;
    }

    public boolean isBreak() {
        if (points > 21) return true;
        return false;
    }

    public String getName() {
        return name;
    }

    public void refresh() {
        points = 0;
        cardsInHand.clear();
    }

}
