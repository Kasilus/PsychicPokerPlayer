import java.io.*;

/**
 * Main class with executable method main().
 * Read cards input from console, check all
 * possible combinations, choose best and
 * output it.
 */
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

    /**
     * Read lines from console input until empty line
     * @return input from console in the same form
     */
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


    /**
     * Fill user hand and deck's top by cards from all cards,
     * which we have taken from input
     * @param userHand array of cards in user hand
     * @param deckTop  array of cards on the deck top
     * @param allCards array of cards from line ( user hand + deck's top)
     * @throws IndexOutOfBoundsException Common for ArrayIndexOutOfBoundsException
     * and StringIndexOutOfBoundsException, which can be caught. In either case it
     * means user input exception
     */
    private static void fillHandAndDeck (Card[] userHand, Card[] deckTop, String[] allCards) throws IndexOutOfBoundsException {

        int counter = 0;

        // User hand
        for (; counter < 5; counter++) {
            userHand[counter] = new Card(
                    Face.getFaceByName(allCards[counter].charAt(0)),
                    Suit.getSuitByName(allCards[counter].charAt(1))
            );
        }

        // Deck's top
        for (; counter < 10; counter++) {
            deckTop[counter - 5] = new Card(
                    Face.getFaceByName(allCards[counter].charAt(0)),
                    Suit.getSuitByName(allCards[counter].charAt(1))
            );
        }

    }

    /**
     * Print in the console cards in user hand, on the deck's top and best combination
     * @param userHand cards in user hand
     * @param deckTop cards on the deck's top
     * @param best weight of best combination for these cards
     */
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
