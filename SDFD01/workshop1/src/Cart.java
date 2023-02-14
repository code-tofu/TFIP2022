import java.util.ArrayList;
import java.util.Scanner;

public class Cart {

    public static void main(String[] args) {

        System.out.println("Welcome to your shopping cart");
        ArrayList<String> newcart = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.print(">");
            String[] cmdinput = scan.nextLine().split(" ",2);
            if(cmdinput.length>0){
                switch(cmdinput[0].toLowerCase()){
                    case "list":
                        if(newcart.size() ==0){
                            System.out.println("Cart is Empty");
                            break;
                        }
                        for(int i = 0 ; i < newcart.size(); i++){
                            System.out.printf(" %d. %s\n", i+1, newcart.get(i)); //printf is fmt string
                        }
                        break;
                    //
                    case "add":
                        String[] cartinput = cmdinput[1].split(",");
                        for(int i = 0; i < cartinput.length;i++){ //.length is not coloured.
                            if(newcart.contains(cartinput[i].toLowerCase().trim())){
                                System.out.printf("%s is already in your cart\n",cartinput[i].trim());
                            } else {
                                System.out.printf("%s has been added to your cart\n",cartinput[i].trim());
                                newcart.add(cartinput[i].toLowerCase().trim());
                            }
                        }
                        break;
                    //
                    case "delete":
                        try {
                            int index = Integer.parseInt(cmdinput[1]);
                            if(index<newcart.size()+1){
                                System.out.printf("%s has been removed \n", newcart.remove(index-1));
                            } else{
                                System.out.println("Incorrect item index");
                            }
                            break;
                        } catch (NumberFormatException e){
                            System.out.println("Incorrect item index");
                            break;
                        }
                    //
                    default:
                        if((cmdinput.length>0) && (cmdinput[0].equals("exit")))return;
                        System.out.println("Usage, Use Commands: list, add, delete");
                }
            }

        }
    }
}
