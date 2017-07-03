/**
 * Enumeration type for card suit
 * Has one parameter : name.
 */
public enum Suit {

    Clubs('C'), Diamonds('D'), Hearts('H'), Spades('S');

    private final char name;

    Suit(char name) {
        this.name = name;
    }

    public char getName() {
        return name;
    }

    /**
     * Allow get card suit by name.
     * @param name name of suit
     * @return card suit if it exists
     */
    public static Suit getSuitByName(char name){
        for (Suit suit : Suit.values()){
            if (suit.name == name){
                return suit;
            }
        }
        return null;
    }
}
