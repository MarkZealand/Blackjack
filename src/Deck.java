import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    static ArrayList<Card> deck = new ArrayList<>();

    public static void initDeck() {
        deck.clear();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
        shuffle();
    }

    public static void shuffle() {
        Collections.shuffle(deck);
    }

    public static Card dealCard() {
        Card card = deck.get(0);
        deck.remove(0);
        return card;
    }
}
