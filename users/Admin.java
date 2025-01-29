package users;

public class Admin extends User{
    public void manageProduct(){
        System.out.print("--Manage Product--\n[1] Add New Product\n[2] Add Stocks\n[3] Delete Product\n[4] View Products\n[0] Back\n> ");
    }
    
    public void viewReports(){
        System.out.println("--View Reports--\n[1] Inventory List\n[2] Member List\n[3] Supplier List\n[0] Back");
    }

    @Override
    public void welcomeMessage(String name){
        System.out.println("----------------------");
        System.out.println(String.format("*WELCOME ADMIN %s*", name.toUpperCase()));
        System.out.println("----------------------");
    }

    @Override
    public void menu(){
        super.menu();
        System.out.println("[3] Manage Product\n[4] View Inventory\n[0] Log Out");
    }
}