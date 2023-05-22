package model.abstraction;

import java.util.List;

public interface IUserModel {

    public String getAuthenticatedUserName();

    public boolean login(String username, String password);

    public void logout();

    public void addFollower(String followerName);

    public void addFollowing(String followingName);

    public List<String> getFollowers(String username);

    public List<String> getFollowing(String username);

    public void unfollow(String followingName);

}
