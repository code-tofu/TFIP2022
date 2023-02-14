import java.util.ArrayList;
import java.util.Scanner;

public class CartTwo {

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
                        listCart(newcart);
                        break;
                    case "add":
                        addCart(newcart,cmdinput);
                        break;
                    case "delete":
                        deleteCart(newcart,cmdinput);
                        break;
                    case "exit":
                        return;
                    default:
                        System.out.println("Usage, Use Commands: list, add, delete");
                }
            }
        }
    }

    public static void listCart(ArrayList<String> newcart){ //has to be static
       if(newcart.size() ==0){
            System.out.println("Cart is Empty");
            return;
        }
        for(int i = 0 ; i < newcart.size(); i++){
            System.out.printf(" %d. %s\n", i+1, newcart.get(i)); //printf is fmt string
       }
    }

    public static void addCart(ArrayList<String> newcart,String[] cmdinput) {
        if(cmdinput.length<2){
            System.out.println("Usage: add <item>");
            return;
        }
        String[] cartinput = cmdinput[1].split(",");
        for (int i = 0; i < cartinput.length; i++) { //.length is not coloured.
            if (newcart.contains(cartinput[i].toLowerCase().trim())) {
                System.out.printf("%s is already in your cart\n", cartinput[i].trim());
            } else {
                System.out.printf("%s has been added to your cart\n", cartinput[i].trim());
                newcart.add(cartinput[i].toLowerCase().trim());
            }
        }
    }

    public static void deleteCart(ArrayList<String> newcart, String[] cmdinput){
        if(cmdinput.length<2){
            System.out.println("Usage: delete <item>");
            return;
        }
        try {
            int index = Integer.parseInt(cmdinput[1]);
            if(index<newcart.size()+1){
                System.out.printf("%s has been removed \n", newcart.remove(index-1));
            } else{
                System.out.println("Incorrect item index");
            }
        } catch (NumberFormatException e){
            System.out.println("Incorrect item index");
        }
    }
}
