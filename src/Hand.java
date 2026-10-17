import java.util.ArrayList;

public class Hand {

    private final ArrayList<Card> cards = new ArrayList<Card>();

    public Hand() {
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
    public void addCard(Card card) {
        cards.add(card);
    }
    public void clearCards(){
        cards.clear();
    }

    public int getHandValue() {
        int value = 0;
        for(Card card : getCards()){
            value = value + card.getCardValue();
        }
        if(value == 21){
            return 21;
        }
        else if(value > 21) {
            return 0;
        }
        else {
            return value;
        }
    }
}
