package users;

public class CheckUser {
    // private int adminAttempt, employeeAttempt;
    private String username, password;
    // char[] password;

    public void setUsername(String user){
        this.username = user;
    }

    private String getUsername(){
        return username;
    }

    public void setPassword(String pass){       
        this.password = pass;
    }

    private String getPassword(){
        return password;
    }

    public int authenticateUser(){
        int access = 0;
        // System.out.println(getUsername());
        if ("Jaine".equals(getUsername())){
            if ("Jaine123".equals(String.valueOf(getPassword()))) {
                access = 1;
            } else {
                System.out.print("Incorrect username or password, try again? (Y/N) ");
                // employeeAttempt++;
            }
        } else if ("admin".equals(getUsername())) {
            if ("admin123".equals(String.valueOf(getPassword()))) {
                access = 2;
            } else {
                System.out.print("Incorrect username or password, try again? (Y/N) ");
                // adminAttempt++;
            }
        } else {
           System.out.print("Account doesn't exist, try again? (Y/N) ");
        }
        return access;
    }
}
