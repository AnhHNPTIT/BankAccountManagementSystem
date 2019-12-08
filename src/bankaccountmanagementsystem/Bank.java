/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountmanagementsystem;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hoang
 */
public class Bank {
    private int bankID;
    private String name;
    private String location;

    public Bank() {
    }

    public Bank(int bankID, String name, String location) {
        this.bankID = bankID;
        this.name = name;
        this.location = location;
    }

    public int getBankID() {
        return bankID;
    }

    public void setBankID(int bankID) {
        this.bankID = bankID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return bankID + " " + name + " " + location;
    }
    
    // doc danh sach Bank tu file
    public void loadDataBank(String filename, ArrayList<Bank> listBank){
        try{
            Scanner sc = new Scanner(new File(filename));
            while(sc.hasNextLine()){
                int BankID = sc.nextInt();
                sc.nextLine();
                String Name = sc.nextLine();
                String Location = sc.nextLine();
                Bank temp = new Bank(BankID, Name, Location);
                listBank.add(temp);
            }
            sc.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    // nhap Bank tu ban phim
    public Bank createBank(Scanner sc){
        
        System.out.println("Enter BankID: ");
        int BankID = sc.nextInt();
        
        System.out.println("Enter Name: ");
        sc.nextLine();
        String Name = sc.nextLine();
        System.out.println("Enter Location: ");
        String Location = sc.nextLine();
        Bank ba = new Bank(BankID, Name, Location);
        return ba;
    }
    // hien thi danh sach Bank
    public void displayBank(ArrayList<Bank> listBank){
            for(Bank ba : listBank){
            System.out.println(ba.toString());
        }
    }
}
