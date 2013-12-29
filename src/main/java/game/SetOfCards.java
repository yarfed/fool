package game;

import java.util.ArrayList;
import java.util.Collections;

class SetOfCards {
    private final ArrayList<Card> setOfCards;
    private final ArrayList<Card> fullSetOfCards;
    private Card trump = null;
    SetOfCards() {
        setOfCards = new ArrayList<Card>();
        for (SuitOfCard s : SuitOfCard.values()) {
            for (RankOfCard r : RankOfCard.values()) {
                setOfCards.add(new Card(s, r));
            }
        }
        fullSetOfCards = new ArrayList<Card>();
        fullSetOfCards.addAll(setOfCards);
    }

    boolean isEmpty() {
        return (setOfCards.size() == 0);
    }

    Card getNextCard() throws IllegalArgumentException {
        Card nextCard;
        if (!setOfCards.isEmpty()) {
            nextCard = setOfCards.get(0);
            setOfCards.remove(0);
            return nextCard;
        }
        throw new IllegalArgumentException("Empty");
    }

    void reset() {
        setOfCards.clear();
        setOfCards.addAll(fullSetOfCards);
        for (Card card:fullSetOfCards){
             card.setTrump(false);
        }
        trump = null;
    }

    void shuffle() {
        Collections.shuffle(setOfCards);
    }

    void setTrump(Card trump) {
        this.trump = trump;
        for (Card card:fullSetOfCards){
            if (card.ifSuitSame(trump)) {
                card.setTrump(true);
            }
        }
        setOfCards.add(trump);
    }
    public void reprint() {
        int size = setOfCards.size();
        System.out.println("Колода(" + size +")");
        if (size > 0 && !(trump==null)) {
            System.out.println("Козырь:" + trump.toString());
        }
    }
}
