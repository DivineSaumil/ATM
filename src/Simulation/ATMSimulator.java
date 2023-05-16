package Simulation;
import java.sql.*;
import java.util.Scanner;

class ATM_Simulator {
    public static void main(String[] args) throws SQLException {
        System.out.println("              *       * * * * *    *       *  ");
        System.out.println("             * *          *        * *   * *  ");
        System.out.println("            *   *         *        *   *   *  ");
        System.out.println("           * * * *        *        *       *  ");
        System.out.println("          *       *       *        *       *  ");
        System.out.println("             Welcome to the ATM Simulator!    ");
        System.out.println("Please select an option:");
        System.out.println("1. User Login");
        System.out.println("2. Admin Login");
        Scanner in = new Scanner(System.in);
        int option = in.nextInt();
        switch (option) {
            case 1:
                User user = new User();
                user.login();
                break;
            case 2:
                Admin admin = new Admin();
                admin.login();
                break;
            default:
                System.out.println("Invalid option selected!");
        }
        in.close();
    }
}

class User {
    public void login() throws SQLException {
        int username, password;
        Scanner s = new Scanner(System.in);
        System.out.println("Enter your account number: ");
        username = s.nextInt();
        System.out.print("Enter your PIN: ");
        password = s.nextInt();
        System.out.println("Authentication Successful");
        UserInterface userInterface = new UserInterface(username, password);
        userInterface.performTransaction(username, password);
        s.close();
    }
}

class Admin {
    public void login() {
        System.out.println("Admin Login");
        int username, password;
        Scanner t = new Scanner(System.in);
        System.out.println("Enter your account number: ");
        username = t.nextInt();
        System.out.print("Enter your PIN: ");
        password = t.nextInt();
        if (username == 151152 && password == 153158) {
            System.out.println("Authentication Successful");
            AdminInterface adminInterface = new AdminInterface();
            adminInterface.Menu();
        } else {
            System.out.println("Username or password incorrect!");
        }
        t.close();
    }
}
