import enums.PokerCombination;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Check the best combination for current hand.
 */
public class Check {


    interface CheckCombination{
       PokerCombination check();
    }


    /**
     * Public method, which allows checking of the best
     * combination. Algorithm was made for the fastest
     * search of the best combination.
     * For example, when we check royal and straight flush,
     * we check straight and flush combination too. But these
     * combos are smaller, than four-of-a-kind and full-house.
     * So as not do same things some times, we remember info
     * in boolean and check them, when time comes.
     * @param cards cards in current hand
     * @return the best combination for current hand
     */
    public static PokerCombination checkBestCombination(Card[] cards) {


        sort(cards);
        Set<Map.Entry<Integer, Integer>> repeats = countRepeats(cards);

        CheckCombination[] checks = new CheckCombination[]{
                new CheckCombination() { public PokerCombination check() { return checkRoyalFlush(cards); } },
                new CheckCombination() { public PokerCombination check() { return checkStraightFlush(cards); } },
                new CheckCombination() { public PokerCombination check() { return checkFourOfAKind(cards, repeats); } },
                new CheckCombination() { public PokerCombination check() { return checkFullHouse(cards, repeats); } },
                new CheckCombination() { public PokerCombination check() { return checkFlush(cards); } },
                new CheckCombination() { public PokerCombination check() { return checkStraight(cards); } },
                new CheckCombination() { public PokerCombination check() { return checkThreeOfAKind(cards, repeats); } },
                new CheckCombination() { public PokerCombination check() { return checkTwoPairs(cards, repeats); } },
                new CheckCombination() { public PokerCombination check() { return checkOnePair(cards, repeats); } },

        };

        for (CheckCombination checkCombination : checks){
           if (checkCombination.check() != null){
               return checkCombination.check();
           }
        }

        return PokerCombination.HighestCard;

    }

    /**
     * Sort cards in hand in ascending order by faces.
     * Uses for further simple check of the best combination
     * @param cards cards in current hand
     * @return sorted cards in current hand
     */
    private static Card[] sort(Card[] cards) {

        Arrays.sort(
                cards,
                (a, b) -> Integer.compare(a.getFace().getValue(), b.getFace().getValue())
        );

        return cards;
    }

    /**
     * Count repeats of card faces in hand for further
     * simple check of the best combination. Made, because
     * many poker combinations are based on faces' repeats
     * @param cards cards in current hand
     * @return set of map entries for
     * further simple check of the best combination
     */
    private static Set<Map.Entry<Integer, Integer>> countRepeats(Card[] cards) {

        Map<Integer, Integer> repeats = new HashMap<>();

        for (int i = 0; i < 5; i++) {

            int value = cards[i].getFace().getValue();

            if (repeats.containsKey(value)) {
                repeats.put(value, repeats.get(value) + 1);
            } else {
                repeats.put(value, 1);
            }

        }

        return repeats.entrySet();
    }

    private static PokerCombination checkRoyalFlush(Card[] cards){

        if ((checkStraightFlush(cards) != null) && cards[0].getFace().getValue() == 10){
            return PokerCombination.RoyalFlush;
        }

        return null;

    }

    private static PokerCombination checkStraightFlush(Card[] cards){

        if ((checkFlush(cards) != null) && (checkStraight(cards) != null)){
            return PokerCombination.StraightFlush;
        }

        return null;

    }

    private static PokerCombination checkFourOfAKind(Card[] cards, Set<Map.Entry<Integer, Integer>> repeats) {

        for (Map.Entry<Integer, Integer> entry : repeats){
            if (entry.getValue() == 4){
                return PokerCombination.FourOfAKind;
            }

        }

        return null;
    }

    private static PokerCombination checkFullHouse(Card[] cards, Set<Map.Entry<Integer, Integer>> repeats) {

        for (Map.Entry<Integer, Integer> entry : repeats) {
            if (entry.getValue() == 3 && repeats.size() == 2) {
                return PokerCombination.FullHouse;
            }

        }

        return null;
    }

    private static PokerCombination checkFlush(Card[] cards) {

        char suit = cards[0].getSuit().getName();
        int i = 1;

        while ((i < 5) && (suit == cards[i].getSuit().getName())) {
            i++;
        }

        if (i == 5) {
            return PokerCombination.Flush;
        }

        return null;

    }

    private static PokerCombination checkStraight(Card[] cards) {

        int min = cards[0].getFace().getValue();
        min++;

        int i = 1;
        while ((i < 5) && (min == cards[i].getFace().getValue())) {
            i++;
            min++;
        }

        if (i == 5) {
            return PokerCombination.Straight;
        }

        // check if ace is the lowest in combination
        if (cards[4].getFace().getValue() == 14){
            min = 1;
            min++;

            i = 0;
            while ((i < 4) && (min == cards[i].getFace().getValue())) {
                i++;
                min++;
            }
        }

        if (i == 4){
            return PokerCombination.Straight;
        }

        return null;

    }

    private static PokerCombination checkThreeOfAKind(Card[] cards, Set<Map.Entry<Integer, Integer>> repeats) {

        for (Map.Entry<Integer, Integer> entry : repeats) {
            if (entry.getValue() == 3 && repeats.size() == 3) {
                return PokerCombination.ThreeOfAKind;
            }

        }

        return null;
    }

    private static PokerCombination checkTwoPairs(Card[] cards, Set<Map.Entry<Integer, Integer>> repeats) {

        for (Map.Entry<Integer, Integer> entry : repeats) {
            if (entry.getValue() == 2 && repeats.size() == 3) {
                return PokerCombination.TwoPairs;
            }

        }

        return null;

    }

    private static PokerCombination checkOnePair(Card[] cards, Set<Map.Entry<Integer, Integer>> repeats) {

        for (Map.Entry<Integer, Integer> entry : repeats) {
            if (entry.getValue() == 2 && repeats.size() == 4) {
                return PokerCombination.OnePair;
            }

        }

        return null;

    }


}

