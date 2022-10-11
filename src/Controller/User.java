package Controller;

import BankClasses.account.Account;
import Interfaces.IUser;

import java.util.List;

public class User implements IUser
{
    private List<Account> userAccounts;
    private String userName;

    public void setUserValues(List<Account> userAccounts, String userName)
    {
        this.userAccounts = userAccounts;
        this.userName = userName;
    }

    public List<Account> getUserAccounts()
    {
        return userAccounts;
    }

    public String getUserName()
    {
        return userName;
    }
}
