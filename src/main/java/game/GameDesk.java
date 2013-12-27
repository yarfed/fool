package game;

import java.util.ArrayList;

public class GameDesk {
    private ArrayList<Card> cardsOfAttack = new ArrayList<Card>();
    private ArrayList<Card> cardsOfDefence = new ArrayList<Card>();
    private SetOfCards setOfCards;

    GameDesk (SetOfCards setOfCards){
        this.setOfCards = setOfCards;
    }

    public ArrayList<Card> getCardsOfAttack() {
        return cardsOfAttack;
    }

    public ArrayList<Card> getCardsOfDefence() {
        return cardsOfDefence;
    }

    public ArrayList<Card> getAllCardsOnDesk() {
        ArrayList<Card> result = new ArrayList<>();
        result.addAll(cardsOfAttack);
        result.addAll(cardsOfDefence);
        return result;
    }

    void addAttackCard(ArrayList<Card> addingCards) {
        cardsOfAttack.addAll(addingCards);
    }

    void addDefenceCard(ArrayList<Card> addingCards) {
        cardsOfDefence.addAll(addingCards);
    }

    void clear() {

        cardsOfAttack.clear();
        cardsOfDefence.clear();
    }
    public void reprint(ArrayList<Card> cardsOfPlayer, int numberCardsOfOpponent, String message) {
        System.out.print("Оппонент:     ");
        String cardsOfOpponent="";
        for (int i = 0; i < numberCardsOfOpponent; i++){
            cardsOfOpponent +=("   ☺");
        }

        System.out.println(cardsOfOpponent);

        setOfCards.reprint();
        System.out.println();
        System.out.println("Стол:          " + setOfCardsToPrint(cardsOfAttack));
        System.out.println("Бито:          " + setOfCardsToPrint(cardsOfDefence));
        System.out.println();
        System.out.println("Ваши карты:    " + setOfCardsToPrint(cardsOfPlayer));
        System.out.println ("Игрок" + message);
    }

    public  String setOfCardsToPrint (ArrayList<Card> setOfCards){
        String result = "";
        for (Card c : setOfCards ) {
            result += c.toString() + " ";
        }
        return result;
    }
}
