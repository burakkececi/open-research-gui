package view;

import controller.MainController;
import entity.abstraction.IReadingList;
import model.ReadingListModel;
import model.UserModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class ResearcherDetailsView extends JFrame implements Observer {

    private JLabel messageLabel;
    private String researcherName;
    private ReadingListModel readingListModel;
    private DefaultListModel<String> listModel;
    private DefaultListModel<String> paperModel;
    private JList<String> list;
    private JList<String> paperList;
    private JButton backButton, followButton, unfollowButton;

    public DefaultListModel<String> getPaperModel() {
        return paperModel;
    }

    public ResearcherDetailsView(ReadingListModel readingListModel, UserModel userModel, String researcherName) {
        this.researcherName = researcherName;
        this.readingListModel = readingListModel;

        // Frame initialization
        this.setTitle("OpenResearcher");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Header
        messageLabel = new JLabel("Profile of "+ researcherName);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel.setBounds(300, 50, 200, 40);
        this.add(messageLabel);

        // Create components
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Add padding to the list scroll pane

        paperModel = new DefaultListModel<>();
        paperList = new JList<>(paperModel);
        JScrollPane paperScrollPane = new JScrollPane(paperList);
        paperScrollPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Add padding to the paper scroll pane

        followButton = new JButton("Follow");
        followButton.setBounds(50, 400, 200, 40);
        unfollowButton = new JButton("Unfollow");
        unfollowButton.setBounds(400, 400, 200, 40);

        // Add components to the frame
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(new JLabel("Reading Lists"), BorderLayout.NORTH);
        listPanel.add(listScrollPane, BorderLayout.CENTER);
        listPanel.setBorder(new EmptyBorder(100, 20, 50, 10)); // Add padding to the list panel

        JPanel listButtonPanel = new JPanel(new FlowLayout());
        listButtonPanel.add(followButton);
        listPanel.add(listButtonPanel, BorderLayout.SOUTH);

        JPanel paperPanel = new JPanel(new BorderLayout());
        paperPanel.add(new JLabel("Papers"), BorderLayout.NORTH);
        paperPanel.add(paperScrollPane, BorderLayout.CENTER);
        paperPanel.setBorder(new EmptyBorder(100, 20, 50, 10)); // Add padding to the list panel

        JPanel listButtonPaperPanel = new JPanel(new FlowLayout());
        listButtonPanel.add(unfollowButton);
        paperPanel.add(listButtonPaperPanel, BorderLayout.SOUTH);

        backButton = new JButton("<-");
        backButton.setBounds(10, 10, 50, 40);
        backButton.addActionListener(e -> {
            setVisible(false);
            MainView mainView = new MainView(userModel);
            MainController mainController = new MainController(userModel, mainView);
        });
        this.add(backButton);

        // Adjust heights of list and paper panels
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.add(listPanel);
        mainPanel.add(paperPanel);
        add(mainPanel, BorderLayout.CENTER);

        // populate
        Vector<IReadingList> readingLists = readingListModel.getReadingListsByCreatorName(researcherName);
        for (IReadingList readingList : readingLists) {
            listModel.addElement(readingList.getName());
        }


        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public void addListSelectionListener(ListSelectionListener mal) {
        list.addListSelectionListener(mal);
    }

    public void addFollowButtonListener(ActionListener mal) {
        followButton.addActionListener(mal);
    }

    public void addUnfollowButtonListener(ActionListener mal) {
        unfollowButton.addActionListener(mal);
    }


    public String getResearcherName() {
        return researcherName;
    }

    public DefaultListModel<String> getListModel() {
        return listModel;
    }


    public JList<String> getList() {
        return list;
    }


}
