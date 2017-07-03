public class Calculation {

    final private Card[] deckTop;
    private int best;

    public Calculation(Card[] deckTop) {
        this.deckTop = deckTop;
    }

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

    private void checkIfBetterCombination(Card[] array){
        Combination currentCombination = Check.checkBestCombination(array);

        if (currentCombination.getWeight() > best) {
            best = currentCombination.getWeight();
        }
    }

    public int calculateBest(Card[] userHand) {

        best = 1;

        for (int i = 5; 0 <= i; i--) {
            getAllCombinations(userHand, i, 0, new Card[i]);
        }

        return best;
    }
}
