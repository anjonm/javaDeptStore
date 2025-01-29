package users;

public class Employee extends User {
    @Override
    public void welcomeMessage(String name){
        System.out.println("---------------");
        System.out.println(String.format("*Welcome %s*", name));
        System.out.println("---------------");
    }

    @Override
    public void menu(){
        super.menu();
        System.out.println("[0] Log Out");
    }
}
