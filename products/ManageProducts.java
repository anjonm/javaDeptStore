package products;

import utilities.*;
import java.util.*;

public class ManageProducts {
    private Crud crud = new Crud();
    private General genUtil = new General();
    private Scanner readInput = new Scanner(System.in);
    private String inputString;
    private char bipolar;
    private boolean verified;
    private int inputInt, upperLimit;

    private void viewProduct(String option){
        ArrayList<String> categCode;
        do {
            bipolar = 0;
            categCode = crud.viewBy();
            System.out.println(String.format("-----%s Product-----", option));

            // Options/Choices
            genUtil.setCategory(categCode);
            genUtil.getCategory();
            inputInt = readInput.nextInt();
            upperLimit = categCode.size() + 1;
            if (inputInt == 0) {
                verified = false;
                bipolar = 'N';
                break;
            }else if (inputInt == upperLimit) {
                // viewAll
                crud.view();
                crud.setCateg("all");
            } else if (inputInt <= upperLimit) {
                // view by categ
                crud.view(categCode.get(inputInt - 1));
                crud.setCateg(categCode.get(inputInt - 1));
            } else {
                System.out.println("Not an option");
            }
        
            if (inputInt > 0 && inputInt <= upperLimit) {
                System.out.print(String.format("Are you sure you want to %s (Y/N) ", option));
                bipolar = Character.toUpperCase(readInput.next(".").charAt(0));
                if (bipolar == 'N') {
                    verified = false;
                    bipolar = 0;
                    break;
                } else {
                    do {
                        System.out.print("Product Id: ");
                        readInput.nextLine();
                        inputString = readInput.nextLine();
                        crud.setId(inputString);
                        verified = crud.verifyId();
                        if (!verified) {
                            System.out.print("Product ID doesn't match, Try agian? (Y/N) ");
                            bipolar = Character.toUpperCase(readInput.next(".").charAt(0));
                        } else {
                            bipolar = 'N';
                            break;
                        }
                    } while (bipolar != 'N');
                }
            }
        } while (bipolar != 'N');
    }

    public void addStocks(){
        crud.retrieveData("savedFiles/products.txt");
        do {
            viewProduct("Add");
            if (verified) {
                System.out.print("Enter Quantity: ");
                inputInt = readInput.nextInt();
                crud.setQty(inputInt);
                crud.addStocks();
                System.out.println("Stocks Updated: ");
                crud.view();
                System.out.print("Do you want to add more? (Y/N) ");
                bipolar = Character.toUpperCase(readInput.next(".").charAt(0));
                crud.updateFile("savedFiles/products.txt");
            }
        } while(bipolar != 'N');
    }

    public void deleteProducts(){
        crud.retrieveData("savedFiles/products.txt");
        do {
            viewProduct("Delete");
            if (verified) {
                crud.delete();
                System.out.println("Product deleted, you want to delete more? (Y/N) ");
                bipolar = Character.toUpperCase(readInput.next(".").charAt(0));
            }
        } while (bipolar != 'N');
        crud.deleteFile();
        crud.updateFile("savedFiles/products.txt");
        // crud.delete(); 
    }
}
