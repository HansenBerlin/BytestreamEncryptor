package BankClasses.account.AccountTypes;
import BankClasses.account.Account;

public class SavingsAccount extends Account
{
    private double balance;
    private int interest;

    public SavingsAccount(int number, String holderName, double balance, int interest, String type) 
    {
        super(number, holderName, balance, type);
        this.interest = interest;
    }   

    public int getInterest()
    {
        return interest;
    }

    public void setInterest(int interest)
    {
        this.interest = interest;
    }

    public void displayAccountValues()
    {
        System.out.println(String.format("Account Number: %d\nAccount Holder Name: %s\nAccount Balance: %2f\nCurrent interest: %d", number, holderName, balance, interest));                
    }    
}
