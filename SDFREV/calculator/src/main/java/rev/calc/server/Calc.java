package rev.calc.server;

public class Calc {

    public static double calculate(String command, double[] input) throws ArithmeticException{
        double result = 0.0;
        switch(command){
            case "a":
            case "add":
                result = add (input);
                break;
            case "s":
            case "subtract":
                result = subtract(input);
                break;
            case "m":
            case "multiply":
                result = multiply(input);
                break;
            case "d":
            case "divide":
                result = divide(input);
                break;
            default:
                return Double.NaN;
        }
        return result;        
    }

    public static double add(double[] input){
        double answer = 0.0;
        for (double i: input){
            answer += i;
        }
        return answer;
    }

    public static double subtract(double[] input){
        double answer = input[0];
        for (int i = 1; i < input.length; i++ ){
            answer -= input[i];
        }
        return answer;
    }

    public static double multiply(double[] input){
        double answer = input[0];
        for (int i = 1; i < input.length; i++ ){
            answer *= input[i];
        }
        return answer;
    }

    public static double divide(double[] input){
        double answer = Double.NaN;
        for (int i = 0; i < input.length; i++ ){
            if(input[i] == 0) return Double.NaN;
            if (i == 0) answer = input[i];
            answer /= input[i];
        }
        return answer;
    }
  
}


