package controller;

import model.ReadingListModel;
import model.UserModel;
import view.ResearcherView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResearcherController {

    private final ReadingListModel readingListModel;
    private final UserModel userModel;
    private final ResearcherView view;

    public ResearcherController(ReadingListModel readingListModel, UserModel model, ResearcherView view) {
        this.readingListModel = readingListModel;
        this.userModel = model;
        this.view = view;

        model.addObserver(view);
        readingListModel.addObserver(view);
    }

    private class ResearcherButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

}
