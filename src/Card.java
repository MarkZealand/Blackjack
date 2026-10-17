public class Card {


    private final Suit cardSuit;
    private final Rank cardRank;

    public Card(Suit suit, Rank rank) {
        this.cardSuit = suit;
        this.cardRank = rank;
    }

    public Suit getSuit() {
        return cardSuit;
    }
    public Rank getRank() {
        return cardRank;
    }
    public int getCardValue(){
        return cardRank.getValue();
    }
    public static enum Suit {
        Hearts("♥"),
        Spades("♠"),
        Diamonds("♦"),
        Clubs("♣");

        private final String value;
        Suit(String value){ this.value = value; }
        public String getValue() { return value; }
    }
    public static enum Rank {
        Two(2),
        Three(3),
        Four(4),
        Five(5),
        Six(6),
        Seven(7),
        Eight(8),
        Nine(9),
        Ten(10),
        Jack(10),
        Queen(10),
        King(10),
        Ace(11);

        private final int value;

        Rank(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
