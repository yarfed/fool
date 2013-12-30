package game;

import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
public class GameDeskTest {
     GameDesk desk;
    Card card1 = new Card(SuitOfCard.SPADES,RankOfCard.ACE);
    Card card2 = new Card(SuitOfCard.DIAMONDS,RankOfCard.KING);
    Card card3 = new Card(SuitOfCard.CLUBS,RankOfCard.SEVEN);
    Card card4 = new Card(SuitOfCard.HEARTS,RankOfCard.JACK);
    Card card5 = new Card(SuitOfCard.SPADES,RankOfCard.QUEEN);
    ArrayList<Card> someCards1 = new ArrayList<>();
    ArrayList<Card> someCards2 = new ArrayList<>();
    ArrayList<Card> someCardsAll = new ArrayList<>();

    @Before
    public void setUp()  {
        SetOfCards setOfCardsMock = mock(SetOfCards.class);
        doNothing().when(setOfCardsMock).reprint();
         desk = new GameDesk(setOfCardsMock);
        someCards1.add(card1);
        someCards1.add(card2);
        someCards1.add(card3);
        someCards2.add(card4);
        someCards2.add(card5);
        someCardsAll.addAll(someCards1);
        someCardsAll.addAll(someCards2);
        desk.clear();
    }

    @Test
    public void getSetCardsTest() {

        ArrayList<Card> cardsOfAttack;
        ArrayList<Card> cardsOfDefence;
        ArrayList<Card> allCards;
        desk.addAttackCard(someCards1);
        desk.addDefenceCard(someCards2);
        cardsOfAttack = desk.getCardsOfAttack();
        cardsOfDefence= desk.getCardsOfDefence();
        allCards = desk.getAllCardsOnDesk();
        assertEquals(someCards1, cardsOfAttack);
        assertEquals(someCards2, cardsOfDefence);
        assertEquals(someCardsAll, allCards);
    }

    @Test
    public void reprintTest() throws Exception {

        String str;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteStream, false, "utf-8");
        System.setOut(printStream);
        desk.reprint(someCards1, 3, " test message");
        str = byteStream.toString("utf-8");
        assertEquals("Оппонент:        ☺   ☺   ☺\r\n" +
                "\r\n" +
                "Стол:          \r\n" +
                "Бито:          \r\n" +
                "\r\n" +
                "Ваши карты:     Т♠  К♦  7♣ \r\n" +
                "Игрок test message\r\n", str);
    }

}
