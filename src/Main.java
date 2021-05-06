import java.io.IOException;

import BankClasses.account.*;
import Controller.AccountFabric;
import Controller.SecureAccountsController;

public class Main 
{
    private static IBank bank;
    
    public static void main(String[] args) throws IOException, ClassNotFoundException 
    {  
        boolean createAndSaveDummyAccounts = true;        
        var secureAccounts = new SecureAccountsController();

        if (createAndSaveDummyAccounts)
        {
            bank = new Bank();
            var accountFabric = new AccountFabric(bank);
            accountFabric.createRandomAccounts(20);        
            bank.listAllCustomers();
            secureAccounts.saveAccountsToFile(bank);
        }        

        var bankLoaded = secureAccounts.loadAccountsTFromFile();
        bankLoaded.listAllCustomers();
    }  
}