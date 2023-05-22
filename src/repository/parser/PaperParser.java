package repository.parser;

import repository.abstraction.IParser;

import java.io.*;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaperParser implements IParser {
    private final String INPUT_FOLDER = "src/data/Homework3";
    private final String OUTPUT_FILE = "src/data/papers.csv";
    private final String FILE_PREFIX_ARTICLE = "A";
    private final int MAX_DOWNLOADS = 1500;

    public void parse() {
        try {
            File output = new File(OUTPUT_FILE);
            FileWriter writer = new FileWriter(output);
            File folder = new File(INPUT_FOLDER);
            File[] files = folder.listFiles();
            if (files != null) {
                System.out.println("Parsing " + files.length + " files.");
                for (File file : files) {
                    String fileName = file.getName();
                    if (fileName.endsWith(".bib")) {
                        String filePrefix = fileName.substring(0, 1);
                        if (filePrefix.equals(FILE_PREFIX_ARTICLE)) {
                            String[] data = parseArticle(file);
                            writeCSVLine(writer, "Article", data);
                        } else {
                            String[] data = parseConferencePaper(file);
                            writeCSVLine(writer, "Conference Paper", data);
                        }
                    }
                }
            }

            writer.close();
            System.out.println("Parsing completed. Output written to " + OUTPUT_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String[] parseConferencePaper(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        String[] data = new String[6];

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("author")) {
                data[0] = parseFieldValue(line);
            } else if (line.startsWith("title")) {
                data[1] = parseFieldValue(line);
            } else if (line.startsWith("year")) {
                data[2] = parseFieldValue(line);
            } else if (line.startsWith("doi")) {
                data[3] = parseFieldValue(line);
            } else if (line.startsWith("booktitle")) {
                data[4] = parseFieldValue(line);
            }
        }

        reader.close();
        data[5] = generateDownloads();
        return data;
    }

    private String[] parseArticle(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        String[] data = new String[8];

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("author")) {
                data[0] = parseFieldValue(line);
            } else if (line.startsWith("title")) {
                data[1] = parseFieldValue(line);
            } else if (line.startsWith("year")) {
                data[2] = parseFieldValue(line);
            } else if (line.startsWith("volume")) {
                data[3] = parseFieldValue(line);
            } else if (line.startsWith("number")) {
                data[4] = parseFieldValue(line);
            } else if (line.startsWith("doi")) {
                data[5] = parseFieldValue(line);
            } else if (line.startsWith("journal")) {
                data[6] = parseFieldValue(line);
            }
        }
        // print data as a string
        StringBuilder sb = new StringBuilder();
        reader.close();
        data[7] = generateDownloads();
        return data;
    }

    private static String parseFieldValue(String line) {
        if (line.startsWith("author")) {
            String[] parts = line.split("=");
            if (parts.length == 2) {
                String authors = parts[1].trim();
                authors = authors.replaceAll(",", "").trim();
                return authors;
            }
        } else {
            Pattern pattern = Pattern.compile("\\{([^}]+)\\}");
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                return matcher.group(1).trim();
            }
        }
        return "";
    }

    private String generateDownloads() {
        Random random = new Random();
        int downloads = random.nextInt(MAX_DOWNLOADS + 1);
        return String.valueOf(downloads);
    }

    private static void writeCSVLine(FileWriter writer, String type, String[] data) throws IOException {
        StringBuilder line = new StringBuilder(type);
        for (String field : data) {
            line.append(";").append(field);
        }
        line.append("\n");
        writer.write(line.toString());
    }

}
