public enum Combination {

    HighestCard(1,"highest-card"), OnePair(2, "one-pair"), TwoPairs(3,"two-pairs"), ThreeOfAKind(4, "three-of-a-kind"), Straight(5, "straight"),
    Flush(6, "flush"), FullHouse(7, "full-house"), FourOfAKind(8, "four-of-a-kind"), StraightFlush(9,"straight-flush"), RoyalFlush(10,"royal-flush");

    private int weight;
    private String name;

    Combination(int weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public static String getNameByWeight(int weight){
        for (Combination combination : Combination.values()){
            if (combination.weight == weight){
                return combination.name;
            }
        }
        return null;
    }
}
