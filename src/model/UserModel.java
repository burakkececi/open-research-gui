package model;

import model.abstraction.IUserModel;
import repository.UserRepository;
import repository.abstraction.IUserRepository;

import java.util.List;
import java.util.Observable;

public class UserModel extends Observable implements IUserModel {

    private String authenticatedUserName;
    private String authenticatedUserPassword;
    private final IUserRepository userRepository;

    public UserModel() {
        this.userRepository = new UserRepository();
    }

    public String getAuthenticatedUserName() {
        return authenticatedUserName;
    }

    public boolean login(String username, String password) {
        this.authenticatedUserName = username;
        this.authenticatedUserPassword = password;
        return userRepository.verifyUser(username, password);
    }

    public void logout() {
        this.authenticatedUserName = null;
        this.authenticatedUserPassword = null;
    }

    public void addFollower(String followerName) {
        userRepository.addFollower(getAuthenticatedUserName(), followerName);
    }

    public void addFollowing(String followingName) {
        userRepository.addFollowing(getAuthenticatedUserName(), followingName);
        userRepository.addFollower(followingName, getAuthenticatedUserName());
    }

    public List<String> getFollowers(String username) {
        return userRepository.getFollowers(getAuthenticatedUserName());
    }

    public List<String> getFollowing(String username) {
        return userRepository.getFollowing(getAuthenticatedUserName());
    }

    public void unfollow(String followingName) {
        userRepository.unfollow(getAuthenticatedUserName(), followingName);
        userRepository.removeFollower(followingName, getAuthenticatedUserName());
    }

    public List<String> getAllUserNames() {
        return userRepository.getAllUserNames();
    }

}
