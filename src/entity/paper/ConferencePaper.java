package entity.paper;

import java.util.List;

public class ConferencePaper extends Paper {

    private final String bookTitle;

    public ConferencePaper(List<String> authors, String title, String year, String DOI, String bookTitle, int numberOfDownloads) {
        super(authors, title, year, DOI, numberOfDownloads);
        this.bookTitle = bookTitle;
    }

    public String toString() {
        return "Year:" + getYear() + ", DOI:" + getDOI() + ", BookTitle:" + bookTitle + ", Number Of Downloads:" + getNumberOfDownloads();
    }

}
