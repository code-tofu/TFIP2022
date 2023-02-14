package shufflehard;
import java.util.Random;

public class Deck {
    private int deckSize = 52;
    private int numSuits = 4;
    private int cardPerSuit = 13; //decksize/numSuits; divide by zero since not initiated (initiated default to zerp)
    private boolean isDrawn = false;
    private boolean isShuffled = false;
    private Card[] cardArr  = new Card[deckSize]; //create (reference?) array for uninitialised cards. undefined sized?  
    
    public Deck(){ //overloaded constructor for default values.
        //initialises cardArr with values and suits
        this.initDeck(); //try for fun
    }

    private void initDeck(){
        for( int  i = 0; i < cardPerSuit; i++){
            // Card cardArr[i] = new Card(10,"C"); //Type mismatch: cannot convert from Card to Card[]
            // Cannot be resused and cumbersome
            Card c = new Card((i + 1),"Clubs");
            cardArr[i] = c; //duplicate local variable
            c = new Card((i + 1),"Diamonds");
            cardArr[i + 1*cardPerSuit] = c;
            c = new Card((i + 1),"Hearts");
            cardArr[i + 2*cardPerSuit] = c;
            c = new Card((i + 1),"Spades");
            cardArr[i + 3*cardPerSuit] = c;
        }  
    }

    public void fan(){
        System.out.printf("Fanning the deck:\n");
        for(int i = 0; i < deckSize; i++){
            if(cardArr[i] != null){
                String cardVal = Integer.toString(cardArr[i].getValue()) + " of " + cardArr[i].getSuit() ;
                System.out.printf("%s\n",cardVal);
            } 
            else {System.out.printf("0");} //to test for null
        }
        System.out.printf("\n"); //two breaks when full deck
    }
    
    public void shuffle(){
        Random rand = new Random();
        int shufflePos = 0;
        Card temp = new Card(1,"Spades"); //Ace of Spades as default
        for(int i = 0; i < deckSize; i++){
            shufflePos = rand.nextInt(51); //returns between 0 and 51
            temp = cardArr[shufflePos];
            cardArr[i] = cardArr[shufflePos];
            cardArr[shufflePos] = temp;
        }//TODO: check if this is okay. don't need to swap values even though private?     
    }

    public void draw(int drawNum){ //draw from index 0
        for(int i = 0; i < drawNum; i++){
            String cardVal = "Draw: " + Integer.toString(cardArr[i].getValue()) + " of " + cardArr[i].getSuit() ;
            System.out.printf("%s\n",cardVal);
        } 

        for(int i = 0; i < deckSize;i++){//TODO
            if(i + drawNum < deckSize) {
                cardArr[i] = cardArr[i + drawNum];
                cardArr[i + drawNum] = null;
            } else {
                cardArr[i] = null;
            }
        }
    }

    public void reshuffle(){
        this.initDeck();
        this.shuffle();
    }
}

