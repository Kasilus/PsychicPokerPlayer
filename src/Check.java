import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Check {

    private static Card[] sort(Card[] cards) {

        Arrays.sort(
                cards,
                (a, b) -> Integer.compare(a.getFace().getValue(), b.getFace().getValue())
        );

        return cards;
    }

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

    public static Combination checkBestCombination(Card[] cards) {

        boolean isFlush = false, isStraight = false, hasSet = false, hasPair = false;

        isFlush = checkFlush(cards);

        sort(cards);
        isStraight = checkStraight(cards);

        if (isFlush && isStraight) {
            if (cards[0].getFace().getValue() != 10) {
                return Combination.StraightFlush;
            } else {
                return Combination.RoyalFlush;
            }
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

        // check if ace is first in combination
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

    private static boolean checkFullHouse(Set<Map.Entry<Integer, Integer>> repeats) {

        if (repeats.size() == 2){
            return true;
        }
        return false;
    }


}

