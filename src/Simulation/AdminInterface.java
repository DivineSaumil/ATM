package Simulation;
import java.sql.*;
import java.util.Scanner;

public class AdminInterface {
    
    private static final int ADD_RECORD = 1;
    private static final int DELETE_RECORD = 2;
    private static final int UPDATE_RECORD = 3;

    public void Menu() { 
        try { 
            // Create a new AccountDatabase object 
            Database db = new Database("jdbc:mysql://localhost:3306/mydb", "root", "root");

            //ADMIN MODE IN ACTION
            Scanner sc = new Scanner(System.in);
            int option;
            do {
                displayMenu();
                option = sc.nextInt();
                switch (option) {
                    case ADD_RECORD:
                        addRecord(db, sc);
                        break;
                    case DELETE_RECORD:
                        deleteRecord(db, sc);
                        break;
                    case UPDATE_RECORD:
                        updateRecord(db, sc);
                        break;
                    default:
                        System.out.println("You entered the wrong number");
                        break;
                }
            } while (option != 4);
            
            sc.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }   

    private static void displayMenu() {
        System.out.println("----- ADMIN MODE MENU -----");
        System.out.println("1. Add Record");
        System.out.println("2. Delete Record");
        System.out.println("3. Update Record");
        System.out.println("4. Exit");
        System.out.println("Enter your choice:");
    }

    private static void addRecord(Database db, Scanner sc) {
        try {
            System.out.println("----- ADD RECORD -----");
            do {
                System.out.println("Enter the following details to add a record:");
                System.out.print("Enter the Account Number: ");
                int account_no = sc.nextInt();
                System.out.print("Enter the Name of the holder: ");
                String name = sc.next();
                System.out.print("Enter the Balance in account: ");
                float balance = sc.nextFloat();
                System.out.print("Enter the Account PIN: ");
                int pin = sc.nextInt();
                System.out.print("Enter the Information about the account: ");
                String info = sc.next();
                db.addRecord(account_no, name, balance, pin, info); 
                System.out.println("RECORD ADDED SUCCESSFULLY!!");
                System.out.println("If you want to add another record, enter 1");
            } while (sc.nextInt() == 1);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void deleteRecord(Database db, Scanner sc) {
        try {
            System.out.println("----- DELETE RECORD -----");
            do {
                System.out.println("Enter the Account Number of the record to delete:");
                int account_no = sc.nextInt();
                db.deleteRecord(account_no);
                System.out.println("RECORD DELETED SUCCESSFULLY!!");
                System.out.println("If you want to delete another record, enter 1");
            } while (sc.nextInt() == 1);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void updateRecord(Database db, Scanner sc) {
        try {
            System.out.println("----- UPDATE RECORD -----");
            do {
                System.out.println("Enter the Account Number and PIN of the record to update:");
                System.out.print("Enter the Account Number: ");
                int account_no = sc.nextInt();
                System.out.print("Enter the PIN: ");
                int pin = sc.nextInt();
                ResultSet rs = db.getRecord(account_no, pin);
                account_no = rs.getInt("account_no");
                String name = rs.getString("name");
                float balance = rs.getFloat("balance");
                pin = rs.getInt("pin");
                String info = rs.getString("info");
                System.out.println("Account details: ");
                System.out.println("Account no: " + account_no);
                System.out.println("Name: " + name);
                System.out.println("Balance: " + balance);
                System.out.println("PIN: " + pin);
                System.out.println("Info: " + info);
                System.out.print("Enter the Updated Name of holder: ");
                name = sc.nextLine();
                System.out.print("Enter the Updated Balance in account: ");
                balance = sc.nextFloat();
                System.out.print("Enter the Updated Account PIN: ");
                pin = sc.nextInt();
                System.out.print("Enter the Updated Information about the account: ");
                info = sc.nextLine();
                db.updateRecord(account_no, name, balance, pin, info);
                System.out.println("RECORD UPDATED SUCCESSFULLY!!");
                System.out.println("If you want to update another record, enter 1");
            } while (sc.nextInt() == 1);
        } catch (Exception e) {
            System.out.println(e);
        }
    
    }
}
    
