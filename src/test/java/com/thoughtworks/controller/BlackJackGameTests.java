package com.thoughtworks.controller;

import com.thoughtworks.model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations.Mock;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class BlackJackGameTests {
    @Mock
    private Deck mockedDeck;

    private BlackJackGame blackJackGame;

    public void setBlackJackGame(BlackJackGame blackJackGame) {
        this.blackJackGame = blackJackGame;
    }

    @Before
    public void setUp() throws Exception {
        Hand host = new Hand("Host");
        Hand player = new Hand("Player");
        blackJackGame = new BlackJackGame(host, player);
        mockedDeck = mock(Deck.class);
    }

    @Test
    public void should_start_game() throws Exception {
        blackJackGame.start();
        assertThat(blackJackGame.getDeck().getCardCount(), is(48));
    }

    @Test
    public void should_get_two_cards_each_at_beginning() throws Exception {
        blackJackGame.start();
        assertThat(blackJackGame.getHost().getCardsInHand().size(), is(2));
        assertThat(blackJackGame.getPlayer().getCardsInHand().size(), is(2));
    }

    @Test
    public void should_draw_one_card_when_player_hits() throws Exception {
        blackJackGame.start();
        blackJackGame.hit();
        assertThat(blackJackGame.getSpeaker(), is(blackJackGame.getPlayer()));
        assertThat(blackJackGame.getPlayer().getCardsInHand().size(), is(3));
    }

    @Test
    public void should_stop_and_exchange_speaker_when_player_stays() throws Exception {
        blackJackGame.start();
        blackJackGame.stand();
        assertThat(blackJackGame.getSpeaker(), is(blackJackGame.getHost()));
        assertThat(blackJackGame.getPlayer().getCardsInHand().size(), is(2));
    }

    @Test
    public void should_draw_one_card_when_host_hits() throws Exception {
        blackJackGame.start();
        blackJackGame.stand();
        blackJackGame.hit();
        assertThat(blackJackGame.getSpeaker(), is(blackJackGame.getHost()));
        assertThat(blackJackGame.getHost().getCardsInHand().size(), is(3));
    }

    @Test
    public void should_stop_when_host_stays() throws Exception {
        blackJackGame.start();
        blackJackGame.stand();
        blackJackGame.hit();
        blackJackGame.stand();
        assertThat(blackJackGame.getSpeaker(), is(blackJackGame.getHost()));
        assertThat(blackJackGame.getHost().getCardsInHand().size(), is(3));
    }

    @Test
    public void host_should_win_when_player_breaks() throws Exception {
        Card firstCard = new Card(Suit.DIAMOND, Rank.EIGHT);
        Card secondCard = new Card(Suit.CLUB, Rank.JACK);
        Card thirdCard = new Card(Suit.HEART, Rank.FIVE);
        Card fourthCard = new Card(Suit.HEART, Rank.FIVE);
        Card fifthCard = new Card(Suit.HEART, Rank.KING);

        when(mockedDeck.deal()).thenReturn(firstCard).thenReturn(secondCard)
                .thenReturn(thirdCard).thenReturn(fourthCard).thenReturn(fifthCard);
        blackJackGame.setDeck(mockedDeck);
        blackJackGame.start();
        blackJackGame.hit();

        verify(mockedDeck, times(5)).deal();
        assertThat(blackJackGame.showResult(), is("Host Win!"));

    }

    @Test
    public void player_should_win_when_only_host_breaks() throws Exception {
        Card firstCard = new Card(Suit.DIAMOND, Rank.EIGHT);
        Card secondCard = new Card(Suit.CLUB, Rank.JACK);
        Card thirdCard = new Card(Suit.HEART, Rank.JACK);
        Card fourthCard = new Card(Suit.HEART, Rank.NINE);
        Card fifthCard = new Card(Suit.HEART, Rank.KING);

        when(mockedDeck.deal()).thenReturn(firstCard).thenReturn(secondCard)
                .thenReturn(thirdCard).thenReturn(fourthCard).thenReturn(fifthCard);
        blackJackGame.setDeck(mockedDeck);
        blackJackGame.start();
        blackJackGame.stand();
        blackJackGame.hit();

        verify(mockedDeck, times(5)).deal();
        assertThat(blackJackGame.showResult(), is("Player Win!"));
    }

    @Test
    public void player_should_win_when_be_blackjack() throws Exception {
        Card firstCard = new Card(Suit.DIAMOND, Rank.EIGHT);
        Card secondCard = new Card(Suit.CLUB, Rank.JACK);
        Card thirdCard = new Card(Suit.HEART, Rank.FIVE);
        Card fourthCard = new Card(Suit.HEART, Rank.ACE);
        Card fifthCard = new Card(Suit.HEART, Rank.KING);

        when(mockedDeck.deal()).thenReturn(firstCard).thenReturn(secondCard)
                .thenReturn(thirdCard).thenReturn(fourthCard).thenReturn(fifthCard);
        blackJackGame.setDeck(mockedDeck);
        blackJackGame.start();
        blackJackGame.hit();

        verify(mockedDeck, times(5)).deal();
        assertThat(blackJackGame.showResult(), is("Player Win!"));

    }

    @Test
    public void host_win_when_score_of_host_is_bigger_and_both_do_not_break() throws Exception {
        Card firstCard = new Card(Suit.DIAMOND, Rank.EIGHT);
        Card secondCard = new Card(Suit.CLUB, Rank.JACK);
        Card thirdCard = new Card(Suit.HEART, Rank.KING);
        Card fourthCard = new Card(Suit.HEART, Rank.NINE);
        Card fifthCard = new Card(Suit.HEART, Rank.TWO);

        when(mockedDeck.deal()).thenReturn(firstCard).thenReturn(secondCard)
                .thenReturn(thirdCard).thenReturn(fourthCard).thenReturn(fifthCard);
        blackJackGame.setDeck(mockedDeck);
        blackJackGame.start();
        blackJackGame.stand();
        blackJackGame.hit();
        blackJackGame.stand();

        verify(mockedDeck, times(5)).deal();
        assertThat(blackJackGame.showResult(), is("Host Win!"));

    }
    
    @Test
    public void player_win_when_score_of_player_is_bigger_and_both_do_not_break() throws Exception {
        Card firstCard = new Card(Suit.DIAMOND, Rank.EIGHT);
        Card secondCard = new Card(Suit.CLUB, Rank.JACK);
        Card thirdCard = new Card(Suit.HEART, Rank.FIVE);
        Card fourthCard = new Card(Suit.HEART, Rank.KING);
        Card fifthCard = new Card(Suit.HEART, Rank.FOUR);

        when(mockedDeck.deal()).thenReturn(firstCard).thenReturn(secondCard)
                .thenReturn(thirdCard).thenReturn(fourthCard).thenReturn(fifthCard);
        blackJackGame.setDeck(mockedDeck);
        blackJackGame.start();
        blackJackGame.stand();
        blackJackGame.hit();
        blackJackGame.stand();

        verify(mockedDeck, times(5)).deal();
        assertThat(blackJackGame.showResult(), is("Player Win!"));

    }

    @Test
    public void should_be_tied() throws Exception {
        Card firstCard = new Card(Suit.DIAMOND, Rank.EIGHT);
        Card secondCard = new Card(Suit.CLUB, Rank.JACK);
        Card thirdCard = new Card(Suit.HEART, Rank.QUEEN);
        Card fourthCard = new Card(Suit.HEART, Rank.FIVE);
        Card fifthCard = new Card(Suit.HEART, Rank.THREE);

        when(mockedDeck.deal()).thenReturn(firstCard).thenReturn(secondCard)
                .thenReturn(thirdCard).thenReturn(fourthCard).thenReturn(fifthCard);
        blackJackGame.setDeck(mockedDeck);
        blackJackGame.start();
        blackJackGame.hit();
        blackJackGame.stand();
        blackJackGame.stand();

        verify(mockedDeck, times(5)).deal();
        assertThat(blackJackGame.showResult(), is("In a Tie!"));

    }

    @Test
    public void host_wins_when_player_breaks() throws Exception {
        Card firstCard = new Card(Suit.HEART, Rank.FIVE);
        Card secondCard = new Card(Suit.DIAMOND, Rank.FIVE);
        Card thirdCard = new Card(Suit.HEART, Rank.JACK);
        Card fourthCard = new Card(Suit.SPADE, Rank.SIX);
        Card fifthCard = new Card(Suit.DIAMOND, Rank.ACE);
        Card sixthCard = new Card(Suit.DIAMOND, Rank.JACK);
        Card seventhCard = new Card(Suit.DIAMOND, Rank.THREE);

        when(mockedDeck.deal()).thenReturn(firstCard).thenReturn(secondCard)
                .thenReturn(thirdCard).thenReturn(fourthCard).thenReturn(fifthCard)
                .thenReturn(sixthCard).thenReturn(seventhCard);
        blackJackGame.setDeck(mockedDeck);
        blackJackGame.start();
        blackJackGame.hit();
        blackJackGame.hit();
        //blackJackGame.stand();
        //blackJackGame.stand();

        verify(mockedDeck, times(6)).deal();
        assertThat(blackJackGame.isEnd(), is(true));

    }
}
