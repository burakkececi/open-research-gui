package entities;

import java.util.List;

public class ConferencePaper extends Paper{

    private String bookTitle;
    public ConferencePaper(List<String> authors, String title, String year, String DOI, String bookTitle) {
        super(authors, title, year, DOI);
        this.bookTitle = bookTitle;
    }

    public String getBookTitle() {
        return bookTitle;
    }
}
