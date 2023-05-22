package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.abstraction.IReadingList;
import entity.paper.ReadingList;
import repository.abstraction.IReadingListRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadingListRepository implements IReadingListRepository {

    private final String filePath = "src/data/reading_lists.json";
    private final List<ReadingList> readingLists;
    private final Gson gson;

    public ReadingListRepository() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        readingLists = loadReadingLists();
    }

    public void createReadingList(String creatorName, String readingListName) {
        for (ReadingList readingList : readingLists) {
            if (readingList.getResearcherName().equals(creatorName) && readingList.getName().equals(readingListName)) {
                return;
            }
        }

        ReadingList readingList = new ReadingList(readingListName, creatorName);
        readingLists.add(readingList);
        saveReadingLists();
    }

    public List<IReadingList> getReadingListsByCreatorName(String creatorName) {
        List<IReadingList> result = new ArrayList<>();
        for (ReadingList readingList : readingLists) {
            if (readingList.getResearcherName().equals(creatorName)) {
                result.add(readingList);
            }
        }
        return result;
    }

    public void addPaperToReadingList(String creatorName, String readingListName, String paperName) {
        for (ReadingList readingList : readingLists) {
            if (readingList.getName().equals(readingListName) && readingList.getResearcherName().equals(creatorName)) {
                if (!readingList.getPapers().contains(paperName)) {
                    readingList.addPaper(paperName);
                    saveReadingLists();
                    return;
                }
            }
        }
    }

    public void removePaperFromReadingList(String creatorName, String readingListName, String paperName) {
        for (ReadingList readingList : readingLists) {
            if (readingList.getName().equals(readingListName) && readingList.getResearcherName().equals(creatorName)) {
                readingList.getPapers().remove(paperName);
                saveReadingLists();
                return;
            }
        }
    }

    private List<ReadingList> loadReadingLists() {
        List<ReadingList> loadedReadingLists = new ArrayList<>();
        File file = new File(filePath);
        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                Type listType = new TypeToken<List<ReadingList>>() {
                }.getType();
                loadedReadingLists = gson.fromJson(reader, listType);
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file: " + e.getMessage());
            }
        }
        return loadedReadingLists != null ? loadedReadingLists : new ArrayList<>();
    }

    private void saveReadingLists() {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(readingLists, writer);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public void removeReadingList(String creatorName, String readingListName) {
        Iterator<ReadingList> iterator = readingLists.iterator();
        while (iterator.hasNext()) {
            ReadingList readingList = iterator.next();
            if (readingList.getName().equals(readingListName) && readingList.getResearcherName().equals(creatorName)) {
                iterator.remove();
                saveReadingLists();
                return;
            }
        }
    }

    public List<String> getPaperNamesInReadingList(String creatorName, String readingListName) {
        for (ReadingList readingList : readingLists) {
            if (readingList.getResearcherName().equals(creatorName) && readingList.getName().equals(readingListName)) {
                return readingList.getPapers();
            }
        }
        return new ArrayList<>();
    }

}
