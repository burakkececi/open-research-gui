package controller.main;

import controller.ProfileController;
import model.main.MainModel;
import model.profile.ProfileModel;
import view.main.MainView;
import view.profile.ProfileView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {

    MainModel model;
    MainView view;

    public MainController(MainModel model, MainView view) {
        this.model = model;
        this.view = view;

        view.addProfileListener(new ProfileListener());
        model.addObserver(view);
    }

    private class ProfileListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);

            // show main page
            ProfileModel pModel = new ProfileModel(model.getUser());
            ProfileView pView = new ProfileView(pModel);
            ProfileController controller = new ProfileController(pModel, pView);
        }
    }

//    private class ResearcherListener implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            setVisible(false);
//
//            // show main page
//            ResearcherModel model = new ResearcherModel();
//            ResearcherView view = new ResearcherView(model);
//            view.setVisible(true);
//        }
//    }
//
//    private class PaperListener implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            setVisible(false);
//
//            // show main page
//            PaperModel model = new PaperModel();
//            PaperView view = new PaperView(model);
//            view.setVisible(true);
//        }
//    }
}
