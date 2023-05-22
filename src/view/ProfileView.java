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

public class ProfileView extends JFrame implements Observer {

    private UserModel userModel;
    private ReadingListModel readingListModel;
    private JLabel messageLabel;
    private DefaultListModel<String> listModel, paperModel, followersModel, followingModel;
    private JList<String> list, paperList, followersList, followingList;
    private JTextField listNameTextField;
    private JButton addListButton, removePaperButton, backButton;

    public ProfileView(ReadingListModel readingListModel, UserModel userModel) {
        this.userModel = userModel;
        this.readingListModel = readingListModel;

        // Frame initialization
        this.setTitle("OpenResearcher");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        // go back button
        backButton = new JButton("<-");
        backButton.setBounds(10, 10, 50, 40);
        backButton.addActionListener(e -> {
            setVisible(false);
            MainView mainView = new MainView(userModel);
            MainController mainController = new MainController(userModel, mainView);
        });
        this.add(backButton);

        // Header
        messageLabel = new JLabel("Welcome, " + userModel.getAuthenticatedUserName().toUpperCase() + ".");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel.setBounds(300, 50, 200, 40);
        this.add(messageLabel, BorderLayout.NORTH);

        // Create components
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Add padding to the list scroll pane

        paperModel = new DefaultListModel<>();
        paperList = new JList<>(paperModel);
        JScrollPane paperScrollPane = new JScrollPane(paperList);
        paperScrollPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Add padding to the paper scroll pane

        followersModel = new DefaultListModel<>();
        followersList = new JList<>(followersModel);
        JScrollPane followersScrollPane = new JScrollPane(followersList);
        followersScrollPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Add padding to the followers scroll pane

        followingModel = new DefaultListModel<>();
        followingList = new JList<>(followingModel);
        JScrollPane followingScrollPane = new JScrollPane(followingList);
        followingScrollPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Add padding to the following scroll pane

        listNameTextField = new JTextField(20);
        addListButton = new JButton("Add List");
        removePaperButton = new JButton("Remove Paper");

        // Create panels for each section
        JPanel readingListPanel = new JPanel(new BorderLayout());
        readingListPanel.add(new JLabel("Reading Lists"), BorderLayout.NORTH);
        readingListPanel.add(listScrollPane, BorderLayout.CENTER);

        JPanel papersPanel = new JPanel(new BorderLayout());
        papersPanel.add(new JLabel("Papers"), BorderLayout.NORTH);
        papersPanel.add(paperScrollPane, BorderLayout.CENTER);
        papersPanel.add(removePaperButton, BorderLayout.SOUTH);

        JPanel followersPanel = new JPanel(new BorderLayout());
        followersPanel.add(new JLabel("Followers"), BorderLayout.NORTH);
        followersPanel.add(followersScrollPane, BorderLayout.CENTER);

        JPanel followingPanel = new JPanel(new BorderLayout());
        followingPanel.add(new JLabel("Following"), BorderLayout.NORTH);
        followingPanel.add(followingScrollPane, BorderLayout.CENTER);

        // Create a container panel for the top sections
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(readingListPanel);
        topPanel.add(papersPanel);

        // Create a container panel for the bottom sections
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        bottomPanel.add(followersPanel);
        bottomPanel.add(followingPanel);

        // Add components to the main frame
        this.add(topPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        // Populate the lists with data
        Vector<IReadingList> readingLists = readingListModel.getReadingListsByCreatorName(userModel.getAuthenticatedUserName());
        for (IReadingList readingList : readingLists) {
            listModel.addElement(readingList.getName());
        }

        for (String follower : userModel.getFollowers(userModel.getAuthenticatedUserName())) {
            followersModel.addElement(follower);
        }

        for (String following : userModel.getFollowing(userModel.getAuthenticatedUserName())) {
            followingModel.addElement(following);
        }

        // Button listeners
        addListButton.addActionListener(e -> {
            String listName = listNameTextField.getText();
            if (!listName.isEmpty()) {
                listModel.addElement(listName);
                listNameTextField.setText("");
            }
        });

        removePaperButton.addActionListener(e -> {
            int selectedIndex = paperList.getSelectedIndex();
            if (selectedIndex != -1) {
                paperModel.remove(selectedIndex);
            }
        });

        // Add components to the frame
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(new JLabel("Reading Lists"), BorderLayout.NORTH);
        listPanel.add(listScrollPane, BorderLayout.CENTER);
        listPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding to the list panel

        JPanel listButtonPanel = new JPanel(new FlowLayout());
        listButtonPanel.add(listNameTextField);
        listButtonPanel.add(addListButton);
        listPanel.add(listButtonPanel, BorderLayout.SOUTH);
        add(listPanel, BorderLayout.WEST);

        JPanel paperPanel = new JPanel(new BorderLayout());
        paperPanel.add(new JLabel("Papers"), BorderLayout.NORTH);
        paperPanel.add(paperScrollPane, BorderLayout.CENTER);
        paperPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding to the paper panel

        JPanel paperButtonPanel = new JPanel(new FlowLayout());
        paperButtonPanel.add(removePaperButton);
        paperPanel.add(paperButtonPanel, BorderLayout.SOUTH);
        add(paperPanel, BorderLayout.CENTER);


        setVisible(true);
    }

    public void addListSelectionListener(ListSelectionListener listener) {
        list.addListSelectionListener(listener);
    }

    public void addListButtonListener(ActionListener listener) {
        addListButton.addActionListener(listener);
    }

    public void removePaperButtonListener(ActionListener listener) {
        removePaperButton.addActionListener(listener);
    }

    @Override
    public void update(Observable o, Object arg) {
        followersModel.clear();
        followingModel.clear();

        for (String follower : userModel.getFollowers(userModel.getAuthenticatedUserName())) {
            followersModel.addElement(follower);
        }

        for (String following : userModel.getFollowing(userModel.getAuthenticatedUserName())) {
            followingModel.addElement(following);
        }
    }

    public JTextField getListNameTextField() {
        return listNameTextField;
    }

    public DefaultListModel<String> getListModel() {
        return listModel;
    }

    public void setListNameTextField(String s) {
        listNameTextField.setText(s);
    }

    public JList<String> getList() {
        return list;
    }

    public JList<String> getPaperList() {
        return paperList;
    }

    public DefaultListModel<String> getPaperModel() {
        return paperModel;
    }

    public JList<String> getFollowersList() {
        return followersList;
    }

    public JList<String> getFollowingList() {
        return followingList;
    }

    public DefaultListModel<String> getFollowersModel() {
        return followersModel;
    }

    public DefaultListModel<String> getFollowingModel() {
        return followingModel;
    }
    
}
