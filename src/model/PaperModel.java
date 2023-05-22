package model;

import entity.abstraction.IPaper;
import model.abstraction.IPaperModel;
import repository.PaperRepository;
import repository.abstraction.IPaperRepository;

import java.util.List;
import java.util.Observable;

public class PaperModel extends Observable implements IPaperModel  {

    private final IPaperRepository paperRepository;

    public PaperModel() {
        this.paperRepository = new PaperRepository();
    }

    public List<String> getPaperTitles() {
        return paperRepository.getPaperTitles();
    }

    public IPaper getPaperByTitle(String paperTitle) {
        return paperRepository.getPaperByTitle(paperTitle);
    }

    public List<IPaper> getPapers() {
        return paperRepository.getPapers();
    }

    public void downloadPaper(String paper) {
        paperRepository.downloadPaper(paper);
        setChanged();
        notifyObservers();
    }

}
