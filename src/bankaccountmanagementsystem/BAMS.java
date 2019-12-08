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
public class BAMS {

    // tao menu chuc nang cua he thong
    public void menu(){
        System.out.println("===========Menu============");
        System.out.println("1. Open a bank account for a customer");
        System.out.println("2. Close a bank account for a customer");
        System.out.println("3. Execute deposit money");
        System.out.println("4. Execute withdraw money");
        System.out.println("5. Display information account");
        System.out.println("6. Exit");
        System.out.println("Enter choose menu: ");
    }
    
    // tao menu con 
    public void submenu(){
        System.out.println("===========================");
        System.out.println("1. By Customer Name");
        System.out.println("2. By AccountID");
        System.out.println("3. Back");
        System.out.println("Enter choose submenu: ");
    }
         
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BAMS demo = new BAMS();
        Bank ba = new Bank();
        Account ac = new Account();
        Customer cu = new Customer();
        ArrayList<Bank> listBank = new ArrayList<Bank>();
        ArrayList<Account> listAccount = new ArrayList<Account>();
        ArrayList<Customer> listCustomer = new ArrayList<Customer>();
        ba.loadDataBank("bank.txt",listBank); 
        ac.loadDataAccount("account.txt", listAccount);
        cu.loadDataCustomer("customer.txt", listCustomer);
        int choose = 0;
        do{
            demo.menu();
            choose = sc.nextInt();
            switch(choose){
                case 1: {
                    ac.openAccount(sc, listCustomer, listAccount);
                    ac.genDataAccount("account.txt", listAccount);
                    cu.genDataCustomer("customer.txt", listCustomer);
                    break;                
                }
                case 2: {
                    int subchoose = 0;
                    do{
                        demo.submenu();
                        subchoose = sc.nextInt();
                        switch(subchoose){
                            case 1: {
                                sc.nextLine();
                                ac.closeAccountByCustomerName(sc, listCustomer, listAccount);
                                ac.genDataAccount("account.txt", listAccount);
                                cu.genDataCustomer("customer.txt", listCustomer);
                                break;
                            }
                            case 2: {
                                ac.closeAccountByID(sc, listCustomer, listAccount);
                                ac.genDataAccount("account.txt", listAccount);
                                cu.genDataCustomer("customer.txt", listCustomer);
                                break;                                
                            }
                            case 3: {
                                System.out.println("Back Menu!");
                                break;
                            }
                            default: {
                                System.out.println("Retype choose submenu");
                                break;
                            }
                        }
                    } while(subchoose != 3);
                    break;
                }
                case 3: {
                    int subchoose = 0;
                    do{
                        demo.submenu();
                        subchoose = sc.nextInt();
                        switch(subchoose){
                            case 1: {
                                sc.nextLine();
                                ac.depositByCustomerName(sc, listAccount, listCustomer);
                                ac.genDataAccount("account.txt", listAccount);
                                cu.genDataCustomer("customer.txt", listCustomer);
                                break;
                            }
                            case 2: {
                                ac.depositByAccountID(sc, listAccount);
                                ac.genDataAccount("account.txt", listAccount);
                                cu.genDataCustomer("customer.txt", listCustomer);
                                break;                                
                            }
                            case 3: {
                                System.out.println("Back Menu!");
                                break;
                            }
                            default: {
                                System.out.println("Retype choose submenu");
                                break;
                            }
                        }
                    } while(subchoose != 3);
                    break;
                }
                case 4: {
                    int subchoose = 0;
                    do{
                        demo.submenu();
                        subchoose = sc.nextInt();
                        
                        switch(subchoose){
                            case 1: {
                                sc.nextLine();
                                ac.withdrawByCustomerName(sc, listAccount, listCustomer);
                                ac.genDataAccount("account.txt", listAccount);
                                cu.genDataCustomer("customer.txt", listCustomer);
                                break;
                            }
                            case 2: {
                                ac.withdrawByAccountID(sc, listAccount);
                                ac.genDataAccount("account.txt", listAccount);
                                cu.genDataCustomer("customer.txt", listCustomer);
                                break;                                
                            }
                            case 3: {
                                System.out.println("Back Menu!");
                                break;
                            }
                            default: {
                                System.out.println("Retype choose submenu");
                                break;
                            }
                        }
                    } while(subchoose != 3);
                    break;                    
                } 
                case 5: {
                    int subchoose = 0;
                    do{
                        demo.submenu();
                        subchoose = sc.nextInt();
                        
                        switch(subchoose){
                            case 1: {
                                sc.nextLine();
                                ac.displayByCustomerName(sc, listAccount, listCustomer, listBank);
                                ac.genDataAccount("account.txt", listAccount);
                                cu.genDataCustomer("customer.txt", listCustomer);
                                break;
                            }
                            case 2: {
                                ac.displayByAccountID(sc, listAccount, listCustomer, listBank);
                                ac.genDataAccount("account.txt", listAccount);
                                cu.genDataCustomer("customer.txt", listCustomer);
                                break;                                
                            }
                            case 3: {
                                System.out.println("Back Menu!");
                                break;
                            }
                            default: {
                                System.out.println("Retype choose submenu");
                                break;
                            }
                        }
                    } while(subchoose != 3);
                    break;
                }
                case 6: {
                    System.out.println("Goodbye!See you again.");
                    break;                   
                }
                default: {
                    System.out.println("Retype choose menu ");
                    break;
                }
            }
        } while (choose!= 6);
   }
}
