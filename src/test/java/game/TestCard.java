package game;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 02.12.13
 * Time: 23:36
 * some change
 */
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCard {
    private final Card card = new Card(SuitOfCard.DIAMONDS, RankOfCard.JACK);
    private static final Card[] fullSetOfCards = new Card[36];

    @BeforeClass
    public static void initOfSetOfCards(){
        int i = 0;
        for (SuitOfCard s : SuitOfCard.values()) {
            for (RankOfCard r : RankOfCard.values()){
                fullSetOfCards [i] = new Card(s,r);
                i++;
            }
        }
    }

    @Test
    public void testCompareTo(){
        assertEquals(-1, fullSetOfCards[0].compareTo(fullSetOfCards[1]));
        assertEquals(0, fullSetOfCards[3].compareTo(fullSetOfCards[12]));
        assertEquals(2, card.compareTo(fullSetOfCards[3]));
        fullSetOfCards[0].setTrump(true);
        assertTrue(fullSetOfCards[0].compareTo(fullSetOfCards[1])>0);
        assertTrue(fullSetOfCards[15].compareTo(fullSetOfCards[0])<0);

    }

    @Test
    public void testIfSuitSame() {
        assertEquals(true, fullSetOfCards[0].ifSuitSame(fullSetOfCards[8]));
        assertEquals(false, fullSetOfCards[10].ifSuitSame(fullSetOfCards[30]));
    }

    @Test
    public void testToString(){
        assertEquals(" 8♥" ,fullSetOfCards[2].toString());
        assertEquals("10♠",fullSetOfCards[22].toString());
        assertEquals(" В♣",fullSetOfCards[32].toString());
    }

    @Test
    public void testSetGetTrump(){
        fullSetOfCards[15].setTrump(true);
        assertTrue(fullSetOfCards[15].ifTrump());
        fullSetOfCards[15].setTrump(false);
        assertFalse(fullSetOfCards[15].ifTrump());

    }
}
