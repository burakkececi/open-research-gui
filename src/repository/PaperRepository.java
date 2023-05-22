package repository;

import entity.abstraction.IPaper;
import entity.paper.Article;
import entity.paper.ConferencePaper;
import repository.abstraction.IPaperRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaperRepository implements IPaperRepository {

    private final String filePath = "src/data/papers.csv";

    public List<String> getPaperTitles() {
        List<String> paperTitles = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", 3);
                if (parts.length >= 2) {
                    String title = parts[2].trim().split(";", 2)[0];
                    paperTitles.add(title);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        return paperTitles;
    }

    public IPaper getPaperByTitle(String paperTitle) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", 3);
                if (parts.length >= 2) {
                    String title = parts[2].trim().split(";", 2)[0];
                    if (title.equals(paperTitle)) {
                        String[] paperDetails = line.split(";", 11);
                        String type = paperDetails[0].trim();
                        List<String> authors = parseAuthors(paperDetails[1]);
                        String year = paperDetails[3].trim();

                        if (type.equals("Article")) {
                            String volume = paperDetails[4].trim();
                            String number = paperDetails[5].trim();
                            String DOI = paperDetails[6].trim();
                            String journal = paperDetails[7].trim();
                            int numberOfDownloads = Integer.parseInt(paperDetails[paperDetails.length - 1].trim());
                            return new Article(authors, title, year, DOI, volume, number, journal, numberOfDownloads);
                        } else if (type.equals("Conference Paper")) {
                            String DOI = paperDetails[5].trim();
                            String bookTitle = paperDetails[5].trim();
                            int numberOfDownloads = Integer.parseInt(paperDetails[paperDetails.length - 1].trim());
                            return new ConferencePaper(authors, title, year, DOI, bookTitle, numberOfDownloads);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        return null; // Paper with the given title not found
    }

    private List<String> parseAuthors(String authorsString) {
        List<String> authors = new ArrayList<>();
        String[] authorNames = authorsString.split(" and ");
        for (String author : authorNames) {
            authors.add(author.trim());
        }
        return authors;
    }

    public List<IPaper> getPapers() {
        List<IPaper> papers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", 3);
                if (parts.length >= 2) {
                    String title = parts[2].trim().split(";", 2)[0];
                    IPaper paper = getPaperByTitle(title);
                    if (paper != null) {
                        papers.add(paper);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        return papers;
    }

    public void downloadPaper(String paperTitle) {
        List<String> fileLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", 3);
                if (parts.length >= 2) {
                    String title = parts[2].trim().split(";", 2)[0];
                    if (title.equals(paperTitle)) {
                        String[] paperDetails = line.split(";", 11);
                        int downloadCount = Integer.parseInt(paperDetails[paperDetails.length - 1].trim());
                        downloadCount++;
                        paperDetails[paperDetails.length - 1] = String.valueOf(downloadCount);
                        line = String.join(";", paperDetails);
                    }
                }
                fileLines.add(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        // Update the file with the modified download count
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String line : fileLines) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

}
