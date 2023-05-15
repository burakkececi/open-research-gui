package view.main;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class MainView extends JFrame implements IMainView {

    private JLabel messageLabel;

    public MainView() {
        // Frame initialization
        this.setTitle("OpenResearcher");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Header
        messageLabel = new JLabel("Log In");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 24));
        messageLabel.setBounds(400, 100, 150, 40);
        this.add(messageLabel);
    }

    @Override
    public void showWindow() {

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
