package dataAccess;

import entities.User;

import java.util.List;

public interface IUserRepository {

    void saveUsers(List<User> users);

    boolean exists(User user);
}
