package users;

abstract class User {
    public void logOut(){
        System.out.print("Are you sure you want to log out? (Y/N) ");
    }

    protected void menu(){
        System.out.println("--OPTION--\n[1] Process Sale\n[2] Manage Customer");
    }

    abstract void welcomeMessage(String name);
}
