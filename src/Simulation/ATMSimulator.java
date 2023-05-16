package Simulation;



Ryan update code 
import java.util.*;
class ATM_Simulator{


public static void main(String[] args){
System.out.println("                 *       * * * * *    *       *  ");
System.out.println("                * *          *        * *   * *  ");
System.out.println("               *   *         *        *   *   *  ");
System.out.println("              * * * *        *        *       *  ");
System.out.println("             *       *       *        *       *  ");
System.out.println("                Welcome to the ATM Simulator!    ");
System.out.println("Please select an option:");
System.out.println("1. User Login");
System.out.println("2. Admin Login");
Scanner in=new Scanner(System.in);
int option =in.nextInt();
switch(option){
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


class User{
public void login(){
   String Username,password;
   Scanner s=new Scanner(System.in);
   System.out.println("Enter your account_no");//username:user
   Username=s.nextInt();
   System.out.print("Enter pin:");//password:user
   password = s.nextInt();
   if(Username.equals("user") && password.equals("user"))
   {System.out.println("Authentication Successful");
       userinterface(Username,password);
   }


   else{
        System.out.println("Username or password incorrect!");
       }
       s.close();


   }
}




class Admin{
public void login(){
System.out.println("Admin Login");
String Username,password;
Scanner t=new Scanner(System.in);
System.out.println("Enter your account_no");//username:admin
Username=t.nextLine();
System.out.print("Enter pin:");//password:admin
password = t.nextLine();
if(Username.equals("admin") && password.equals("admin"))
{System.out.println("Authentication Successful");
   admininterface(Username,password);


   }


   else


   {


   System.out.println("Username or password incorrect!");
   }
   t.close();
}
}




