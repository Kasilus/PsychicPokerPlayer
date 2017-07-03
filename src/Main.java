import java.io.*;


public class Main {

    public static void main(String[] args) {

        String input = readFromConsole();

        String[] lines = input.split("\n");

        long startTime = System.nanoTime();

        for (String line : lines) {

            String[] allCards = line.split(" ");

            Card[] userHand = new Card[5];
            Card[] deckTop = new Card[5];

            try {
                fillHandAndDeck(userHand, deckTop, allCards);
            }catch (IndexOutOfBoundsException e){
                System.out.println("Incorrect input!");
                return;
            }

            Calculation calculation = new Calculation(deckTop);
            int best = calculation.calculateBest(userHand);


            output(userHand, deckTop, best);

        }

        System.out.println();

        long estimatedTime = System.nanoTime() - startTime;

        System.out.println("Time : " + estimatedTime);

    }

    private static String readFromConsole() {

        StringBuilder full = new StringBuilder();

        try (InputStreamReader inputStreamReader = new InputStreamReader(System.in);
             BufferedReader bf = new BufferedReader(inputStreamReader)) {

            full = new StringBuilder();
            String line;


            while ((line = bf.readLine()) != null) {
                if (line.isEmpty()){
                    break;
                }
                full.append(line).append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return full.toString();

    }


    private static void fillHandAndDeck (Card[] userHand, Card[] deckTop, String[] allCards) throws IndexOutOfBoundsException {

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


    private static void output(Card[] userHand, Card[] deckTop, int best) {

        StringBuffer output = new StringBuffer();

        output.append("Hand: ");
        for (Card card : userHand) {
            output.append(card).append(" ");
        }

        output.append("Deck: ");
        for (Card card : deckTop) {
            output.append(card).append(" ");
        }

        output.append("Best hand: ");
        output.append(Combination.getNameByWeight(best));

        System.out.println(output);
    }

}
