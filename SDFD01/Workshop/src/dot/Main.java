package dot;

import java.io.Console;

public class Main {
    public static void main(String[] args) {
        
        // get user input for Equation size
        Console userCon = System.console();
        String coeffCol = userCon.readLine("How many equations (rows)?\n"); //remember readLine camelcase
        int coeffColLen = Integer.parseInt(coeffCol);
        String coeffRow = userCon.readLine("How many terms? (columns)?\n");
        int coeffRowLen = Integer.parseInt(coeffRow); //can combined into single line i.e (Integer.parseInt(cons.readline...)
        
        int[][] coeffArray = new int[coeffColLen][coeffRowLen]; //TODO: figure out col vs row
        //TODO: Change to Float 
        System.out.printf("Initialising %d by %d matrix\n",coeffColLen,coeffRowLen);

        // Check initialised coeff matrix
        for (int i = 0; i < coeffColLen; i++){
            for (int j = 0; j < coeffRowLen; j++) {
                System.out.printf("%d ",coeffArray[i][j]);
            }
            System.out.printf("\n");
        }

        // Get user input and populate array
        for (int i = 0; i < coeffColLen;i++){
            System.out.printf("Input %d weights for the Row %d, seprated by spaces: ",coeffRowLen,i+1); //combined with readline?
            String userRowInput = userCon.readLine("");
            String[] userRow = userRowInput.split(" ",coeffRowLen);
            for (int j = 0; j < coeffRowLen ;j++){
                coeffArray[i][j] = Integer.parseInt(userRow[j]);
            }
        }

        // Print User Matrix
        System.out.printf("You have keyed in %d by %d matrix:\n",coeffColLen,coeffRowLen);
        // Check initialised matrix
        for (int i = 0; i < coeffColLen;i++){
            for (int j = 0; j < coeffRowLen ;j++) {
                System.out.printf("%d ",coeffArray[i][j]);
            }
            System.out.printf("\n");
        }

        int[] varsArray = new int[coeffRowLen];
         // Check initialised vars matrix
        System.out.printf("Initialising 1 by %d matrix\n",coeffRowLen);
        for (int i = 0; i < coeffRowLen;i++){
            System.out.printf("%d \n", varsArray[i]);
        }
        
        // Get user input and populate array
        System.out.printf("Input %d variables, seprated by spaces: ",coeffRowLen);
        String userRowInput = userCon.readLine("");
        String[] userRow = userRowInput.split(" ",coeffRowLen);
            for (int i = 0; i < coeffRowLen;i++){
                varsArray[i] = Integer.parseInt(userRow[i]);
            }
        
        // Print User Matrix
        System.out.printf("You have keyed in:\n");
        for (int i = 0; i < coeffRowLen ; i++) {
            System.out.printf("%d \n", varsArray[i]);
        }

        int[] prodArray = new int[coeffColLen];
        for (int i = 0; i < coeffColLen ;i++){
            for (int k = 0; k < coeffRowLen ;k++){
                prodArray[i] += (coeffArray[i][k] * varsArray[k]);             
            }    
        }

        // Print User Matrix
        System.out.printf("The Dot Product is:\n");
        for (int i = 0; i < coeffColLen ; i++) {
            System.out.printf("%d ", prodArray[i]);
        }
    }
}
