package com.thoughtworks.model;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.util.List;

public class Hand {
    private int points;
    private String name;
    private List<Card> cardsInHand;

    public Hand() {
        this.points = 0;
        this.cardsInHand = Lists.newArrayList();
    }

    public Hand(String name) {
        super();
        this.name = name;
    }

    public Card draw(Card card) {
        cardsInHand.add(card);
        return card;
    }

    public int getPoints() {
        ImmutableList.copyOf(Iterables.transform(
                cardsInHand, new Function<Card, Integer>() {
            @Override
            public Integer apply(Card card) {
                points += card.getValue();
                return card.getValue();
            }
        }
        ));
        return points;
    }
}
