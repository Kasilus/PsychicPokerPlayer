import java.io.*;


public class Main {

    public static void main(String[] args) {

        String input = new String();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

        while (true) {

            input = br.readLine();

            if (input.equals("")){
                break;
            }


            String[] allCards = input.split(" ");

            Card[] userHand = new Card[5];
            Card[] deckTop = new Card[5];


            fillHandAndDeck(userHand, deckTop, allCards);


            Calculation calculation = new Calculation(deckTop);
            int best = calculation.calculateBest(userHand);


            output(userHand, deckTop, best);
        }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void fillHandAndDeck(Card[] userHand, Card[] deckTop, String[] allCards) {

        int counter = 0;

        // User's hand
        for (; counter < 5; counter++) {
            userHand[counter] = new Card(
                    Face.getFaceByName(allCards[counter].charAt(0)),
                    Suit.getSuitByName(allCards[counter].charAt(1))
            );
        }

        // Top of the card deck
        for (; counter < 10; counter++) {
            deckTop[counter - 5] = new Card(
                    Face.getFaceByName(allCards[counter].charAt(0)),
                    Suit.getSuitByName(allCards[counter].charAt(1))
            );
        }

    }


    private static void output(Card[] userHand, Card[] deckTop, int best){

        StringBuffer output = new StringBuffer();

        output.append("Hand: ");
        for (Card card: userHand) {
            output.append(card).append(" ");
        }

        output.append("Deck: ");
        for (Card card: deckTop) {
            output.append(card).append(" ");
        }

        output.append("Best hand: ");
        output.append(Combination.getNameByWeight(best));

        System.out.println(output);
    }

}
