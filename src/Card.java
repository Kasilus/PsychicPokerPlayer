import enums.Face;
import enums.Suit;

/**
 * POJO class for playing card
 */
public class Card {

    private Face face;
    private Suit suit;


    public Card(Face face, Suit suit) {
        this.face = face;
        this.suit = suit;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }

    /**
     * Output of the card information
     * @return two-character code of the card (first - face, second - suit)
     */
    @Override
    public String toString() {
        return face.getName() + "" + suit.getName();
    }
}
