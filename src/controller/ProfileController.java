package controller;

import model.profile.ProfileModel;
import view.profile.ProfileView;

import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProfileController {

    private ProfileModel model;
    private ProfileView view;


    public ProfileController(ProfileModel model, ProfileView view) {
        this.model = model;
        this.view = view;

        view.addListButtonListener(new ListButtonListener());
        view.removePaperButtonListener(new PaperButtonListener());
        view.addListSelectionListener(new ListSelectionListener());
        model.addObserver(view);
    }


    private class ListButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String listName = view.getListNameTextField().getText();
            if (!view.getReadingLists().containsKey(listName)) {
                view.getReadingLists().put(listName, new ArrayList<>());
                view.getListModel().addElement(listName);
                model.setReadingList(view.getReadingLists());
            }
            view.setListNameTextField("");
        }
    }

    private class PaperButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedList = view.getList().getSelectedValue();
            if (selectedList != null) {
                int[] selectedIndices = view.getPaperList().getSelectedIndices();
                List<String> papers = new ArrayList<>(view.getReadingLists().get(selectedList)); // Create a copy of the papers list
                for (int i = selectedIndices.length - 1; i >= 0; i--) {
                    int selectedIndex = selectedIndices[i];
                    papers.remove(selectedIndex);
                    view.getPaperModel().remove(selectedIndex);
                }
                view.getReadingLists().put(selectedList, papers); // Update the readingLists map with the modified papers list
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
                    List<String> papers = view.getReadingLists().get(selectedList);
                    for (String paper : papers) {
                        view.getPaperModel().addElement(paper);
                    }
                }
            }
        }
    }
}
