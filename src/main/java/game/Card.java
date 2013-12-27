package game;

public class Card implements Comparable<Card> {
    public final SuitOfCard suit;
    public final RankOfCard rank;
    private boolean trump;

    public Card(SuitOfCard suit, RankOfCard rank) {
        this.suit = suit;
        this.rank = rank;
        trump = false;
    }

    public boolean ifSuitSame(Card card) {
        SuitOfCard suit1 = this.suit;
        SuitOfCard suit2 = card.suit;
        return suit1.equals(suit2);
    }

    void setTrump(boolean trump){
        this.trump = trump;
    }
    public boolean ifTrump () {
        return trump;
    }

    @Override
    public int compareTo(Card card) {
        int cost = this.rank.cost - card.rank.cost;
        if (this.ifTrump()) {
            cost += 15;
        }
        if (card.ifTrump()) {
            cost -= 15;
        }
        return cost;
    }

    @Override
    public String toString() {
        return this.rank.symbol + this.suit.symbol;
    }

}


