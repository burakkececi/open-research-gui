package view;

import model.UserModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class MainView extends JFrame implements Observer {

    private JLabel messageLabel;
    private JButton profileButton, researcherButton, paperButton, faqButton;
    private UserModel model;

    public MainView(UserModel model) {

        this.model = model;

        // Frame initialization
        this.setTitle("OpenResearcher");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Header
        messageLabel = new JLabel("Open Research");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 24));
        messageLabel.setBounds(300, 100, 200, 40);
        this.add(messageLabel);

        // profile
        profileButton = new JButton("My Profile");
        profileButton.setBounds(200, 200, 150, 100);
        this.add(profileButton);

        researcherButton = new JButton("Researchers");
        researcherButton.setBounds(400, 200, 150, 100);
        this.add(researcherButton);

        paperButton = new JButton("Papers");
        paperButton.setBounds(200, 350, 150, 100);
        this.add(paperButton);

        faqButton = new JButton("FAQ");
        faqButton.setBounds(400, 350, 150, 100);
        this.add(faqButton);

        setVisible(true);

    }

    public void addProfileListener(ActionListener mal) {
        profileButton.addActionListener(mal);
    }

    public void addResearcherListener(ActionListener mal) {
        researcherButton.addActionListener(mal);
    }

    public void addPaperListener(ActionListener mal) {
        paperButton.addActionListener(mal);
    }

    public void addFAQListener(ActionListener mal) {
        faqButton.addActionListener(mal);
    }

    @Override
    public void update(Observable o, Object arg) {
        ///////////////////////////////////
    }


}
