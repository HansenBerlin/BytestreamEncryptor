package Interfaces;
import BankClasses.account.Account;
import Controller.User;

import java.io.Serializable;
import java.util.List;

public interface IBank extends Serializable
{
    List<Account> accounts = null;
    List<User> users = null;

    boolean addAccount(Account account);
    void deleteAccount(int accountNumber);
    int getNumberOfAccounts();
    void listAllCustomers();
    List<Account> getOneCustomersAccount(String name);
}
