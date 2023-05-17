package model.profile;

import entities.User;

import java.util.List;
import java.util.Map;
import java.util.Observable;

public class ProfileModel extends Observable {

    private User user;


    public ProfileModel(User user) {
        this.user = user;
    }

    public void setReadingList(Map<String, List<String>> readingList) {
        this.user.setReadingList(readingList);
        setChanged();
        notifyObservers();
    }

    public Map<String, List<String>> getReadingList() {
        return this.user.getReadingList();
    }
    public String getUsername() {
        return user.getUsername();
    }
}
