package Simulation;
import java.util.*;
import java.sql.*;
public class AdminInterface {
    
    
    public static void main(String[] args) { 
        try { 
            // Create a new AccountDatabase object 
            Database db = new Database("jdbc:mysql://localhost:3306/mydb", "root", "root");
    
            //ADMIN MODE IN ACTION
            System.out.println("Enter 1 to Add Record");
            System.out.println("Enter 2 to Delete Record");
            System.out.println("Enter 3 to Update Record");
            Scanner sc = new Scanner(System.in);
            int a = sc.nextInt();
            int b;
            switch(a) {
                case 1:
                    
                    do {
                        System.out.println("ENTER THE FOLLOWING DETAILS TO ADD A RECORD");
                        System.out.println("Enter The Account Number-");
                        int account_no = sc.nextInt();
                        System.out.println("Enter The Name of holder-");
                        String name = sc.next();
                        System.out.println("Enter The Balance in account-");
                        Float balance = sc.nextFloat();
                        System.out.println("Enter The Account PIN-");
                        int pin = sc.nextInt();
                        System.out.println("Enter The Information about the account-");
                        String info = sc.nextLine();
                        db.addRecord(account_no, name, balance, pin, info); 
                        System.out.println("RECORD ADDED SUCCESSFULLY!!");
                        System.out.println("If you want to ADD another record \t Enter 1");
                        b = sc.nextInt();
                    } while (b == 1);
                    break;
                case 2:
                    
                    do {
                        System.out.println("ENTER THE ACCOUNT NUMBER OF ACCOUNT TO DELETE THE RECORD");
                        System.out.println("Enter The Account Number-");
                        int account_no = sc.nextInt();
                        db.deleteRecord(account_no);
                        System.out.println("RECORD DELETED SUCCESSFULLY!!");
                        System.out.println("If you want to DELETE another record \t enter 1");
                        b = sc.nextInt();
                    } while (b == 1);
                    break;
                case 3:
                    
                    do {
                        System.out.println("ENTER THE ACCOUNT NUMBER AND PIN OF ACCOUNT TO UPDATE THE RECORD");
                        System.out.println("Enter The Account Number-");
                        int account_no = sc.nextInt();
                        System.out.println("Enter The PIN-");
                        int pin = sc.nextInt();
                        ResultSet rs = db.getRecord(account_no, pin);
                        account_no = rs.getInt("account_no");
                        String name = rs.getString("name");
                        Float balance = rs.getFloat("balance");
                        pin = rs.getInt("pin");
                        String info = rs.getString("info");
                        System.out.println("Account details: ");
                        System.out.println("Account no: " + account_no);
                        System.out.println("Name: " + name);
                        System.out.println("Balance: " + balance);                        System.out.println("PIN: " + pin);
                        System.out.println("Info: " + info);
                        System.out.println("Enter The Updated Name of holder-");
                        name = sc.nextLine();
                        System.out.println("Enter The Updated Balance in account-");
                        balance = sc.nextFloat();
                        System.out.println("Enter The Updated Account PIN-");
                        pin = sc.nextInt();
                        System.out.println("Enter The Updated Information about the account-");
                        info = sc.nextLine();
                        db.updateRecord(account_no, name, balance, pin, info);
                        System.out.println("RECORD UPDATED SUCCESSFULLY!!");
                        System.out.println("If you want to Update another record \t enter 1");
                        b = sc.nextInt();
                    } while (b == 1);
                    break;
                default:
                    System.out.println("You entered the wrong number");
            }
            sc.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }   
}

