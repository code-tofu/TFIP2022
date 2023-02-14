package guesser;
import java.io.Console;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random(); //create new random object/instance
        int numTries = 5;
        int answer = rand.nextInt(20); //return int from random object
        System.out.printf("Random: %d\n", answer);

        while(numTries >0){
            Console userCon = System.console();
            System.out.printf("Guess a number between 1 and 20! (%d tries left)",numTries);
            String userInput = userCon.readLine("What is your guess?");
            int userGuess = Integer.parseInt(userInput);
            // does readline take a format string? yes.
            // use  int userGuess = userCon.readLine("What is your guess?").parseInt(userInput);?
            // userGuess == answer ?
            if ((userGuess > 20) || (userGuess < 1)) {
                System.out.printf("Number has to be between 1 and 20\n");
                continue;
            } else if (userGuess == answer){
                System.out.printf("You win!\n");
                return;
            } else {
                numTries -= 1;
                if(userGuess> answer){
                    System.out.printf("Guess Lower!\n");
                } else {
                    System.out.printf("Guess Higher!\n");
                }
            }
        }
        System.out.printf("You Lose! The number is %d",answer);
    }
}

