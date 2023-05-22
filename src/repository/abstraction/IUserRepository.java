package repository.abstraction;

import entity.User;

import java.util.List;

public interface IUserRepository {

    public void saveUser(User user);

    public void deleteUser(String username);

    public void addFollower(String username, String followerName);

    public void addFollowing(String username, String followingName);

    public List<String> getFollowers(String username);

    public List<String> getFollowing(String username);

    public boolean verifyUser(String username, String password);

    public void unfollow(String username, String followingName);

    public List<String> getAllUserNames();

    public void removeFollower(String username, String followerName);

}
