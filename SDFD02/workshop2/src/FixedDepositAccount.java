public class FixedDepositAccount extends BankAccount{
    private float interest = 3;
    private boolean isChangedInterest = false;
    private int months = 6 ;
    private boolean isChangedMonths = false;

    public FixedDepositAccount(String name, Float balance){
        super(name, balance);
    }

    public FixedDepositAccount(String name, Float balance, Float interest){
        super(name, balance);
        this.interest = interest;
    }

    public FixedDepositAccount(String name, Float balance, Float interest, Integer duration){
        super(name, balance);
        this.interest = interest;
        this.months = duration;
        
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        if(this.isChangedInterest == true){
            System.out.println("Interest can only be changed once");
            return;
        }
        this.interest = interest;
        this.isChangedInterest = true;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        if(this.isChangedMonths == true){
            System.out.println("Duration can only be changed once");
            return;
        }
        this.months = months;
        this.isChangedMonths = true;
    }
    
    @Override
    public void withdraw(double amount){}//NOP

    @Override
    public void deposit(double amount){} //NOP
    
    @Override
    public float getAccBal() {
        return this.accBal + (this.accBal * this.interest/12*this.months/100);} //accBal needs to be protected instead of private
        // need to call instance of interest


}





