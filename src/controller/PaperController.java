package controller;

import model.PaperModel;
import view.PaperView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaperController {

    private final PaperView view;
    private final PaperModel model;

    public PaperController(PaperModel model, PaperView view) {
        this.model = model;
        this.view = view;

        view.setVisible(true);
    }

    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Back button pressed");
            view.setVisible(false);

            //MainController mainController = new MainController();
        }
    }

}
