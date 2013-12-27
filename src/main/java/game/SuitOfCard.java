package game;

public enum SuitOfCard {
    HEARTS("♥"),
    DIAMONDS("♦"),
    SPADES("♠"),
    CLUBS("♣");

    public final String symbol;

    SuitOfCard(String symbol) {
        this.symbol = symbol;
    }
}
