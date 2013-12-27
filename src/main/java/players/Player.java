package players;

import game.Card;
import game.GameDesk;

import java.util.ArrayList;

public interface Player {

    public ArrayList<Card> turn (GameDesk desk, ArrayList<Card> cardsOfPlayer,
                                 int numberCardsOfOpponent, String typeOfTurn);


    public String getName ();
}
