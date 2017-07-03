/**
 * Enumeration type for card face
 * Has two parameters : name and value of face.
 */
public enum Face {

    Two('2',2), Three('3',3), Four('4',4), Five('5',5), Six('6', 6),
    Seven('7', 7), Eight('8', 8), Nine('9', 9), Ten('T',10), Jack('J',11),
    Queen('Q',12), King('K',13), Ace('A',14);

    private char name;
    private int value;

    Face(char name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public char getName() {
        return name;
    }

    /**
     * Allow get card face by it's name.
     * It is important, when you read from input
     * name of card face, but have to compare
     * cards by faces
     * @param name name of card face
     * @return appropriate face for name
     */
    public static Face getFaceByName(char name){

        for (Face face : Face.values()){
            if (face.name == name){
                return face;
            }
        }
     return null;
    }
}
