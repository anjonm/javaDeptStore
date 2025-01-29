package customers;

import java.util.*;

public class ManageCustomer {
    CustomerInfo customInfo = new CustomerInfo();
    Scanner readInput = new Scanner(System.in);

    int inputInt;

    public void customerDisplay(){
        System.out.println("--Manage Customer--");
        System.out.print("[1] New Member\n[2] View Members\n[0] Back\n>");
        inputInt = readInput.nextInt();
        switch (inputInt) {
            case 0:
                
                break;

            case 1:
                
                break;
            case 2:
                
                break;
        
            default:
                break;
        }
    }
}
