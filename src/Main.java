import java.io.IOException;
import BankClasses.account.*;
import Controller.AccountFabric;
import Controller.SecureAccountsController;
import Interfaces.IBank;

public class Main 
{
    private static IBank bank;
    
    public static void main(String[] args) throws IOException, ClassNotFoundException 
    {  
        boolean createAndSaveDummyAccounts = true;     
        boolean loadAccounts = true;   
        var secureAccounts = new SecureAccountsController();

        if (createAndSaveDummyAccounts)
        {
            bank = new Bank();
            var accountFabric = new AccountFabric(bank);
            accountFabric.createRandomAccounts(3);        
            bank.listAllCustomers();
            secureAccounts.saveAccountsToFile(bank);
        }        

        if (loadAccounts)
        {
            var bankLoaded = secureAccounts.loadAccountsTFromFile();
            bankLoaded.listAllCustomers();
        }
    }  
}