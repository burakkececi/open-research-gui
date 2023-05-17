package entities;

import java.util.List;

public abstract class Paper {

    private List<String> authors;
    private String title;
    private String year;
    private String DOI;

    public Paper(List<String> authors, String title, String year, String DOI) {
        this.authors = authors;
        this.title = title;
        this.year = year;
        this.DOI = DOI;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getDOI() {
        return DOI;
    }


}
