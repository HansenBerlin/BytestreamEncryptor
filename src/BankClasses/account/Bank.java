package BankClasses.account;
import Controller.User;
import Interfaces.IBank;

import java.util.ArrayList;
import java.util.List;

public class Bank implements IBank
{
    private final List<Account> accounts = new ArrayList<Account>();
    public List<User> users = new ArrayList<User>();
    
    public boolean addAccount(Account account)
    {
        if (accounts.size() >= 1000)
            return false;
        accounts.add(account);
        return true;
    }  

    public void addUser(User user)
    {
        users.add(user);
    }
    
    public void deleteAccount(int accountNumber)
    {
        for (int i = 0; i < 0; i++) 
        {
            if (accounts.get(i).number == accountNumber)
                accounts.remove(i);
        }
    } 

    public int getNumberOfAccounts()
    {
        return accounts.size();
    }

    public void listAllCustomers()
    {
        for (int i = 0; i < accounts.size(); i++) 
        {
            System.out.println("-------------------------------------");            
            System.out.println("Kontotyp:    " + accounts.get(i).getAccountType());
            System.out.println("Kontonummer: " + accounts.get(i).getNumber());
            System.out.println("Kundenname:  " + accounts.get(i).getHolderName());
            System.out.printf("Kontostand:  %.2f€\n", accounts.get(i).getBalance());
            System.out.println("-------------------------------------");
        }
    }

    public List<Account> getOneCustomersAccount(String name)
    {
        List<Account> customerAccounts = new ArrayList<Account>();  
        for (Account account : accounts) 
        {
            if (account.holderName.equals(name))
                customerAccounts.add(account);            
        }
        return customerAccounts;
    }
}
