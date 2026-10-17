public class Dealer {
    public final Hand hand = new Hand();

    public void playTurn(){
        boolean dealersTurn = true;

        while(dealersTurn){
            int value = 0;
            for(Card card : hand.getCards()) {
                value = value + card.getCardValue();
            }
            if(value < 17)
                hit();
            else if(value >= 17)
                dealersTurn = false;
        }
        stand();
    }

    public void hit(){
        Card newCard = Deck.dealCard();
        System.out.println("Dealer trak: " + newCard.getSuit().getValue() + newCard.getRank().getValue());
        addCard(newCard);
        showHand();
    }

    public void stand(){
        System.out.println("Dealer valgte at stå.");
    }

    public void addCard(Card card){
        hand.addCard(card);
    }
    public void clearHand(){
        hand.clearCards();
    }

    public void showHand(){
        System.out.println("Dealers hånd:");
        for (int i = 0; i < hand.getCards().size(); i++) {
            if(i == 0)
                System.out.print("[??] ");
            else
                System.out.print("[" + hand.getCards().get(i).getSuit().getValue() + hand.getCards().get(i).getRank().getValue() + "] ");
        }
        System.out.println();
    }

}
