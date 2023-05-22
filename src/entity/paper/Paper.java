package entity.paper;

import entity.abstraction.IPaper;

import java.util.List;

public abstract class Paper implements IPaper {

    private final List<String> authors;
    private final String title;
    private final String year;
    private final String DOI;
    private final int numberOfDownloads;

    public Paper(List<String> authors, String title, String year, String DOI, int numberOfDownloads) {
        this.authors = authors;
        this.title = title;
        this.year = year;
        this.DOI = DOI;
        this.numberOfDownloads = numberOfDownloads;
    }

    public int getNumberOfDownloads() {
        return numberOfDownloads;
    }

    public String getAuthors() {
        String authorsString = formatList(authors);
        if (authorsString.startsWith("{") && authorsString.endsWith("}")) {
            authorsString = authorsString.substring(1, authorsString.length() - 1);
        }
        return authorsString;
    }

    public String formatList(List<String> stringList) {
        if (stringList == null || stringList.isEmpty()) {
            return "";
        } else if (stringList.size() == 1) {
            return stringList.get(0);
        } else {
            StringBuilder formattedString = new StringBuilder();
            for (int i = 0; i < stringList.size() - 1; i++) {
                formattedString.append(stringList.get(i)).append(", ");
            }
            formattedString.append("and ").append(stringList.get(stringList.size() - 1));
            return formattedString.toString();
        }
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
