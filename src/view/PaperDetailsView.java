package view;

import controller.MainController;
import entity.abstraction.IReadingList;
import entity.paper.Paper;
import model.PaperModel;
import model.ReadingListModel;
import model.UserModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class PaperDetailsView extends JFrame implements Observer {

    private UserModel model;
    private ReadingListModel readingListModel;
    private Paper paper;
    private JLabel title;

    private JLabel  author, description;
    private JButton downloadButton, backButton, addReadingListButton;
    private JComboBox<IReadingList> readingListComboBox;

    public PaperDetailsView(ReadingListModel readingListModel, UserModel model, Paper paper) {
        this.model = model;
        this.readingListModel = readingListModel;
        this.paper = paper;

        this.setTitle("OpenResearcher");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // go back button
        backButton = new JButton("<-");
        backButton.setBounds(10, 10, 50, 40);
        backButton.addActionListener(e -> {
            setVisible(false);
            MainView mainView = new MainView(model);
            MainController mainController = new MainController(model, mainView);
        });
        this.add(backButton);

        title = new JLabel("<html><body><div width='700'>" + paper.getTitle() + "</div></body></html>");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setBounds(50, 100, 700, 60);
        this.add(title);

        author = new JLabel("<html><body><div width='600'>" +paper.getAuthors());
        author.setBounds(50, 150, 200, 40);
        this.add(author);

        description = new JLabel("<html><body><div width='600'>" + paper.toString());
        description.setBounds(50, 200, 200, 100);
        this.add(description);

        downloadButton = new JButton("Download");
        downloadButton.setBounds(50, 300, 200, 40);
        this.add(downloadButton);

        addReadingListButton = new JButton("Add to reading list");
        addReadingListButton.setBounds(400, 300, 200, 40);
        this.add(addReadingListButton);

        readingListComboBox = new JComboBox<>(readingListModel.getReadingListsByCreatorName(model.getAuthenticatedUserName()));
        readingListComboBox.setBounds(400, 350, 200, 40);
        this.add(readingListComboBox);
    }

    @Override
    public void update(Observable o, Object arg) {
        // update paper details
        PaperModel paperModel = (PaperModel) o;
        description.setText("<html><body><div width='600'>" + paperModel.getPaperByTitle(paper.getTitle()).toString());
    }

    public Paper getPaper() {
        return paper;
    }
    public JComboBox getComboBox() {
        return readingListComboBox;
    }
    public void downloadButtonListener(ActionListener listener) {
        downloadButton.addActionListener(listener);
    }
    public void addReadingListButtonListener(ActionListener listener){ addReadingListButton.addActionListener(listener);}

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public JLabel getDescription() {
        return description;
    }

    public void setDescription(JLabel description) {
        this.description = description;
    }
}
