package controller;

import model.PaperModel;
import model.ReadingListModel;
import model.UserModel;
import view.MainView;
import view.PaperView;
import view.ProfileView;
import view.ResearcherView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {

    private final UserModel model;
    private final MainView view;

    public MainController(UserModel model, MainView view) {
        this.model = model;
        this.view = view;

        view.addProfileListener(new ProfileListener());
        view.addResearcherListener(new ResearcherListener());
        view.addPaperListener(new PaperListener());
        model.addObserver(view);
    }

    private class ProfileListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);

            // show main page
            ReadingListModel readingListModel = new ReadingListModel(model);
            ProfileView profileView = new ProfileView(readingListModel, model);
            ProfileController profileController = new ProfileController(readingListModel, model, profileView);
        }
    }

    private class ResearcherListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);

            // show main page
            ReadingListModel readingListModel = new ReadingListModel(model);
            ResearcherView view = new ResearcherView(readingListModel, model);
            ResearcherController controller = new ResearcherController(readingListModel, model, view);
            view.setVisible(true);
        }
    }

    private class PaperListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);

            // show main page
            ReadingListModel readingListModel = new ReadingListModel(model);
            PaperView view = new PaperView(readingListModel, new PaperModel(), model);
            view.setVisible(true);
        }
    }

}
