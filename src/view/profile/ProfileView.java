package view.profile;

import model.profile.ProfileModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.*;

public class ProfileView extends JFrame implements Observer {

    private ProfileModel model;

    private JLabel messageLabel;
    private Map<String, List<String>> readingLists;
    private DefaultListModel<String> listModel, paperModel;
    private JList<String> list, paperList;
    private JTextField listNameTextField;
    private JButton addListButton, removePaperButton;


    public ProfileView(ProfileModel model) {
        this.model = model;

        // Frame initialization
        this.setTitle("OpenResearcher");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Header
        messageLabel = new JLabel("Welcome, " + model.getUsername().toUpperCase() + ".");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel.setBounds(300, 50, 200, 40);
        this.add(messageLabel);

        this.readingLists = new HashMap<>();

        // Create components
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Add padding to the list scroll pane

        paperModel = new DefaultListModel<>();
        paperList = new JList<>(paperModel);
        JScrollPane paperScrollPane = new JScrollPane(paperList);
        paperScrollPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Add padding to the paper scroll pane

        listNameTextField = new JTextField(20);
        addListButton = new JButton("Add List");
        removePaperButton = new JButton("Remove Paper");

        // Add components to the frame
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(new JLabel("Reading Lists"), BorderLayout.NORTH);
        listPanel.add(listScrollPane, BorderLayout.CENTER);
        listPanel.setBorder(new EmptyBorder(100, 20, 50, 20)); // Add padding to the list panel
        JPanel listButtonPanel = new JPanel(new FlowLayout());
        listButtonPanel.add(listNameTextField);
        listButtonPanel.add(addListButton);
        listPanel.add(listButtonPanel, BorderLayout.SOUTH);
        add(listPanel, BorderLayout.WEST);

        JPanel paperPanel = new JPanel(new BorderLayout());
        paperPanel.add(new JLabel("Papers"), BorderLayout.NORTH);
        paperPanel.add(paperScrollPane, BorderLayout.CENTER);
        paperPanel.setBorder(new EmptyBorder(100, 20, 50, 20)); // Add padding to the list panel
        JPanel paperButtonPanel = new JPanel(new FlowLayout());
        paperButtonPanel.add(removePaperButton);
        paperPanel.add(paperButtonPanel, BorderLayout.SOUTH);
        add(paperPanel, BorderLayout.CENTER);


        model.setReadingList(readingLists);

        setVisible(true);

    }

    public void addListButtonListener(ActionListener mal) {
        addListButton.addActionListener(mal);
    }

    public void removePaperButtonListener(ActionListener mal) {
        removePaperButton.addActionListener(mal);
    }

    public void addListSelectionListener(ListSelectionListener mal) {
        list.addListSelectionListener(mal);
    }


    @Override
    public void update(Observable o, Object arg) {
        ProfileModel model = (ProfileModel) o;
        this.readingLists = ((ProfileModel) o).getReadingList();

    }

    public JTextField getListNameTextField() {
        return listNameTextField;
    }

    public Map<String, List<String>> getReadingLists() {
        return readingLists;
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


//    public static void main(String[] args) {
//        ProfileView profileView = new ProfileView();
//        profileView.setVisible(true);
//    }
}
