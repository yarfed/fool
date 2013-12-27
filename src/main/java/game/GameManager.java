package game;
import players.PlayerBot;
import players.PlayerHuman;
import players.Player;

import java.io.UnsupportedEncodingException;
import java.util.*;

class GameManager {
    private ArrayList<Card> cardsOfAttackPlayer = new ArrayList<Card>();
    private ArrayList<Card> cardsOfDefencePlayer = new ArrayList<Card>();
    final private SetOfCards setOfCards = new SetOfCards();
    final private GameDesk gameDesk = new GameDesk(setOfCards);
    private Player attackPlayer = new PlayerHuman("Игрок");
    private Player defencePlayer = new PlayerBot("Компьютер");
    private ArrayList<Card> selectedCards;
    private int numberCardsOfDefence;
    private int numberCardsOfAttack;
    String game() {

        firstDistribution();

        while (true) {
            numberCardsOfDefence = cardsOfDefencePlayer.size();
            selectedCards = attackPlayer.turn(gameDesk,  cardsOfAttackPlayer, numberCardsOfDefence, "attack");
            moveCardsFromAttackPlayerToDesk();
            if (checkWinner(cardsOfAttackPlayer)) {
                return attackPlayer.getName();
            }
            while (true) {
                numberCardsOfAttack = cardsOfAttackPlayer.size();
                selectedCards = defencePlayer.turn(gameDesk, cardsOfDefencePlayer, numberCardsOfAttack, "defence");
                if (selectedCards.isEmpty()) {
                    numberCardsOfDefence = cardsOfDefencePlayer.size();
                    selectedCards = attackPlayer.turn(gameDesk, cardsOfAttackPlayer, numberCardsOfDefence, "lastAdd");
                    moveCardsFromAttackPlayerToDesk();
                    if (checkWinner(cardsOfAttackPlayer)) {
                        return attackPlayer.getName();
                    }
                    moveCardsFromDeskToDefencePlayer();
                    distribution();
                    break;
                }
                moveCardsFromDefencePlayerToDesk();
                if (checkWinner(cardsOfDefencePlayer)) {
                    return defencePlayer.getName();
                }
                numberCardsOfDefence = cardsOfDefencePlayer.size();
                selectedCards = attackPlayer.turn(gameDesk, cardsOfAttackPlayer, numberCardsOfDefence, "addCards");
                if (selectedCards.isEmpty()) {
                    gameDesk.clear();
                    distribution();
                    switchPlayers();
                    break;

                }
                moveCardsFromAttackPlayerToDesk();
                if (checkWinner(cardsOfAttackPlayer)) {
                    return attackPlayer.getName();
                }
            }
        }
    }

    private void distribution() {
        Card nextCard;
        while ((cardsOfAttackPlayer.size()) < 6 && !setOfCards.isEmpty()) {
            nextCard = setOfCards.getNextCard();
            cardsOfAttackPlayer.add(nextCard);
        }
        while (cardsOfDefencePlayer.size() < 6 && !setOfCards.isEmpty()) {
            nextCard = setOfCards.getNextCard();
            cardsOfDefencePlayer.add(nextCard);
        }
    }

    private void moveCardsFromDeskToDefencePlayer() {
        ArrayList<Card> cardsOfAttack = gameDesk.getCardsOfAttack();
        ArrayList<Card> cardsOfDefence = gameDesk.getCardsOfDefence();
        cardsOfDefencePlayer.addAll(cardsOfDefence);
        cardsOfDefencePlayer.addAll(cardsOfAttack);
        gameDesk.clear();
    }

    private void moveCardsFromAttackPlayerToDesk() {
        for (Card card : selectedCards) {
            cardsOfAttackPlayer.remove(card);
        }
        gameDesk.addAttackCard(selectedCards);
        selectedCards.clear();
    }

    private void moveCardsFromDefencePlayerToDesk() {
        for (Card card : selectedCards) {
            cardsOfDefencePlayer.remove(card);
        }
        gameDesk.addDefenceCard(selectedCards);
        selectedCards.clear();
    }

    private boolean checkWinner(ArrayList<Card> cardsOfPlayer) {
        return setOfCards.isEmpty() && cardsOfPlayer.isEmpty();
    }

    private void switchPlayers() {
        Player tempForPlayer;
        ArrayList<Card> tempForCards;
        tempForPlayer = attackPlayer;
        attackPlayer = defencePlayer;
        defencePlayer = tempForPlayer;
        tempForCards = cardsOfAttackPlayer;
        cardsOfAttackPlayer = cardsOfDefencePlayer;
        cardsOfDefencePlayer = tempForCards;
    }

    private void firstDistribution() {
        setOfCards.reset();
        setOfCards.shuffle();
        Card nextCard;
        for (int i = 0; i < 6; i++) {
            nextCard = setOfCards.getNextCard();
            cardsOfAttackPlayer.add(nextCard);
            nextCard = setOfCards.getNextCard();
            cardsOfDefencePlayer.add(nextCard);
        }
        Card trump = setOfCards.getNextCard();
        setOfCards.setTrump(trump);

    }

    public static void main(String args[])  {

        GameManager gameManager = new GameManager();
        System.out.println(gameManager.game() + " - победитель!!!");
    }

}
