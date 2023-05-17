package entities;

import java.util.List;

public class Article extends Paper {

    private String volume;
    private String number;
    private String journal;

    public Article(List<String> authors, String title, String year, String DOI, String volume, String number, String journal) {
        super(authors, title, year, DOI);
        this.volume = volume;
        this.number = number;
        this.journal = journal;
    }
}
