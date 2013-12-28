package game;


import org.junit.Test;
import static org.junit.Assert.*;

public class SetOfCardsTest  {

    @Test
    public void testIsEmpty() {
        SetOfCards setOfCards = new SetOfCards();
        assertFalse(setOfCards.isEmpty());
        for (int i=0; i<36; i++){
            setOfCards.getNextCard();
        }
        assertTrue(setOfCards.isEmpty());
    }

}