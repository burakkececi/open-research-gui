package model;

import entity.abstraction.IReadingList;
import model.abstraction.IUserModel;
import repository.ReadingListRepository;
import repository.abstraction.IReadingListRepository;

import java.util.List;
import java.util.Observable;
import java.util.Vector;

public class ReadingListModel extends Observable {

    private final IReadingListRepository readingListRepository;
    private final IUserModel userModel;

    public ReadingListModel(IUserModel userModel) {
        this.readingListRepository = new ReadingListRepository();
        this.userModel = userModel;
    }

    public void createReadingList(String readingListName) {
        readingListRepository.createReadingList(userModel.getAuthenticatedUserName(), readingListName);
    }

    public Vector<IReadingList> getReadingListsByCreatorName(String researcherName) {
        return convertListToVector(readingListRepository.getReadingListsByCreatorName(researcherName));
    }

    public void addPaperToReadingList(String readingListName, String paperName) {
        readingListRepository.addPaperToReadingList(userModel.getAuthenticatedUserName(), readingListName, paperName);
    }

    public void removePaperFromReadingList(String readingListName, String paperName) {
        readingListRepository.removePaperFromReadingList(userModel.getAuthenticatedUserName(), readingListName, paperName);
    }

    public void removeReadingList(String readingListName) {
        readingListRepository.removeReadingList(userModel.getAuthenticatedUserName(), readingListName);
    }

    public IUserModel getUserModel() {
        return userModel;
    }

    public List<String> getPaperTitlesFromReadingList(String readingListName) {
        return readingListRepository.getPaperNamesInReadingList(userModel.getAuthenticatedUserName(), readingListName);
    }

    private static <T> Vector<T> convertListToVector(List<T> list) {
        Vector<T> vector = new Vector<>(list.size());
        vector.addAll(list);
        return vector;
    }

}
