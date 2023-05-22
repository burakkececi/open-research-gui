package model.abstraction;

import entity.paper.ReadingList;

import java.util.List;

public interface IReadingListModel {

    public void createReadingList(String readingListName);

    public List<ReadingList> getReadingListsByCreatorName();

    public void addPaperToReadingList(String readingListName, String paperName);

    public void removePaperFromReadingList(String readingListName, String paperName);

    public void removeReadingList(String readingListName);

}
