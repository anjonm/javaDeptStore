import java.util.*;
// import java.io.Console;

import users.*;
import utilities.*;

public class DeptStore {
    static General genUtil = new General();
    static Scanner readInput = new Scanner(System.in);
    private static int access = 0;

    private static void logIn(){
        Menu menu = new Menu();
        CheckUser userCheck = new CheckUser();

        // Console console = System.console();

        String username, password;
        char bipolar;
        // char[] password;
        
        do{
            // get username from user
            System.out.print("Enter Username: ");
            readInput.nextLine();
            username = readInput.nextLine();
            // set username on Checkuser class
            userCheck.setUsername(username);
            // get password from user
            // System.out.print();
            // password = console.readPassword("Enter Password: ");
            System.out.print("Enter Password: ");
            // set password on Checkuser class
            password = readInput.nextLine();
            userCheck.setPassword(password);
            // authenticate credentials
            access = userCheck.authenticateUser();
            if (access == 0){
                bipolar = Character.toUpperCase(readInput.next(".").charAt(0));
            } else {
                break;
            }
        } while (bipolar != 'N');
        
        if (access != 0) {
            menu.userMenu(access);
        }
    }
    public static void main(String[] args) {
        int inputInt;

        do {
            // terminal clear
            genUtil.clear();
            System.out.println("Welcome to Dept Store Info System");
            System.out.print("[1] Login\n[9] Shutdown\n>");
            inputInt = readInput.nextInt();
            
            if (inputInt != 1 && inputInt != 9){
                System.out.println("\nNot an Option let's try that again");
            }

            //log in
            if (inputInt == 1) {
                // call method logIn
                logIn();
            }
        } while (inputInt != 9);

        
        System.out.println("System shutdown...");

        readInput.close();
    }
}