package players;

import game.Card;
import game.GameDesk;
import game.Rules;

import java.util.ArrayList;
import java.util.Collections;


public class PlayerBot implements Player {

    private final String name;

    public PlayerBot(String name){
        this.name = name;
    }

    @Override
    public ArrayList<Card> turn (GameDesk desk, ArrayList<Card> cardsOfPlayer,
                                    int numberCardsOfOpponent, String typeOfTurn) {
        Collections.sort(cardsOfPlayer);

        switch (typeOfTurn) {
            case "attack":
                return attackTurn(cardsOfPlayer);

            case "addCards":
                return addCardsTurn(desk, cardsOfPlayer, numberCardsOfOpponent);

            case "lastAdd":
                return addCardsTurn(desk, cardsOfPlayer, numberCardsOfOpponent);

            case "defence":
                return defenceTurn(desk, cardsOfPlayer);
        }
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    private ArrayList<Card> attackTurn (ArrayList<Card> cardsOfPlayer){

         ArrayList<Card> result = new ArrayList<>();
         result.add(cardsOfPlayer.get(0));
         return result;
    }
    private ArrayList<Card> addCardsTurn (GameDesk desk, ArrayList<Card> cardsOfPlayer, int numberCardsOfOpponent){
        ArrayList<Card> result = new ArrayList<>();
        for (Card card : cardsOfPlayer) {
            if (card.ifTrump()) break;
            if (Rules.ifContainSameRankCard(desk.getAllCardsOnDesk(),card)) {
                result.add(card);
            }
            if (!Rules.checkNotTooManyCards(desk, result, numberCardsOfOpponent)) {
                result.remove(card);
                break;
            }
        }
        return result;
    }

    private ArrayList<Card> defenceTurn (GameDesk desk, ArrayList<Card> cardsOfPlayer) {
        ArrayList<Card> coveredCards = new ArrayList<>();
        ArrayList<Card> result = new ArrayList<>();

        for (int i = desk.getCardsOfDefence().size(); i < desk.getCardsOfAttack().size(); i++ ){
            coveredCards.add(desk.getCardsOfAttack().get(i));

        }


        for (Card coveredCard: coveredCards){
            for (Card coverCard : cardsOfPlayer ) {
                if (Rules.firstCardCoverSecond(coverCard,coveredCard)) {
                    result.add(coverCard);
                    cardsOfPlayer.remove(coverCard);
                    break;
                }
            }
        }
        if (result.size() < coveredCards.size()) {
            cardsOfPlayer.addAll(result);
            result.clear();
        }
        return result;
    }
}
