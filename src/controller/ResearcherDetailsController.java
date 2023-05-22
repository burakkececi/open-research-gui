package controller;

import model.ReadingListModel;
import model.UserModel;
import view.ResearcherDetailsView;

import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ResearcherDetailsController {

    private final ReadingListModel readingListModel;
    private final ResearcherDetailsView view;
    private final UserModel userModel;
    public ResearcherDetailsController(ReadingListModel readingListModel, ResearcherDetailsView view, UserModel userModel) {
        this.readingListModel = readingListModel;
        this.view = view;
        this.userModel = userModel;
        view.addListSelectionListener(new ListSelectionListener());
        view.addFollowButtonListener(new FollowButtonListener());
        view.addUnfollowButtonListener(new UnfollowButtonListener());
        readingListModel.addObserver(view);
        view.setVisible(true);
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
                    readingListModel.hasChanged();
                }
            }
        }
    }

    private class FollowButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            userModel.addFollowing(view.getResearcherName());
            System.out.println("Followed");
        }
    }

    private class UnfollowButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            userModel.unfollow(view.getResearcherName());
        }
    }

}
