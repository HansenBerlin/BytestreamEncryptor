package BankClasses.account.AccountTypes;
import java.util.List;

import BankClasses.account.*;

public class DepotAccount extends Account
{
    private List<Stock> stocks;

    public DepotAccount(int number, String holderName, List<Stock> stocks, String type) 
    {
        super(number, holderName, 0, type);        
        this.stocks = stocks;
    }

    public double getBalance()
    {
        double valueOfAllStocks = 0;
        for (Stock stock : stocks) 
        {
            valueOfAllStocks += stock.getStockCount() * stock.getStockValue();            
        }
        
        return valueOfAllStocks;
    }

    public void addStock(Stock stock)
    {
        stocks.add(stock);
    }

    public List<Stock> getStocks()
    {
        return stocks;
    }

    public void addStocks(List<Stock> stocks)
    {
        this.stocks = stocks;
    }

    

    //public void displayAccountValues()
    //{
    //    System.out.println(String.format("Account Number: %d\nAccount Holder Name: %s", number, holderName));
    //    for (int i = 0; i < stocks.length; i++)         
    //        System.out.println(String.format("Stock: %d: %s", i+1, stocks[i]));         
    //}
}
