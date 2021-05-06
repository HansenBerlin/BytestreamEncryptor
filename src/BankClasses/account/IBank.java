package BankClasses.account;
import java.io.Serializable;
import java.util.List;
import Controller.User;

public interface IBank extends Serializable
{
    public List<Account> accounts = null;  
    public List<User> users = null;

    public boolean addAccount(Account account);
    public void deleteAccount(int accountNumber);
    public int getNumberOfAccounts();
    public void listAllCustomers();
    public List<Account> getOneCustomersAccount(String name); 
}
