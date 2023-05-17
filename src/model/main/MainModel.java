package model.main;

import entities.User;

import java.util.Observable;

public class MainModel extends Observable {

    private User user;

    public MainModel(User user) {
        this.user = user;
    }

    public User getUser() {
        return new User(this.user);
    }
}
