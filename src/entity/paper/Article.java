package entity.paper;

import java.util.List;

public class Article extends Paper {

    private final String volume;
    private final String number;
    private final String journal;

    public Article(List<String> authors, String title, String year, String DOI, String volume, String number, String journal, int numberOfDownloads) {
        super(authors, title, year, DOI, numberOfDownloads);
        this.volume = volume;
        this.number = number;
        this.journal = journal;
    }

    public String toString() {
        return "Year:" + getYear() + ", DOI:" + getDOI() + ", Volume:" + volume + ", Number:" + number + ", Journal:" + journal + ", Number Of Downloads:" + getNumberOfDownloads();
    }

}
