package dataAccess;

import entities.User;

import java.util.List;

public interface IUserRepository {

    void saveUser(User user);

    boolean exists(User user);
}
