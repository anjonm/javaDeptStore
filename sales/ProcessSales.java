package sales;

import java.util.*;

import utilities.*;

public class ProcessSales {
    Scanner readInput = new Scanner(System.in);
    General genUtil = new General();
    Crud crud = new Crud();
    ArrayList<String> itemInCart = new ArrayList<String>();
    ArrayList<String> reciept = new ArrayList<String>();

    char bipolar;
    String inputString;
    public void sales(){
        int inputInt;
        crud.retrieveData("savedFiles/products.txt");
        do {
            System.out.println("-----Process Sales-----");
            System.out.print("[1] Add to Cart\n[2] Check Out\n[0] Back\n>");
            inputInt = readInput.nextInt();
            switch (inputInt) {
                case 0:
                    break;
                case 1:
                    addToCart();
                    break;

                case 2:
                    genUtil.clear();
                    reciept = crud.view(itemInCart);
                    System.out.print("Do you want to check out? (Y/N) ");
                    bipolar = Character.toUpperCase(readInput.next(".").charAt(0));
                    if (bipolar == 'Y') {
                        crud.checOut(reciept);
                        crud.updateFile("savedFiles/products.txt");
                    }
                    break; 
                default:
                    break;
            }
        } while (inputInt !=0);
        readInput.nextLine();
    }

    private void addToCart(){
        int inputInt, itemQty, qtyToBuy;
        String idQty;
        String [] data;
        ArrayList<String> categCode;
        boolean verified, existing = true;
        do {
            bipolar = 0;
            categCode = crud.viewBy();
            System.out.println("-----Choose Product to add to Cart-----");

            // Options/Choices
            genUtil.setCategory(categCode);
            genUtil.getCategory();
            inputInt = readInput.nextInt();

            if (inputInt == 0) {
                bipolar = 'N';
                break;
            } else if (inputInt == categCode.size() + 1) {
                // viewAll
                crud.view();
                crud.setCateg("all");
            } else if (inputInt <= categCode.size() + 1) {
                // view by categ
                crud.view(categCode.get(inputInt - 1));
                crud.setCateg(categCode.get(inputInt - 1));
            } else {
                System.out.println("Not an option");
            }
        
            if (inputInt > 0 && inputInt <= categCode.size() + 1) {
                System.out.print(String.format("Are you sure you want to add (Y/N) "));
                bipolar = Character.toUpperCase(readInput.next(".").charAt(0));

                if (bipolar == 'Y') {
                    do {
                        readInput.nextLine();
                        System.out.print("Product Id: ");
                        inputString = readInput.nextLine();
                        crud.setId(inputString);
                        verified = crud.verifyId();
                        if (!verified) {
                            System.out.print("Product ID doesn't match, Try agian? (Y/N) ");
                            bipolar = Character.toUpperCase(readInput.next(".").charAt(0));
                            if (bipolar == 'N') {
                                break;
                            }
                        } else {
                            System.out.print("Enter Quantity to buy: ");
                            qtyToBuy = readInput.nextInt();
                            // crud.setQty(qtyToBuy);
                            if (itemInCart.size() != 0) {
                                for (int i = 0; i < itemInCart.size(); i++) {
                                    idQty = itemInCart.get(i);
                                    if (idQty.contains(inputString)) {
                                        data = idQty.split("#");
                                        for (int j = 0; j < data.length; j++) {
                                            if (data[j].contains("qty:")) {
                                                itemQty = Integer.parseInt(data[j].substring(4));
                                                itemQty += qtyToBuy;
                                                data[j] = "qty:" + itemQty;
                                            }
                                        }
                                        inputString = String.join("#", data);
                                        itemInCart.set(i, inputString);
                                        existing = false;
                                    }

                                }
                                if (existing) {
                                    System.out.println("else if " + qtyToBuy);
                                    itemInCart.add(String.format("id:%s#qty:%d", inputString, qtyToBuy));
                                }
                            } else {
                                itemInCart.add(String.format("id:%s#qty:%d", inputString, qtyToBuy));
                            }           
                            genUtil.clear();                 
                            crud.view(itemInCart);
                            bipolar = 'N';
                        }
                    } while (bipolar != 'N');
                }
            }
        } while (inputInt != 0);
    }
}

