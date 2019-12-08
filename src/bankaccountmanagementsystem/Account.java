/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountmanagementsystem;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hoang
 */
public class Account {
    private int accountID;
    private int customerID;
    private int bankID;
    private double balance;

    public Account() {
    }

    public Account(int accountID, int customerID, int bankID, double balance) {
        this.accountID = accountID;
        this.customerID = customerID;
        this.bankID = bankID;
        this.balance = balance;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getBankID() {
        return bankID;
    }

    public void setBankID(int bankID) {
        this.bankID = bankID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return accountID + " " + customerID + " " + bankID + " " + balance;
    }
    
    // doc danh sach Account tu file
    public void loadDataAccount(String filename, ArrayList<Account> listAccount){
        try{
            Scanner sc = new Scanner(new File(filename));
            while(sc.hasNextLine()){
                int AccountID = sc.nextInt();
                if(AccountID != -1){
                    int CustomerID = sc.nextInt();
                    int BankID = sc.nextInt();
                    double Balance = sc.nextDouble();
                    Account temp = new Account(AccountID, CustomerID, BankID, Balance);
                    listAccount.add(temp);                    
                }
                else {
                    break;
                }

            }
            sc.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    // ghi danh sach Account vao file
    public void genDataAccount(String filename, ArrayList<Account> listAccount){
        try{
            PrintWriter out = new PrintWriter(filename);
            out.flush();
            for(Account ac : listAccount){
                int AccountID = ac.getAccountID();
                out.println(AccountID);
                int CustomerID = ac.getCustomerID();
                out.println(CustomerID);
                int BankID = ac.getBankID();
                out.println(BankID);
                double Balance = ac.getBalance();
                out.println(Balance);
            }
            out.println(-1);
            out.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
        // kiem tra Customer da ton tai chua?
    public Customer checkCustomer(int CustomerID, ArrayList<Customer> listCustomer){
        for(Customer cu : listCustomer){
            if(cu.getCustomerID() == CustomerID){
                return cu;
            }
        }
        return null;
    }
    
    // kiem tra Account thuoc Bank da ton tai chua?
    public Account searchAccountByID(int AccountID, int BankID, ArrayList<Account> listAccount){
        for(Account ac : listAccount){
            if(ac.getAccountID() == AccountID && ac.getBankID() == BankID){
                return ac;
            }
        }
        return null;
    }
    
    // tim kiem Account theo name of customer
    public Account searchAccountByName(Scanner sc, String Name, ArrayList<Customer> listCustomer, ArrayList<Account> listAccount){
        for(Customer cu : listCustomer){
            if(cu.getName().equalsIgnoreCase(Name)){
                System.out.println(cu.toString());
            }
        }
        System.out.println("Enter AccountID: ");
        int AccountID = sc.nextInt();
        for(Customer cu : listCustomer){
            if(cu.getAccountID() == AccountID && cu.getName().equalsIgnoreCase(Name)){
                for(Account ac : listAccount){
                    if(ac.getAccountID() == cu.getAccountID()){
                        return ac;
                    }
                }
            }
        }
        return null;
    }
    
    // kiem tra Bank da ton tai chua?
    public Bank checkBank(int BankID, ArrayList<Bank> listBank){
        for(Bank ba : listBank){
            if(ba.getBankID() == BankID){
                return ba;
            }
        }
        return null;
    }
    
    // mo Account cho Customer (them Customer va them Account)
    public void openAccount(Scanner sc, ArrayList<Customer> listCustomer, ArrayList<Account> listAccount){
        int CustomerID;
        do{
            System.out.println("Enter CustomerID: ");
            CustomerID = sc.nextInt();           
        } while(checkCustomer(CustomerID, listCustomer) != null);
        sc.nextLine();
        System.out.println("Enter Name: ");
        String Name = sc.nextLine();
        System.out.println("Enter Address: ");
        String Address = sc.nextLine();
        int AccountID, BankID;
        do{
            System.out.println("Enter AccountID: ");
            AccountID = sc.nextInt();
            System.out.println("Enter BankID: ");
            BankID = sc.nextInt();            
        } while(searchAccountByID(AccountID, BankID, listAccount) != null);
        double Balance;
        do{
            System.out.println("Enter Balance: ");
            Balance = sc.nextDouble();           
        } while(Balance < 0);
        Customer cu = new Customer(CustomerID, Name, Address, AccountID);
        Account ac = new Account(AccountID, CustomerID, BankID, Balance);
        listCustomer.add(cu);
        listAccount.add(ac);
        System.out.println("Open Account Successed!");
    }
    
    // Dong Account cho Customer (Xoa Customer va xoa Account)
    public void closeAccountByID(Scanner sc, ArrayList<Customer> listCustomer, ArrayList<Account> listAccount){
        System.out.println("Enter AccountID: ");
        int AccountID = sc.nextInt();
        System.out.println("Enter BankID: ");
        int BankID = sc.nextInt();    
        Account ac = searchAccountByID(AccountID, BankID, listAccount);
        if(ac == null){
            System.out.println("No data available");
        }
        else {
            listAccount.remove(ac);
            for(Customer cu : listCustomer){
                if(cu.getCustomerID() == ac.getCustomerID()){
                   listCustomer.remove(cu);
                   break;
                }
            }    
            System.out.println("Close Account Successed!");
    }
    }
    
    public void closeAccountByCustomerName(Scanner sc, ArrayList<Customer> listCustomer, ArrayList<Account> listAccount){
        System.out.println("Enter Customer Name: ");
        String Name = sc.nextLine();
        Account ac = searchAccountByName(sc, Name, listCustomer, listAccount);
        if(ac == null){
            System.out.println("No data available");
        }
        else {
            listAccount.remove(ac);
            for(Customer cu : listCustomer){
                if(cu.getCustomerID() == ac.getCustomerID()){
                   listCustomer.remove(cu);
                   break;
                }
            }  
            System.out.println("Close Account Successed!");
        }
    }
    
    // Gui tien vao Account
    public void depositByAccountID(Scanner sc, ArrayList<Account> listAccount){
        double amount;
        System.out.println("Enter AccountID: ");
        int AccountID = sc.nextInt();
        System.out.println("Enter BankID: ");
        int BankID = sc.nextInt();    
        Account ac = searchAccountByID(AccountID, BankID, listAccount);
        if(ac == null){
            System.out.println("No data available");
        }
        else {
            do{
                System.out.println("Enter amount: ");
                amount = sc.nextDouble();         
            } while(amount < 0);
            ac.setBalance(ac.getBalance()+amount);
            System.out.println("Execute Deposit Money Successed!");
        }
    }
    public void depositByCustomerName(Scanner sc, ArrayList<Account> listAccount, ArrayList<Customer> listCustomer){
        double amount;
        System.out.println("Enter Customer Name: ");
        String Name = sc.nextLine();
        Account ac = searchAccountByName(sc, Name, listCustomer, listAccount);
        if(ac == null){
            System.out.println("No data available");
        }
        else {
            do{
                System.out.println("Enter amount: ");
                amount = sc.nextDouble();         
            } while(amount < 0);
            ac.setBalance(ac.getBalance()+amount);
            System.out.println("Execute Deposit Money Successed!");
        }
    }   
    // Rut tien tu Account
    public void withdrawByAccountID(Scanner sc, ArrayList<Account> listAccount){
        double amount;
        System.out.println("Enter AccountID: ");
        int AccountID = sc.nextInt();
        System.out.println("Enter BankID: ");
        int BankID = sc.nextInt();    
        Account ac = searchAccountByID(AccountID, BankID, listAccount);
        if(ac == null){
            System.out.println("No data available");
        }
        else {
            do{
                System.out.println("Enter amount: ");
                amount = sc.nextDouble();         
            } while(amount < 0);
            if(ac.getBalance() >= amount){
                ac.setBalance(ac.getBalance()-amount);
                System.out.println("Execute Withdraw Money Successed!");                
            }
            else {
                System.out.println("Execute Withdraw Money Failed!");
            } 
        }
    } 
    public void withdrawByCustomerName(Scanner sc, ArrayList<Account> listAccount, ArrayList<Customer> listCustomer){
        double amount;
        System.out.println("Enter Customer Name: ");
        String Name = sc.nextLine();
        Account ac = searchAccountByName(sc, Name, listCustomer, listAccount);
        if(ac == null){
            System.out.println("No data available");
        }
        else {
            do{
                System.out.println("Enter amount: ");
                amount = sc.nextDouble();         
            } while(amount < 0);
            if(ac.getBalance() >= amount){
                ac.setBalance(ac.getBalance()-amount);
                System.out.println("Execute Withdraw Money Successed!");                
            }
            else {
                System.out.println("Execute Withdraw Money Failed!");
            }
        }
    }    
    // Hien thi so du
    public void displayByAccountID(Scanner sc, ArrayList<Account> listAccount,ArrayList<Customer> listCustomer, ArrayList<Bank> listBank){
        System.out.println("Enter AccountID: ");
        int AccountID = sc.nextInt();
        System.out.println("Enter BankID: ");
        int BankID = sc.nextInt();    
        Account ac = searchAccountByID(AccountID, BankID, listAccount);
        if(ac == null){
            System.out.println("No data available");
        }
        else {
            Customer cu = checkCustomer(ac.getCustomerID(), listCustomer);
            Bank ba = checkBank(BankID, listBank);
            System.out.println("===========Display Information Account===========");
            System.out.println("Customer Name: " + cu.getName());
            System.out.println("AccountID: " + ac.getAccountID());
            System.out.println("Bank Name: " + ba.getName());
            System.out.println("Balance: " + ac.getBalance());
        }
    }  
    public void displayByCustomerName(Scanner sc, ArrayList<Account> listAccount,ArrayList<Customer> listCustomer, ArrayList<Bank> listBank){
        System.out.println("Enter Customer Name: ");
        String Name = sc.nextLine();
        Account ac = searchAccountByName(sc, Name, listCustomer, listAccount);
        if(ac == null){
            System.out.println("No data available");
        }
        else {
            Customer cu = checkCustomer(ac.getCustomerID(), listCustomer);
            Bank ba = checkBank(ac.getBankID(), listBank);
            System.out.println("===========Display Information Account===========");
            System.out.println("Customer Name: " + cu.getName());
            System.out.println("AccountID: " + ac.getAccountID());
            System.out.println("Bank Name: " + ba.getName());
            System.out.println("Balance: " + ac.getBalance());
        }
    } 
    
    // hien thi danh sach Account
    public void displayAccount(ArrayList<Account> listAccount){
        for(Account ac : listAccount){
            System.out.println(ac.toString());
        }
    }
}
