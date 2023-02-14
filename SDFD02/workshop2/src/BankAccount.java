import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class BankAccount {

    private final String accName;
    private final String accNum;
    protected float accBal;
    private boolean isClosed;
    private Date dateCreate;
    private Date dateClose;
    private ArrayList<String> transactions = new ArrayList<>();

    
    public BankAccount(String name, float initialBal) {
        Random rand = new Random();
        int accRand = rand.nextInt(0, Integer.MAX_VALUE);
        this.accNum = Integer.toString(accRand);
        this.accName = name;
        this.accBal = initialBal;
        Date date = new Date();
        this.dateCreate = date;
    }

    public BankAccount(String name) {
        this(name, 0); //constructor chaining
    } 

    

    public String getAccName() {return accName;}
    public String getAccNum() {return accNum;}

    public float getAccBal() {return accBal;}

    public boolean isClosed() {return isClosed;}
    public void setClosed(boolean isClosed) {this.isClosed = isClosed;}

    public Date getDateCreate() {return dateCreate;}
    public Date getDateClose() {return dateClose;}

    

    @Override
    public String toString() {
        return "BankAccount [transactions=" + transactions + "]";
    }

    public void deposit(double amount){
        if(isClosed){
            System.out.println("Account is closed");
            throw new IllegalArgumentException();
        }
        if(amount<0){
            System.out.println("Deposit must be positive amount");
            throw new IllegalArgumentException();
        }

        this.accBal += amount;
        Date date = new Date();  
        this.transactions.add("Deposit %.2f at %s".formatted(amount,date.toString()));
    }

    public void withdraw(double amount){
        if(isClosed){
            System.out.println("Account is closed");
            throw new IllegalArgumentException();
        }
        if(amount<0){
            System.out.println("Withdraw must be positive amount");
            throw new IllegalArgumentException();
        }
        if(amount>this.accBal){
            System.out.println("Insufficient Funds for Withdraw Amount");
            throw new IllegalArgumentException();
        }
        this.accBal -= amount;
        Date date = new Date();
        this.transactions.add("Withdraw %.2f at %s".formatted(amount,date.toString()));

    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }
    

    
}
