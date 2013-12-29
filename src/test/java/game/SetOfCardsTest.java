package game;


import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class SetOfCardsTest {
    SetOfCards setOfCards = new SetOfCards();

    @Before
    public void init() {
        setOfCards.reset();
    }

    @Test
    public void isEmptyTest() {
        assertFalse(setOfCards.isEmpty());
        for (int i = 0; i < 36; i++) {
            setOfCards.getNextCard();
        }
        assertTrue(setOfCards.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getNextCardTest() {
        for (int i = 0; i < 37; i++) {
            setOfCards.getNextCard();
        }
    }

    @Test
    public void resetShuffleTest() {
        Card card1;
        Card card2;
        setOfCards.reset();
        setOfCards.shuffle();
        card1 = setOfCards.getNextCard();
        setOfCards.reset();
        setOfCards.shuffle();
        card2 = setOfCards.getNextCard();
        assertNotEquals(card1, card2);
    }

    @Test
    public void setTrumpTest() {
        setOfCards.shuffle();
        Card trump = setOfCards.getNextCard();
        setOfCards.setTrump(trump);
        Card card = null;
        for (int i = 0; i < 36; i++) {
            card = setOfCards.getNextCard();
            if (card.suit == trump.suit) {
                assertTrue(card.ifTrump());
            } else {
                assertFalse(card.ifTrump());
            }
        }
        assertEquals(card, trump);
    }

    @Test
    public void reprintTest() throws UnsupportedEncodingException {
        setOfCards.shuffle();
        Card trump = setOfCards.getNextCard();
        setOfCards.setTrump(trump);
        String str;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteStream, false, "utf-8");
        System.setOut(printStream);
        setOfCards.reprint();
        str = byteStream.toString("utf-8");
        assertEquals("Колода(36)\r\nКозырь:" + trump.toString() + "\r\n", str);
    }

    @Test
    public void reprintWithoutTrumpTest() throws UnsupportedEncodingException {
        setOfCards.shuffle();
        String str;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteStream, true, "utf-8");
        System.setOut(printStream);
        setOfCards.reprint();
        str = byteStream.toString("utf-8");
        assertEquals("Колода(36)\r\n", str);
    }

    @Test
    public void reprintEmptyTest() throws UnsupportedEncodingException {

        for (int i = 0; i < 36; i++) {
            setOfCards.getNextCard();
        }
        String str;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteStream, false, "utf-8");
        System.setOut(printStream);
        setOfCards.reprint();
        str = byteStream.toString("utf-8");
        assertEquals("Колода(0)\r\n", str);
    }
}



