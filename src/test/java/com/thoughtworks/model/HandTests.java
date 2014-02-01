package com.thoughtworks.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations.Mock;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class HandTests {
    private Hand hand;
    @Mock
    private Deck mockedDeck;

    @Before
    public void setUp() throws Exception {
        hand = new Hand();
        mockedDeck = mock(Deck.class);
    }

    @Test
    public void should_get_total_points() throws Exception {
        Card firstCard = new Card(Suit.DIAMOND, Rank.EIGHT);
        Card secondCard = new Card(Suit.CLUB, Rank.JACK);

        when(mockedDeck.deal()).thenReturn(firstCard).thenReturn(secondCard);

        hand.draw(mockedDeck.deal());
        hand.draw(mockedDeck.deal());

        verify(mockedDeck, times(2)).deal();
        assertThat(hand.getPoints(), is(18));
    }

    @Test
    public void should_break() throws Exception {
        Card firstCard = new Card(Suit.DIAMOND, Rank.EIGHT);
        Card secondCard = new Card(Suit.CLUB, Rank.JACK);
        Card thirdCard = new Card(Suit.HEART, Rank.FIVE);

        when(mockedDeck.deal()).thenReturn(firstCard).thenReturn(secondCard)
            .thenReturn(thirdCard);

        hand.draw(mockedDeck.deal());
        hand.draw(mockedDeck.deal());
        hand.draw(mockedDeck.deal());

        verify(mockedDeck, times(3)).deal();
        assertThat(hand.isBreak(), is(true));

    }

    @Test
    public void should_not_break() throws Exception {
        Card firstCard = new Card(Suit.DIAMOND, Rank.EIGHT);
        Card secondCard = new Card(Suit.CLUB, Rank.JACK);
        Card thirdCard = new Card(Suit.HEART, Rank.THREE);

        when(mockedDeck.deal()).thenReturn(firstCard).thenReturn(secondCard)
                .thenReturn(thirdCard);

        hand.draw(mockedDeck.deal());
        hand.draw(mockedDeck.deal());
        hand.draw(mockedDeck.deal());

        verify(mockedDeck, times(3)).deal();
        assertThat(hand.isBreak(), is(false));
    }
}
