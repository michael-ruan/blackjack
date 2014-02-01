package com.thoughtworks.controller;

import com.sun.tools.javac.util.List;
import com.thoughtworks.model.*;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")
public class GameControllerTests {
    private MockMvc mockMvc;

    @Mock
    private Deck mockedDeck;

    private BlackJackGame blackJackGame;
    private GameController gameController;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
        Hand host = new Hand("Host");
        Hand player = new Hand("Player");
        blackJackGame = new BlackJackGame(host, player);
        gameController = new GameController();
        gameController.setBlackJackGame(blackJackGame);
        mockedDeck = mock(Deck.class);
    }

    @Test
    public void should_get_index_page() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void should_test_mockito() throws Exception {
        List<String> mockedList = mock(List.class);
        //stubbing
        when(mockedList.get(0)).thenReturn("first");

        String res = mockedList.get(0);

        verify(mockedList).get(0);
        assertThat(res, is("first"));
    }

    @Test
    public void test_start() throws Exception {
        Card firstCard = new Card(Suit.HEART, Rank.FIVE);
        Card secondCard = new Card(Suit.DIAMOND, Rank.FIVE);
        Card thirdCard = new Card(Suit.HEART, Rank.JACK);
        Card fourthCard = new Card(Suit.SPADE, Rank.SIX);

        when(mockedDeck.deal()).thenReturn(firstCard).thenReturn(secondCard)
                .thenReturn(thirdCard).thenReturn(fourthCard);
        blackJackGame.setDeck(mockedDeck);
        String result = gameController.start();

        JSONObject obj = new JSONObject();
        obj.put("playerCard1", "../resources/img/5D.gif");
        obj.put("playerCard2", "../resources/img/6S.gif");
        obj.put("hostCard1", "../resources/img/5H.gif");
        obj.put("hostCard2", "../resources/img/JH.gif");
        obj.put("playerScore", 11);
        obj.put("playerName", "Player");

        assertThat(result, is(obj.toString()));
        verify(mockedDeck, times(4)).deal();

    }

    @Test
    public void test_hit() throws Exception {
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
        String result = gameController.hit();

        JSONObject obj = new JSONObject();
        obj.put("card", "../resources/img/JD.gif");
        obj.put("isHost", false);
        obj.put("speakerScore", 22);
        obj.put("speakerName", "Player");
        obj.put("isEnd", true);
        obj.put("playerName", "Player");
        obj.put("playerScore", 22);
        obj.put("hostName", "Host");
        obj.put("hostScore", 15);
        obj.put("result", "Host Win!");

        assertThat(result, is(obj.toString()));
        verify(mockedDeck, times(6)).deal();
    }

    @Test
    public void test_player_stands() throws Exception {
        Card firstCard = new Card(Suit.HEART, Rank.FIVE);
        Card secondCard = new Card(Suit.DIAMOND, Rank.FIVE);
        Card thirdCard = new Card(Suit.HEART, Rank.JACK);
        Card fourthCard = new Card(Suit.SPADE, Rank.SIX);
        Card fifthCard = new Card(Suit.DIAMOND, Rank.ACE);

        when(mockedDeck.deal()).thenReturn(firstCard).thenReturn(secondCard)
                .thenReturn(thirdCard).thenReturn(fourthCard).thenReturn(fifthCard);
        blackJackGame.setDeck(mockedDeck);
        blackJackGame.start();
        blackJackGame.hit();
        String result = gameController.stand();

        JSONObject obj = new JSONObject();
        obj.put("isEnd", false);
        obj.put("playerName", "Player");
        obj.put("playerScore", 12);
        obj.put("hostName", "Host");
        obj.put("hostScore", 15);
        obj.put("result", "Game isn't over!");

        assertThat(result, is(obj.toString()));
        verify(mockedDeck, times(5)).deal();

    }

    @Test
    public void test_host_stands() throws Exception {
        Card firstCard = new Card(Suit.HEART, Rank.FIVE);
        Card secondCard = new Card(Suit.DIAMOND, Rank.FIVE);
        Card thirdCard = new Card(Suit.HEART, Rank.JACK);
        Card fourthCard = new Card(Suit.SPADE, Rank.SIX);
        Card fifthCard = new Card(Suit.DIAMOND, Rank.ACE);

        when(mockedDeck.deal()).thenReturn(firstCard).thenReturn(secondCard)
                .thenReturn(thirdCard).thenReturn(fourthCard).thenReturn(fifthCard);
        blackJackGame.setDeck(mockedDeck);
        blackJackGame.start();
        blackJackGame.hit();
        blackJackGame.stand();
        String result = gameController.stand();

        JSONObject obj = new JSONObject();
        obj.put("isEnd", true);
        obj.put("playerName", "Player");
        obj.put("playerScore", 12);
        obj.put("hostName", "Host");
        obj.put("hostScore", 15);
        obj.put("result", "Host Win!");

        assertThat(result, is(obj.toString()));
        verify(mockedDeck, times(5)).deal();

    }
}
