package Controller;
import BankClasses.account.Bank;
import Interfaces.IBank;
import Utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecureAccountsController implements Serializable
{
    public void saveAccountsToFile(IBank bank) throws IOException
    {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        var convert = new ObjectOutputStream(byteStream); 
        convert.writeObject(bank);
        convert.flush();
        byte[] bankAsByte = byteStream.toByteArray(); 
        
        bankAsByte = encrypt(bankAsByte);
        System.out.println("Bank object encrypted.");       
             
        var writeToFile = new FileOutputStream("bank.lock");
        convert = new ObjectOutputStream(writeToFile);
        convert.write(bankAsByte);
        convert.close();
        System.out.println("Encrypted bank object saved to file.");   
    }


    public Bank loadAccountsTFromFile() throws IOException, ClassNotFoundException
    {
        Bank returnBank = null;
        var loadFile = new FileInputStream("bank.lock");
        var inputStream = new ObjectInputStream(loadFile);
        byte[] bankAsByte = inputStream.readAllBytes();
        inputStream.close();

        bankAsByte = decrypt(bankAsByte);
        System.out.println("Bank object loaded and decrypted."); 

        try 
        {
            ByteArrayInputStream byteStream = new ByteArrayInputStream(bankAsByte);
            inputStream = new ObjectInputStream(byteStream);
            returnBank = (Bank)inputStream.readObject();
            inputStream.close();            
        } 
        catch (NullPointerException | StreamCorruptedException e) 
        {
            System.out.println(e.getMessage());
            System.out.println("Datei konnte nicht entschlüsselt werden. Falsches Passwort?");
        }       
        
        return returnBank;
    }  


    private byte[] decrypt(byte[] bankAsByte) throws IOException
    {
        String sharedKey = readPasswordFile();
        byte[] decryptionCypher = decodePassword(sharedKey);   
        bankAsByte = oneTimePadLikeEncryption(bankAsByte, decryptionCypher);

        return bankAsByte;
    }


    private byte[] encrypt(byte[] bankAsByte) throws IOException
    {
        byte[] randomXORcypher = createRandomByte(bankAsByte.length); 
        bankAsByte = oneTimePadLikeEncryption(bankAsByte, randomXORcypher);
        String sharedKey = encodePassword(randomXORcypher);
        createPasswordFile(sharedKey);

        return bankAsByte;
    }


    private byte[] oneTimePadLikeEncryption(byte[] bankAsByte, byte[] key)
    {
        for (int i = 0; i < bankAsByte.length; i++) 
        {
            int[] singleBytesInFile = convertBinary(bankAsByte[i]);    
            int[] singleBytesInPasswordString = convertBinary(key[i]); 

            for (int j = 0; j < 8; j++) 
            {    
                if((singleBytesInFile[j] == 1 && singleBytesInPasswordString[j] == 0) || 
                    (singleBytesInFile[j] == 0 && singleBytesInPasswordString[j] == 1))
                    singleBytesInFile[j] = 1;      
                else
                    singleBytesInFile[j] = 0;  
            }  
                  
            bankAsByte[i] = convertToSignedByte(singleBytesInFile);            
        }
        return bankAsByte;
    }


    private byte[] createRandomByte(int byteLength)
    {
        byte[] tempByte = new byte[byteLength];
        for (int i = 0; i < byteLength; i++)         
            tempByte[i] = (byte)(rNr(93) + 33);

        return tempByte; 
    }


    private String encodePassword(byte[] randomlyCreatedByte)
    {      
        return new String(randomlyCreatedByte, StandardCharsets.UTF_8);        
    }


    private byte[] decodePassword(String sharedKey)
    {      
        return sharedKey.getBytes();
    }


    private void createPasswordFile(String sharedKey) throws IOException
    {
        FileWriter sharedKeyFile = new FileWriter("sharedKey.txt");
        File filepath = new File("sharedKey.txt");
        sharedKeyFile.write(sharedKey);
        System.out.println("Password saved to file sharedKey.txt in path " + filepath.getAbsolutePath());  
        sharedKeyFile.close();      
    }


    private String readPasswordFile() throws IOException
    {
        String sharedKey = "";
        File filepath = new File("sharedKey.txt");
        if (filepath.exists())        
            sharedKey = new String(Files.readAllBytes(Paths.get(filepath.getAbsolutePath())));   

        System.out.println("Password read from file sharedKey.txt in path " + filepath.getAbsolutePath());  

        return sharedKey;
    }


    // Change algorithm according to OS https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#SecureRandom
    private int rNr(int toIncluded)
    {
        try
        {
            return SecureRandom.getInstance("NativePRNG").nextInt(toIncluded + 1);
            //return SecureRandom.getInstance("Windows-PRNG").nextInt(toIncluded + 1);

        }
        catch(NoSuchAlgorithmException e)
        {
            System.out.println("PRNG Error: " + e.getMessage());
            return 0;
        }
    }
       

    private int[] convertBinary(byte signedByte)
    {
        int num = Byte.toUnsignedInt(signedByte);
        int[] binary = new int[8];
        int index = 7;
        while(num > 0)
        {
          binary[index--] = num%2;
          num = num/2;
        }
        return binary;
    }


    private byte convertToSignedByte(int[] binary)
    {
        int multiplier = 1;
        int value = 0;
        for (int i = 7; i >= 0; i--) 
        {
            if (binary[i] == 1)
                binary[i] = multiplier;
            multiplier *= 2;            
        }
        for (int i : binary)         
            value += i;            
        byte test = (byte)value;
        return test;      
    }
}
