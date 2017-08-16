import enums.PokerCombination;

/**
 * Allow user to get the best combination
 * for current user hand and deck's top position.
 */
public class Calculation {

    final private Card[] deckTop;
    private int best;

    /**
     * Constructor for specifying deck's top.
     * With this, you can get the best combination of
     * every user hand and this deck'top.
     * @param deckTop 5 top cards of deck
     */
    public Calculation(Card[] deckTop) {
        this.deckTop = deckTop;
    }

    /**
     * Recursive method to get and check all
     * possible combinations, which can be in
     * user hand (total 32)
     * @param userHand cards in the user hand (always same)
     * @param cardsFromHand total of cards from the first user hand in current case
     * @param startPosition start position for selection of cards
     * @param result cards in the user hand for current case (different sizes for each situation)
     */
    private void getAllCombinations(Card[] userHand, int cardsFromHand, int startPosition, Card[] result) {

        if (cardsFromHand == 0) {

            Card[] currentHand = new Card[5];

            int i = 0;
            while (i < result.length) {
                currentHand[i] = result[i];
                i++;
            }

            while (i < currentHand.length) {
                currentHand[i] = deckTop[i - result.length];
                i++;
            }

            checkIfBetterCombination(currentHand);

            return;
        }

        for (int i = startPosition; i <= userHand.length - cardsFromHand; i++) {
            result[result.length - cardsFromHand] = userHand[i];
            getAllCombinations(userHand, cardsFromHand - 1, i + 1, result);
        }
    }

    /**
     * Check best combination for current case. If it is,
     * assign new value for variable 'best'.
     * @param cardsCurrent cards in user hand for current case
     */
    private void checkIfBetterCombination(Card[] cardsCurrent){

        PokerCombination currentCombination = Check.checkBestCombination(cardsCurrent);

        if (currentCombination.getWeight() > best) {
            best = currentCombination.getWeight();
        }

    }

    /**
     * Public method, which allows user calculate best combination
     * by his hand
     * @param userHand cards in user hand
     * @return weight of the best combination
     */
    public int calculateBest(Card[] userHand) {

        best = 1;

        for (int i = 5; 0 <= i; i--) {
            getAllCombinations(userHand, i, 0, new Card[i]);
        }

        return best;
    }
}
