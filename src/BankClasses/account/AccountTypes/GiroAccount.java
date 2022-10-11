package BankClasses.account.AccountTypes;
import BankClasses.account.Account;

public class GiroAccount extends Account
{
    private int interest;
    private double maxDebt;

    public GiroAccount(int number, String holderName, double balance, int interest, double maxDebt, String type) 
    {
        super(number, holderName, balance, type);
        this.interest = interest;
        this.maxDebt = maxDebt;
    } 

    public int getInterest()
    {
        return interest;
    }

    public double getMaxDebt()
    {
        return maxDebt;
    }

    public void setMaxDebt(double maxDebt)
    {
        this.maxDebt = maxDebt;
    }

    public void setInterest(int interest)
    {
        this.interest = interest;
    }

    public void displayAccountValues()
    {
        System.out.println(String.format("Account Number: %d\nAccount Holder Name: %s\nAccount Balance: %2f", number, holderName, balance));                
    }    
}
