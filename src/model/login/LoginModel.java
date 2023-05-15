package model.login;

import dataAccess.IUserRepository;
import dataAccess.UserRepository;
import entities.User;

import java.util.Observable;

public class LoginModel extends Observable implements ILoginModel {

    public boolean isUserValid(User user) {
        IUserRepository iUserRepository = new UserRepository();
        return iUserRepository.exists(user);
    }


}
