package Interfaces;
import java.util.List;

import BankClasses.account.Account;

public interface IUser 
{
    public List<Account> userAccounts = null;
    public String userName = null;   
    public List<Account> getUserAccounts();
    public String getUserName();
    public void setUserValues(List<Account> userAccounts, String userName);
 
}
