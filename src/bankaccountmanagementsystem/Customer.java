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
public class Customer {
    private int customerID;
    private String name;
    private String address;
    private int accountID;

    public Customer() {
    }

    public Customer(int customerID, String name, String address, int accountID) {
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.accountID = accountID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    @Override
    public String toString() {
        return customerID + " " + name + " " + address + " " + accountID;
    }
    
        // doc danh sach Customer tu file
    public void loadDataCustomer(String filename, ArrayList<Customer> listCustomer){
        try{
            Scanner sc = new Scanner(new File(filename));
            while(sc.hasNextLine()){
                int CustomerID = sc.nextInt();
                if(CustomerID != -1){
                    sc.nextLine();
                    String Name = sc.nextLine();
                    String Address = sc.nextLine();
                    int AccountID = sc.nextInt();
                    Customer temp = new Customer(CustomerID, Name, Address, AccountID);
                    listCustomer.add(temp);                   
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
    
    // ghi danh sach Customer vao file
    public void genDataCustomer(String filename, ArrayList<Customer> listCustomer){
        try{
            PrintWriter out = new PrintWriter(filename);
            out.flush();
            for(Customer cu : listCustomer){
                int CustomerID = cu.getCustomerID();
                out.println(CustomerID);
                String Name = cu.getName();
                out.println(Name);
                String Address = cu.getAddress();
                out.println(Address);
                int AccountID = cu.getAccountID();
                out.println(AccountID);
            }
            out.println(-1);
            out.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    // hien thi danh sach Customer
    public void displayCustomer(ArrayList<Customer> listCustomer){
        for(Customer cu : listCustomer){
            System.out.println(cu.toString());
        }
    }
}
