package BankClasses.account;
import java.io.Serializable;

public class Stock implements Serializable
{
    private String stockName;
    private int stockCount;
    private double stockValue;

    public Stock(String stockName, int stockCount, double stockValue)
    {
        this.stockName = stockName;
        this.stockCount = stockCount;
        this.stockValue = stockValue;
    }

    public String getStockName()
    {
        return stockName;
    }

    public int getStockCount()
    {
        return stockCount;
    }

    public double getStockValue()
    {
        return stockValue;
    }

    public void setStockName(String stockName)
    {
        this.stockName = stockName;
    }

    public void setStockCount(int stockCount)
    {
        this.stockCount = stockCount;
    }

    public void setStockValue(double stockValue)
    {
        this.stockValue = stockValue;
    }
    
}
