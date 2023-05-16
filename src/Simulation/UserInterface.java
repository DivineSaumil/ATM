package Simulation;


import java.sql.*;
import java.util.*;

public class UserInterface extends Database{
    int account_no;
    int pin;
    UserInterface(int account,int pn) throws SQLException
    {
       super("jdbc:mysql://localhost:3306/mydb", "root", "root");
        account_no = account;
        pin = pn;
    }
    public void processWithdrawal(int account_no, int pin, float balance) throws SQLException {
        float newbalance=0;
        int op=0;
        
        System.out.println("Enter the amount to be withdrawn:");
        Scanner sca = new Scanner(System.in);
        op = sca.nextInt();
        
        if (balance >= op) {
            newbalance = balance - op;
        } else {
            System.out.println("Error: Not enough balance.");
        }
	ResultSet rs = getRecord(account_no, pin);
    String name="";
    String info="";

if (rs.next()) {
               // The record exists, so we can retrieve its values
               account_no = rs.getInt("account_no");
               name = rs.getString("name");
               balance = rs.getFloat("balance");
               pin = rs.getInt("pin");
               info = rs.getString("info");
        } else {
               // The record doesn't exist, so we can notify the user
               System.out.println("Invalid account no or PIN");
           }


		 updateRecord(account_no, name, newbalance, pin, info);
         sca.close();
    }

    public void processDeposit(int account_no, int pin, float balance) throws SQLException {
        float newbalance=0;
        int dep;
        
        System.out.println("Enter the amount to be deposited:");
        Scanner scan = new Scanner(System.in);
        dep = scan.nextInt();
        
        newbalance = balance + dep;
	ResultSet rs = getRecord(account_no, pin);
    String name="";
    String info="";
if (rs.next()) {
               // The record exists, so we can retrieve its values
               account_no = rs.getInt("account_no");
               name = rs.getString("name");
               balance = rs.getFloat("balance");
               pin = rs.getInt("pin");
               info = rs.getString("info");
             } else {
               // The record doesn't exist, so we can notify the user
               System.out.println("Invalid account no or PIN");
           }

        updateRecord(account_no, name, newbalance, pin, info);
         scan.close();
    }


    public void performTransaction(int account,int pn) throws SQLException {
        account = account_no;
        pn = pin;
        float balance=0;
        ResultSet rs = getRecord(account, pin);
            if (rs.next()) {
                // The record exists, so we can retrieve its values
                int account_no = rs.getInt("account_no");
                String name = rs.getString("name");
                balance = rs.getFloat("balance");
                
                String info = rs.getString("info");
                System.out.println("Account details: ");
                System.out.println("Account no: " + account_no);
                System.out.println("Name: " + name);
                System.out.println("Balance: " + balance);
                System.out.println("Info: " + info);
            } else {
                // The record doesn't exist, so we can notify the user
                System.out.println("Invalid account no or PIN");
            }
        System.out.println("Do you want to withdraw money or deposit it?");
        System.out.println("Press 1 for withdrawal and press 2 for deposit.");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        if (n == 1) {
            processWithdrawal(account_no, pn,balance);
        } else if (n == 2) {
            processDeposit(account_no, pn,balance);
        } else {
            System.out.println("Invalid choice.");
        }
        sc.close();
            }
        
        }