package shufflehard;

public class Card { //Card should not extend Deck in case of constructor chaining (TBC)
    private int value; //A to K, 1-13
    private String suit; // CDHS


    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    public int getValue() { return value;}
    public void setValue(int value) { this.value = value;}

    public String getSuit() { return suit;}
    public void setSuit(String suit) { this.suit = suit;}
    
    
}
