package day1;

public class ArrMain {

    public static void main(String[] args) {
        
        String[] names = new String[5];
        String[] fruits = {"apple","orange","pear","mango"};

        for (int i = 0; i < fruits.length; i++){
            System.out.printf("[%d]: %s\n",i,fruits[i]);
        }

        String[][] market ={{"apple","orange","pear","mango"}, {"brocolli","carrot","tomato","corn"}};
        for (int i = 0; i < market.length; i++){
            System.out.printf("[%d]: %s\n",i,market[i]);
            for (int j = 0; j< market[i].length; j++){
                System.out.printf("[%d][%d]: %s\n",i,j,market[i][j]);
            }
        }
    
    }
    
}
