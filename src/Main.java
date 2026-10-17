import java.util.Scanner;

void main() {
    Scanner input = new Scanner(System.in);
    showMenu(input);
}

public void newGame(Scanner input){

    //Session er en omgang ved bordet, fra man sætter sig ned med startbeløb,
    // til man forlader det fordi man går fallit eller ikke gider spille ved det ene bord mere.

    boolean exitProgram = false;

    while(!exitProgram){

        Player player = new Player();
        Dealer dealer =  new Dealer();
        boolean quitSession = false;

        while(!quitSession){
            Deck.initDeck();
            player.clearHand();
            dealer.clearHand();

            double betAmount = promptBet(input, player);
            //De første 2 kort til spiller og dealer
            firstRound(player, dealer);
            if(player.hand.getHandValue() == 21){
                System.out.println("Blackjack!");
                handleRoundEnd(player, dealer, betAmount);
            }
            else {
                //Spiller loop, indtil bust eller stand
                GameState result = playerTurn(input, player);

                if (result == GameState.Stand) {
                    dealer.playTurn();
                }
                handleRoundEnd(player, dealer, betAmount);

                if(!promptNewRound(input) || player.bankroll > 0)
                    quitSession = true;
            }
        }
    }
}

public boolean promptNewRound(Scanner input){
    input.nextLine();
    System.out.print("Spil en runde mere? (ja/nej)");
    if(input.nextLine().equalsIgnoreCase("nej")){
        return false;
    }
    return true;
}

public void handleRoundEnd(Player player, Dealer dealer, double betAmount) {
    if(player.hand.getHandValue() == dealer.hand.getHandValue())
    {
        player.bankroll += betAmount;
        System.out.println("Uafgjort! Dit indskud på: " + betAmount + " blev returneret til din saldo.");
    }
    else if(player.hand.getHandValue() == 21){
        player.bankroll += betAmount * 2.5;
        System.out.println("Du vandt: " + betAmount * 2.5);
        System.out.println("Ny saldo: " + player.bankroll);
    }
    else if(player.hand.getHandValue() > dealer.hand.getHandValue()){
        player.bankroll += betAmount * 2.0;
        System.out.println("Du vandt: " + betAmount * 2.0);
        System.out.println("Ny saldo: " + player.bankroll);
    }
    else {
        System.out.println("Du tabte: " + betAmount);
        System.out.println("Ny saldo: " + player.bankroll);
    }


}
private static boolean isRoundOver(Player player) {
    boolean gameOver;
    System.out.println("Beklager, du tabte!");
    System.out.println("Din nye saldo: " + player.bankroll);
    gameOver = true;
    return gameOver;
}

private GameState playerTurn(Scanner input, Player player) {
    while (true) {
        int choice = promptAction(input);

        switch (choice) {
            case 1:
                player.stand();
                return GameState.Stand;
            case 2:
                player.hit();
                if(player.hand.getHandValue() == 0){
                    System.out.println("Bust!");
                    return GameState.Bust;
                }
                else if(player.hand.getHandValue() == 21){
                    System.out.println("Blackjack!");
                    return GameState.Blackjack;
                }
                break;
            default:
                System.out.println("Ugyldigt input");
        }
    }
}

public enum GameState {
    Stand,
    Bust,
    Blackjack
}

private static void firstRound(Player player, Dealer dealer) {

    player.addCard(Deck.dealCard());
    dealer.addCard(Deck.dealCard());
    player.addCard(Deck.dealCard());
    dealer.addCard(Deck.dealCard());
    player.showHand();
    dealer.showHand();
}

public void showMenu(Scanner input){
    System.out.println("=======Vælg Handling=======");
    System.out.println("1. Nyt spil");
    System.out.println("2. Læs regler");
    System.out.println("3. Afslut spil");
    System.out.print("Indtast (1-3): ");
    switch(input.nextInt()){
        case 1:
            newGame(input);
            break;
        case 2:
            showRules(input);
            break;
        default:
            System.exit(0);
    }
}

public void showRules(Scanner input){
    System.out.println("Regler...");
    showMenu(input);
}
public double promptBet(Scanner input, Player player){
    System.out.println("Din nuværende saldo: " + player.bankroll);
    System.out.print("Indtast beløb du ønsker at satse: ");

    double betAmount = 0;
    while(betAmount == 0) {
        betAmount = input.nextInt();
        if (player.bankroll >= betAmount) {
            player.bankroll -= betAmount;
        } else
            showWrongBet();
    }
    return betAmount;
}

public void showWrongBet(){
    System.out.println("Ikke nok penge på konto...");
}

public int promptAction(Scanner input){
    System.out.println("=======Vælg Handling=======");
    System.out.println("1. Stå");
    System.out.println("2. Nyt kort");
    System.out.println("3. Sats dobbelt");
    System.out.println("4. Split hånd i to");
    System.out.print("Indtast (1-4): ");

    return input.nextInt();
}

public void promptNewGame(Scanner input) {
    System.out.println("Ønsker du at spille et nyt spil? (ja/nej)");

    if(input.next().equals("ja")){
        newGame(input);
    }
    else{
        System.exit(0);
    }
}


