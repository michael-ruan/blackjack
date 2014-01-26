package com.thoughtworks.model;

import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DeckTests {
    Deck deck;
    @Before
    public void setUp() throws Exception {
        deck = new Deck();
    }

    @Test
    public void should_get_all_cards_on_the_deck() throws Exception {
        assertThat(deck.getCardCount(), is(52));
    }

    @Test
    public void should_deal_one_card() throws Exception {
        Card cardByDealing = deck.deal();
        assertThat(deck.getCards().contains(cardByDealing), is(false));
        assertThat(deck.getCardCount(), is(51));
    }

    @Test
    public void should_shuffle() throws Exception {
        deck.deal();
        deck.shuffle();
        assertThat(deck.getCardCount(), is(52));
    }
}
