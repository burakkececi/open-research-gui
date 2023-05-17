package view.login;

import controller.login.LoginController;
import model.login.LoginModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;

public class LoginView extends JFrame implements ILoginView {

    private JLabel userLabel, passwordLabel, messageLabel; // labels for boxes
    private JTextField username; // username input
    private JPasswordField password; // password input
    private JButton loginButton; // login button

    private LoginModel model;

    public LoginView(LoginModel model) {

        this.model = model;

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

        // Adding username and input field
        userLabel = new JLabel("Username");
        userLabel.setBounds(200, 200, 100, 40);
        this.add(userLabel);

        username = new JTextField();
        username.setBounds(300, 200, 300, 40);
        this.add(username);

        // Adding password and input field
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(200, 250, 100, 40);
        this.add(passwordLabel);

        password = new JPasswordField();
        password.setBounds(300, 250, 300, 40);
        this.add(password);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(300, 300, 300, 40);
        this.add(loginButton);

        // Validation message
        messageLabel = new JLabel("");
        messageLabel.setBounds(350, 350, 200, 40);
        this.add(messageLabel);

        setVisible(true);
    }

    public JTextField getUsername() {
        return username;
    }

    public JPasswordField getPasswordText() {
        return password;
    }

    public void addLoginListener(ActionListener mal) {
        loginButton.addActionListener(mal);
    }

    @Override
    public void update(Observable o, Object arg) {
        messageLabel.setText((String) arg);
    }

}

