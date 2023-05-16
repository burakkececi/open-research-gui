package model.login;

import dataAccess.IUserRepository;
import dataAccess.UserRepository;
import entities.User;

import java.util.Observable;

public class LoginModel extends Observable implements ILoginModel {

    private User user;
    private String text;

    public void setUser(User user) {
        this.user = user;
    }

    public void setText(String text) {
        this.text = text;
        setChanged();
        notifyObservers(text);
    }

    public boolean isUserValid() {
        IUserRepository iUserRepository = new UserRepository();
        return iUserRepository.exists(user);
    }

}
