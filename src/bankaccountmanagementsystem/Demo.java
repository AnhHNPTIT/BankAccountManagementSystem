/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountmanagementsystem;

import java.util.Scanner;

/**
 *
 * @author hoang
 */
public class Demo {
    public static void main(String[] args) {
        // nhap danh sach Bank
        Scanner sc = new Scanner(System.in);
        Bank b = new Bank();
        int n = sc.nextInt();
        Bank[] listBank = new Bank[n+1];
        for(int i = 1; i <= n; i++){
            listBank[i] = b.createBank(sc);
        }
        for(int i = 1; i <= n; i++){
            System.out.println(listBank[i].toString());
        }
    }
}
