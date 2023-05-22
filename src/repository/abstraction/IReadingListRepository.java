package repository.abstraction;

import entity.abstraction.IReadingList;

import java.util.List;

public interface IReadingListRepository {

    public void createReadingList(String creatorName, String readingListName);

    public List<IReadingList> getReadingListsByCreatorName(String creatorName);

    public void addPaperToReadingList(String creatorName, String readingListName, String paperName);

    public void removePaperFromReadingList(String creatorName, String readingListName, String paperName);

    public void removeReadingList(String creatorName, String readingListName);

    public List<String> getPaperNamesInReadingList(String creatorName, String readingListName) ;

}
