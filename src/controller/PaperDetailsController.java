package controller;

import entity.paper.Paper;
import model.PaperModel;
import model.ReadingListModel;
import model.UserModel;
import view.PaperDetailsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaperDetailsController {

    private final PaperModel model;
    private UserModel userModel;
    private ReadingListModel readingListModel;
    private final PaperDetailsView view;

    public PaperDetailsController(PaperModel model, UserModel userModel, ReadingListModel readingListModel, PaperDetailsView paperDetailsView) {
        this.model = model;
        this.userModel = userModel;
        this.readingListModel = readingListModel;
        this.view = paperDetailsView;
        view.downloadButtonListener(new downloadButtonListener());
        view.addReadingListButtonListener(new AddReadingListListener());
        model.addObserver(view);
        userModel.addObserver(view);
        view.setVisible(true);
    }

    private class downloadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.downloadPaper(view.getPaper().getTitle());
            view.setPaper((Paper) model.getPaperByTitle(view.getPaper().getTitle()));
        }
    }

    private class AddReadingListListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            readingListModel.addPaperToReadingList(view.getComboBox().getSelectedItem().toString(),view.getPaper().getTitle());
        }
    }

}
