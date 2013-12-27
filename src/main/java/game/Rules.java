package game;

import java.util.ArrayList;

public class Rules {

    public static boolean check(GameDesk desk, ArrayList<Card> selectedCards,
                                int numberCardsOfOpponent, String typeOfTurn) {

        switch (typeOfTurn) {
            case "attack":
                return checkNotTooManyCards(desk, selectedCards, numberCardsOfOpponent)
                        & checkAllCardsSameRank(selectedCards) & !selectedCards.isEmpty();

            case "addCards":
                return checkNotTooManyCards(desk, selectedCards, numberCardsOfOpponent)
                        & checkCardsPresentOnDesk(desk, selectedCards);
            case "lastAdd":
                return checkNotTooManyCards(desk, selectedCards, numberCardsOfOpponent)
                        & checkCardsPresentOnDesk(desk, selectedCards);

            case "defence":
                return checkDefenceRules(desk, selectedCards);
        }
        return false;
    }

    public static boolean checkNotTooManyCards(GameDesk desk, ArrayList<Card> selectedCards,
                                               int numberCardsOfOpponent) {
        ArrayList<Card> cardsOfAttack = desk.getCardsOfAttack();
        ArrayList<Card> cardsOfDefence = desk.getCardsOfDefence();
        int numberCardsOfAttack = cardsOfAttack.size();
        int numberCardsOfDefence = cardsOfDefence.size();
        int numberSelectedCards = selectedCards.size();
        if ((numberSelectedCards + numberCardsOfAttack) > 6) {
            return false;
        }
        if ((numberSelectedCards + numberCardsOfAttack) >
                numberCardsOfOpponent + numberCardsOfDefence) {
            return false;
        }
        return true;
    }

    private static boolean checkAllCardsSameRank(ArrayList<Card> selectedCards) {
        Card firstCard = selectedCards.get(0);
        for (Card card : selectedCards) {
            if (card.rank != firstCard.rank) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkCardsPresentOnDesk(GameDesk desk, ArrayList<Card> selectedCards) {
        ArrayList<Card> allCardsOnDesk = desk.getAllCardsOnDesk();
        for (Card card : selectedCards) {
            if (!ifContainSameRankCard(allCardsOnDesk, card)) {
                return false;
            }
        }
        return true;
    }

    public static boolean ifContainSameRankCard(ArrayList<Card> listOfCards, Card card) {

        for (Card c : listOfCards) {
            if (c.rank == card.rank) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkDefenceRules(GameDesk desk, ArrayList<Card> selectedCards) {
        ArrayList<Card> cardsOfAttack = desk.getCardsOfAttack();
        ArrayList<Card> cardsOfDefence = desk.getCardsOfDefence();
        int numberCardsToCover = cardsOfAttack.size() - cardsOfDefence.size();
        int positionCardsToCover = cardsOfDefence.size();
        if (selectedCards.size() != numberCardsToCover) {
            return false;
        }
        Card cardOfAttack;
        for (Card cardOfDefence : selectedCards) {
            cardOfAttack = cardsOfAttack.get(positionCardsToCover);
            if (!firstCardCoverSecond(cardOfDefence, cardOfAttack)) {
                return false;
            }
            positionCardsToCover++;
        }
        return true;
    }

    public static boolean firstCardCoverSecond(Card card1, Card card2) {
        if (card1.ifSuitSame(card2) && card1.compareTo(card2) > 0) {
            return true;
        }
        return  (card1.ifTrump() && !card2.ifTrump());

    }
}
