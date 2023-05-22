package entity.paper;

import entity.abstraction.IReadingList;

import java.util.ArrayList;
import java.util.List;

public class ReadingList implements IReadingList {

    private final String name;
    private final String researcherName;
    private List<String> papers;

    public ReadingList(String name, String researcherName) {
        this.name = name;
        this.researcherName = researcherName;
        this.papers = new ArrayList<>();
    }

    public void addPaper(String paper) {
        for (String p : this.papers) {
            if (p.equals(paper)) {
                return;
            }
        }
        this.papers.add(paper);
    }

    public void setPapers(List<String> papers) {
        this.papers = papers;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getPapers() {
        return this.papers;
    }

    public String getResearcherName() {
        return researcherName;
    }

    public String toString() {
        return this.name;
    }

}
