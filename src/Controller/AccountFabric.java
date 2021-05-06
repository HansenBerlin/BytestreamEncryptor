package Controller;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import BankClasses.account.*;
import BankClasses.account.AccountTypes.*;
import Interfaces.IBank;

public class AccountFabric
{
    private String[] names = {"Robert", "Nico", "Danny", "Corentin", "Hannes", "Kamilla"};
    private IBank bank;

    public AccountFabric(IBank bank)
    {
        this.bank = bank;
    }      

    public void createRandomAccounts(int numbersOfAccounts)
    {
        for (int i = 0; i < numbersOfAccounts; i++)
        {            
            if (!bank.addAccount(getRandomAccountType()))
                break;       
        }
    }  

    private Account getRandomAccountType()
    {
        switch (rNr(1,3)) 
        {
            case 1:
                return new SavingsAccount(rNr(10000000, 30000000), names[rNr(0, 5)], rNr(0, 100000), rNr(0, 3), "Sparkonto");     
            case 2:
                return new GiroAccount(rNr(10000000, 30000000), names[rNr(0, 5)], rNr(0, 100000), rNr(0, 4), rNr(0, 10000), "Girokonto");     
            case 3:
                return new DepotAccount(rNr(10000000, 30000000), names[rNr(0, 5)], createRandomStocks(), "Depotkonto");
            default:
                return null;
        }
    }

    private List<Stock> createRandomStocks()
    {
        List<Stock> randomStocks = new ArrayList<>();       

        for (int i = 0; i < 10; i++) 
        {
            randomStocks.add(new Stock("Stock " + i, rNr(1, 10000), rNr(1, 100)));            
        }

        return randomStocks;
    }

    private int rNr(int from, int toIncluded)
    {
        return ThreadLocalRandom.current().nextInt(from, toIncluded + 1);
    }
}
