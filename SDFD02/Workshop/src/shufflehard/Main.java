package shufflehard;
//using arrays instead of list

public class Main {
    public static void main(String[] args) {

    Deck mydeck = new Deck();
    mydeck.fan();
    mydeck.shuffle();
    mydeck.fan();
    mydeck.draw(10);
    mydeck.fan();
    mydeck.draw(10);
    mydeck.fan();
    mydeck.reshuffle();
    mydeck.fan();
    mydeck.draw(52);
    mydeck.fan();
}
    
}
