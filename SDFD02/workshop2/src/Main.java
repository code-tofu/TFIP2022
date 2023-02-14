public class Main {
    public static void main(String[] args) {
        
        //Test Default
        BankAccount acc1 = new BankAccount("Mr.Robot", 1000);
        System.out.println(acc1.getAccNum());
        System.out.println(acc1.getAccBal());
        System.out.println(acc1.getAccName());
        System.out.println(acc1.isClosed());
        System.out.println(acc1.getDateCreate());

        acc1.deposit(500.50);
        System.out.println(acc1.getAccBal());
        acc1.withdraw(499.50);
        System.out.println(acc1.getAccBal());
        System.out.println(acc1.getTransactions());
        System.out.println();

        //Test Overloaded
        BankAccount acc2 = new BankAccount("Mr.BMO");
        System.out.println(acc2.getAccNum());
        System.out.println(acc2.getAccBal());
        System.out.println(acc2.getAccName());
        System.out.println(acc2.isClosed());
        System.out.println(acc2.getDateCreate());

        acc2.deposit(500.50);
        System.out.println(acc2.getAccBal());
        acc2.withdraw(499.50);
        System.out.println(acc2.getAccBal());
        System.out.println(acc2.getTransactions());
        System.out.println();

        FixedDepositAccount acc3 = new FixedDepositAccount("Mr Stark",1000.05f, 4.0f, 12);
        System.out.println(acc3.getAccNum());
        System.out.println(acc3.getAccBal());
        System.out.println(acc3.getAccName());
        System.out.println(acc3.isClosed());
        System.out.println(acc3.getDateCreate());
        System.out.println(acc3.getInterest());
        System.out.println(acc3.getMonths());

        acc3.deposit(500.50);
        System.out.println(acc3.getAccBal());
        acc3.withdraw(499.50);
        System.out.println(acc3.getAccBal());


        acc3.setInterest(5);
        System.out.println(acc3.getInterest());
        System.out.println(acc3.getAccBal());
        acc3.setInterest(6);
        System.out.println(acc3.getInterest());
        System.out.println(acc3.getAccBal());

        acc3.setMonths(5);
        System.out.println(acc3.getMonths());
        System.out.println(acc3.getAccBal());
        acc3.setMonths(6);
        System.out.println(acc3.getMonths());
        System.out.println(acc3.getAccBal());
        System.out.println();
        
        FixedDepositAccount acc4 = new FixedDepositAccount("Mr Wayne",1000.05f, 4.0f);
        System.out.println(acc4.getAccNum());
        System.out.println(acc4.getAccBal());
        System.out.println(acc4.getAccName());
        System.out.println(acc4.isClosed());
        System.out.println(acc4.getDateCreate());
        System.out.println(acc4.getInterest());
        System.out.println(acc4.getMonths());

        acc4.deposit(500.50);
        System.out.println(acc4.getAccBal());
        acc4.withdraw(499.50);
        System.out.println(acc4.getAccBal());
        System.out.println();

        FixedDepositAccount acc5 = new FixedDepositAccount("Mr Robotnik",1000.05f);
        System.out.println(acc5.getAccNum());
        System.out.println(acc5.getAccBal());
        System.out.println(acc5.getAccName());
        System.out.println(acc5.isClosed());
        System.out.println(acc5.getDateCreate());
        System.out.println(acc5.getInterest());
        System.out.println(acc5.getMonths());

        acc5.deposit(500.50);
        System.out.println(acc5.getAccBal());
        acc5.withdraw(499.50);
        System.out.println(acc5.getAccBal());
        System.out.println();


    }
}
