import users.*;
import utilities.*;
import products.*;

import customers.ManageCustomer;
import sales.ProcessSales;

import java.util.*;

class Menu {
    private General genUtil = new General();
    private Crud crud = new Crud();
    private Admin admin = new Admin();
    private char bipolar = 0;
    private int inputInt;

    Scanner readInput = new Scanner(System.in);

    private void viewProduct(){
        crud.retrieveData("savedFiles/products.txt");
        int inputInt = 0;
        ArrayList<String> categCode;
        do {
            // get available categ codes
            categCode = crud.viewBy();
            System.out.println("-----VIEW PRODUCTS-----");

            // view option base on categ code
            genUtil.setCategory(categCode);
            genUtil.getCategory();
            inputInt = readInput.nextInt();
            if (inputInt == 0) {
                break;
            }else if (inputInt == categCode.size() + 1) {
                // View All
                crud.view();
            } else if (inputInt <= categCode.size() + 1 ) {
                // View by Categ
                crud.view(categCode.get(inputInt - 1));
            } else {
                System.out.println("Nope not an option");
            }
        } while (inputInt != 0);
    }

    private void manageProduct(){
        ProductInfo productInfo = new ProductInfo();
        ManageProducts manageProduct = new ManageProducts();
        
        do {
            // genUtil.clear();
            admin.manageProduct();
            inputInt = readInput.nextInt();
            switch (inputInt) {
                case 0:
                    // genUtil.clear();
                    break;
                case 1:
                    productInfo.requiredInfo();
                    break;
                case 2:
                    manageProduct.addStocks();
                    break;  

                case 3:
                    manageProduct.deleteProducts();
                    break;

                case 4:
                    viewProduct();
                    break;
            
                default:
                    System.out.println("Not an option let's try that again...");
                    break;
            }
        } while (inputInt != 0);
    }

    protected void userMenu(int access){
        Employee employee = new Employee();
        ProcessSales sales = new ProcessSales();
        ManageCustomer customer = new ManageCustomer();
        int menuLimit = 0;
        
        do {
            do {
                if (access == 1) {
                    menuLimit = 2;
                    employee.welcomeMessage("Jaine");
                    employee.menu();
                } else if (access == 2) {
                    menuLimit = 4;
                    admin.welcomeMessage("Andelo");
                    admin.menu();
                }
                System.out.print("> ");
                inputInt = readInput.nextInt();
                if (inputInt < 0 || inputInt > menuLimit) {
                    System.out.println("Not an option let's try that again");
                }
            } while (inputInt < 0 || inputInt > menuLimit);

            switch (inputInt) {
                case 0:
                    admin.logOut();
                    bipolar = Character.toUpperCase(readInput.next(".").charAt(0));
                    break;

                case 1:
                    sales.sales();
                    break;

                case 2:
                    customer.customerDisplay();
                    break;
                    
                case 3:
                    manageProduct();
                    break;
                
                case 4:
                    viewProduct();
                    break;

                default:
                    break;
            }
        } while (bipolar != 'Y');
        // readInput.close();
    }
}
