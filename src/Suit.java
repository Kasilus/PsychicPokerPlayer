
public enum Suit {

    Clubs('C'), Diamonds('D'), Hearts('H'), Spades('S');

    private final char name;

    Suit(char name) {
        this.name = name;
    }

    public char getName() {
        return name;
    }

    public static Suit getSuitByName(char name){
        for (Suit suit : Suit.values()){
            if (suit.name == name){
                return suit;
            }
        }
        return null;
    }
}
