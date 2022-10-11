package Interfaces;

import BankClasses.account.Account;

import java.util.List;

public interface IUser 
{
    List<Account> userAccounts = null;
    String userName = null;
    List<Account> getUserAccounts();
    String getUserName();
    void setUserValues(List<Account> userAccounts, String userName);
 
}
