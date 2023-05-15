package entities;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void login(String username, String password) {

    }

    public String getPassword() {
        return password;
    }
}
