package entity.abstraction;

import java.util.List;

public interface IReadingList {

    public String getName();

    public List<String> getPapers();

    public void addPaper(String paper);

    public void setPapers(List<String> papers);

    public String getResearcherName();

}
