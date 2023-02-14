package day1;
import java.io.Console;

public class HelloAge {
    
    public static void main(String[] args) {
        Console cons = System.console();
        //https://docs.oracle.com/en/java/javase/19/docs/api/java.base/java/io/Console.html#readLine()
        String name = cons.readLine("What is your name?");
        // String email = cons.readLine();
        System.out.printf("Hello %s\n",name);

        String age = cons.readLine("What is your age?");
        // int ageInt = (int) name; Casting is very strict in Java!
        int ageInt = Integer.parseInt(age); 
        System.out.printf("Are you less than 30 years old? %b\n",ageInt < 30);

        if (ageInt > 30){
            System.out.printf("Hello Sir!\n");
        } else if ( (ageInt < 30) && (ageInt > 0) ) {
            System.out.printf("Hello Bro!\n");
        } else {
            System.out.printf("You kid!\n");
        }

    }
}
