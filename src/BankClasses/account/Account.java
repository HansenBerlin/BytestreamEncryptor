package BankClasses.account;

import java.io.Serializable;

public abstract class Account implements Serializable
{
    protected int number;
    protected String holderName;
    protected double balance;
    protected String accountType;

    public Account(int number, String holderName, double balance, String accountType)
    {
        this.number = number;
        this.holderName = holderName;      
        this.balance = balance; 
        this.accountType = accountType; 
    }

    public int getNumber()
    {
        return number;
    }

    public String getHolderName()
    {
        return holderName;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public void setNumber(int accountNumber)
    {
        this.number = accountNumber;
    } 

    public void setHolderName(String accountHolderName)
    {
        this.holderName = accountHolderName;
    }

    public String getAccountType()
    {
        return accountType;
    }

    public void displayAccountValues()
    {
    }  
}
