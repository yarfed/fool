package game;

public enum RankOfCard {
    SIX(" 6", 6),
    SEVEN(" 7", 7),
    EIGHT(" 8", 8),
    NINE(" 9", 9),
    TEN("10", 10),
    JACK(" В", 11),
    QUEEN(" Д", 12),
    KING(" К", 13),
    ACE(" Т", 14);

    public final String symbol;
    public final int cost;

    RankOfCard(String symbol, int cost) {
        this.cost = cost;
        this.symbol = symbol;
    }
}
