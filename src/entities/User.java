package entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private String username;
    private String password;

    private Map<String, List<String>> readingList;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        readingList = new HashMap<>();
    }

    public User(User user) {
        if (user == null) {
            System.out.println("Error creating user.");
            System.exit(0);
        }
        this.username = user.username;
        this.password = user.password;

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setReadingList(Map<String, List<String>> readingList) {
        this.readingList = readingList;
    }

    public Map<String, List<String>> getReadingList() {
        return readingList;
    }
}
