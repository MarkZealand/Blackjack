public class Player {
    public final Hand hand = new Hand();
    public static double bankroll = 20.00;
    public static void bet(int amount){
        bankroll -= amount;
    }

    public void hit(){
        Card newCard = Deck.dealCard();
        System.out.println("Du trak: " + newCard.getSuit().getValue() + newCard.getRank().getValue());
        addCard(newCard);
        showHand();
    }

    public static void stand(){

    }

    public static void doubleUp(){

    }

    public static void split(){

    }

    public void addCard(Card card){
        hand.addCard(card);
        //System.out.println("Kort tilføjet: " + card.getRank() + " " + card.getSuit());
    }
    public void clearHand(){
        hand.clearCards();
    }
    public void showHand(){
        System.out.println("Spillers hånd: ");
        for(Card card : hand.getCards()){
            System.out.print("[" + card.getSuit().getValue() + card.getRank().getValue() + "] ");
        }
        System.out.println();
    }
}
