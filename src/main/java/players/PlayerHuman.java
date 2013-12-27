package players;

import game.Card;
import game.GameDesk;
import game.Rules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PlayerHuman implements Player {

    private final String name;

    public PlayerHuman(String name){
        this.name = name;
    }

    @Override
    public ArrayList<Card> turn (GameDesk desk, ArrayList<Card> cardsOfPlayer,
                                    int numberCardsOfOpponent, String typeOfTurn) {

        ArrayList<Card> selectedCards = new ArrayList<>();
        Boolean errorsPresent = false;
        Collections.sort(cardsOfPlayer);
        String message;
        do  {
            message = getMessage(typeOfTurn, errorsPresent);
            desk.reprint(cardsOfPlayer, numberCardsOfOpponent, message);
            Scanner sc = new Scanner (System.in);
            String choose = sc.next();
            selectedCards.clear();
            if (choose.equals("0")) {
                return selectedCards;
            }
            String strAnswer[] = choose.split(",");
            errorsPresent = false;
            try {
                getSelectedCards(cardsOfPlayer, selectedCards, strAnswer);
            }
            catch (Exception e) {
                errorsPresent = true;
            }
            errorsPresent = errorsPresent || !Rules.check(desk, selectedCards, numberCardsOfOpponent, typeOfTurn);

        } while (errorsPresent);
        return selectedCards;
    }

    private String getMessage(String typeOfTurn, Boolean errorsPresent) {
        String errors = "";
        if (errorsPresent) {
            errors = " что-то неправильно, попробуйте еще раз, ";
        }
        switch (typeOfTurn) {
            case "attack":
                return errors + ", ваш ход";

            case "addCards":
                return errors + ", будете подкидывать? 0 - нет";

            case "lastAdd":
                return errors + ", противник принимает, добавите вдогонку? 0 - нет";

            case "defence":
                return  errors + ", отбивайтесь, 0 - принять";
        }
        return null;
    }

    private void getSelectedCards(ArrayList<Card> cardsOfPlayer, ArrayList<Card> result, String[] strAnswer) {
        for (String s : strAnswer){
            result.add(cardsOfPlayer.get(Integer.parseInt(s)-1));
        }
    }

    @Override
    public String getName () {
        return name;
    }


}
