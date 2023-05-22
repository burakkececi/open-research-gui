package model.abstraction;

import entity.abstraction.IPaper;

import java.util.List;

public interface IPaperModel {

    public List<String> getPaperTitles();

    public IPaper getPaperByTitle(String paperTitle);

    public List<IPaper> getPapers();

    public void downloadPaper(String paper);

}
