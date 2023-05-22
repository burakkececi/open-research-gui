package entity;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final String name;
    private final String password;
    private final List<String> following;
    private final List<String> followers;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
    }

    public List<String> getFollowing() {
        return following;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
