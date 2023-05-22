package controller;

import model.ReadingListModel;
import model.UserModel;
import view.ProfileView;

import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProfileController {

    private final ReadingListModel readingListModel;
    private final UserModel userModel;
    private final ProfileView view;


    public ProfileController(ReadingListModel readingListModel, UserModel userModel, ProfileView view) {
        this.readingListModel = readingListModel;
        this.userModel = userModel;
        this.view = view;

        view.addListButtonListener(new ListButtonListener());
        view.removePaperButtonListener(new PaperButtonListener());
        view.addListSelectionListener(new ListSelectionListener());
        readingListModel.addObserver(view);
        userModel.addObserver(view);
    }


    private class ListButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String listName = view.getListNameTextField().getText();

            readingListModel.createReadingList(listName);
            view.getListModel().addElement(listName);
            view.setListNameTextField("");
        }
    }

    private class PaperButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedList = view.getList().getSelectedValue();
            if (selectedList != null) {
                String selectedPaper = view.getPaperList().getSelectedValue();
                if (selectedPaper != null) {
                    readingListModel.removePaperFromReadingList(selectedList, selectedPaper);
                    view.getPaperModel().remove(view.getPaperList().getSelectedIndex());
                }
            }
        }
    }

    private class ListSelectionListener implements javax.swing.event.ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                String selectedList = view.getList().getSelectedValue();
                if (selectedList != null) {
                    view.getPaperModel().clear();
                    List<String> papers = readingListModel.getPaperTitlesFromReadingList(selectedList);
                    for (String paper : papers) {
                        view.getPaperModel().addElement(paper);
                    }
                }
            }
        }
    }

}
