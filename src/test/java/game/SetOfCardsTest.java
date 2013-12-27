package game;


import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by looser on 12/27/13.
 */
public class SetOfCardsTest  {

    @Test
    public void testIsEmpty() {
        SetOfCards setOfCards = new SetOfCards();
        assertFalse(setOfCards.isEmpty());
    }
}