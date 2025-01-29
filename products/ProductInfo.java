package products;

import utilities.*;

import java.util.*;

public class ProductInfo {    
    General genUtil = new General();
    
    Scanner readInput = new Scanner(System.in);
    String inputString;
    int inputInt;
    double inputDouble;
    char bipolar = 0;

    public void requiredInfo(){
        int categLength;
        Crud crud = new Crud();
        String prodId, categ;

        crud.retrieveData("savedFiles/products.txt");
        do{ 
            // System.out.println("ASAAAAAAAAAAAAGGGGGGGGGGGG");
            // crud.setPath("product");
            System.out.println("-------------ADD NEW PRODUCT------------");
            System.out.println("------CHOOSE WHICH CATEGORY TO ADD------");
            System.out.println("----------------------------------------");
            // System.out.println(genUtil.getCategory());
            // genUtil.setCategory(null);
            categLength = genUtil.getCategory().size();
            inputInt = readInput.nextInt();

            if (inputInt == 0) break;
            else if (inputInt < 0 || inputInt > categLength) {
                // genUtil.clear();
                System.out.println("Not an Option let's try that again...");
            } else {
                // categIndex = inputInt;
                categ = genUtil.categoryIs(inputInt).toUpperCase();
                do {
                    System.out.print(String.format("You want to add new product in [%s]? (Y/N) ", categ));
                    bipolar = Character.toUpperCase(readInput.next(".").charAt(0));

                    if (bipolar == 'Y') {
                        do {
                            String category;

                            category = genUtil.categoryIs(inputInt).substring(0, 3).toUpperCase();
                            // crud.setPath("product");
                            crud.setCateg(category);
                            prodId = crud.getLatestId();
                            
                            System.out.println(String.format("Product ID: %s", prodId));
                            crud.setId(prodId);
                            
                            readInput.nextLine();

                            System.out.print("Enter Product Name: ");
                            inputString = readInput.nextLine();
                            crud.setName(inputString);

                            System.out.print("Enter Product Price: ");
                            inputInt = readInput.nextInt();
                            crud.setPrice(inputInt);
                            
                            System.out.print("Enter Product Quantity: ");
                            inputInt = readInput.nextInt();
                            crud.setQty(inputInt);

                            crud.addNew();
                            System.out.print(String.format("Add %s again?", categ));
                            bipolar = Character.toUpperCase(readInput.next(".").charAt(0));
                        } while (bipolar != 'N');
                        crud.updateFile("savedFiles/products.txt");
                    } else if (bipolar == 'N') {
                        genUtil.clear();
                    } else {
                        System.out.println("Not an Option let's try that again...");
                    }
                    // crud.setProdId(categories[inputInt]);
                } while (bipolar != 'N' && bipolar != 'Y');
            }
            readInput.nextLine();            
        }while (inputInt != 0);
    }
}
