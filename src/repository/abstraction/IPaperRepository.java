package repository.abstraction;

import entity.abstraction.IPaper;

import java.util.List;

public interface IPaperRepository {

    public List<String> getPaperTitles();

    public IPaper getPaperByTitle(String title);

    public List<IPaper> getPapers();

    public void downloadPaper(String paperTitle);

}

