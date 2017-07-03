import enums.Combination;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Check the best combination for current hand.
 */
public class Check {

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
    public static Combination checkBestCombination(Card[] cards) {

        boolean isFlush = false, isStraight = false, hasSet = false, hasPair = false;

        isFlush = checkFlush(cards);

        sort(cards);
        isStraight = checkStraight(cards);

        if (isFlush && isStraight) {

                return Combination.StraightFlush;

        }


        Set<Map.Entry<Integer, Integer>> repeats = countRepeats(cards);

        for (Map.Entry<Integer, Integer> entry : repeats) {
            switch (entry.getValue()){
                case 4 :
                    return Combination.FourOfAKind;
                case 3 :
                    if (checkFullHouse(repeats)){
                        return Combination.FullHouse;
                    }
                    hasSet = true;
                    break;
                case 2 :
                    hasPair = true;
                    break;
            }

        }

        if (isFlush){
            return Combination.Flush;
        } else if (isStraight){
            return Combination.Straight;
        } else if (hasSet){
            return Combination.ThreeOfAKind;
        }

        if (hasPair){
            if (repeats.size() == 3 ){
                return Combination.TwoPairs;
            } else {
                return Combination.OnePair;
            }
        }


        return Combination.HighestCard;

    }

    /**
     * Check flush combination
     * @param cards cards in current hand
     * @return true, if there is a flush
     */
    private static boolean checkFlush(Card[] cards) {

        char suit = cards[0].getSuit().getName();
        int i = 1;

        while ((i < 5) && (suit == cards[i].getSuit().getName())) {
            i++;
        }

        if (i == 5) {
            return true;
        }

        return false;

    }

    /**
     * Check straight combination
     * Ace can be in this combination either
     * the highest card (Ace = 14, Ace > King)
     * or the lowest (Ace = 1, Ace < 2), so
     * there is second check for this situation
     * @param cards cards in current hand
     * @return true, if there is a straight
     */
    private static boolean checkStraight(Card[] cards) {

        int min = cards[0].getFace().getValue();
        min++;

        int i = 1;
        while ((i < 5) && (min == cards[i].getFace().getValue())) {
            i++;
            min++;
        }

        if (i == 5) {
            return true;
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
            return true;
        }

        return false;

    }

    /**
     * Check full house combination
     * This is not full check, but
     * enough for this decision
     * @param repeats count of card faces' repeats
     * @return true, if there is a full house
     */
    private static boolean checkFullHouse(Set<Map.Entry<Integer, Integer>> repeats) {

        if (repeats.size() == 2){
            return true;
        }
        return false;
    }


}

