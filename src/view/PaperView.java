package view;

import controller.PaperDetailsController;
import entity.abstraction.IPaper;
import entity.paper.Paper;
import model.PaperModel;
import model.ReadingListModel;
import model.UserModel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class PaperView extends JFrame implements Observer {

    private UserModel model;
    private ReadingListModel readingListModel;
    private JButton paperButton, backButton;
    private PaperModel paperModel;

    public PaperView(ReadingListModel readingListModel, PaperModel paperModel, UserModel model) {

        this.model = model;
        this.readingListModel = readingListModel;
        this.paperModel = paperModel;

        this.setTitle("OpenResearcher");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // go back button
        backButton = new JButton("<-");
        backButton.setBounds(10, 10, 50, 40);
        this.add(backButton);

        // list of buttons that shows each paper
        // each button will have a listener that will open a new window with the paper details
        for (int i= 0; i < paperModel.getPapers().size(); i++) {
            IPaper paper = paperModel.getPapers().get(i);
            paperButton = new JButton(paper.getTitle());
            // each button have different y position
            paperButton.setBounds(300, 50 + i * 50, 200, 40);
            paperButton.addActionListener(e -> {
                setVisible(false);
                PaperDetailsView paperDetailsView = new PaperDetailsView(readingListModel, model, (Paper) paper);
                PaperDetailsController paperDetailsController = new PaperDetailsController(paperModel, model, readingListModel, paperDetailsView);
                paperDetailsView.setVisible(true);
            });
            this.add(paperButton);
        }
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

}
