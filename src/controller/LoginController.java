package controller;

import model.UserModel;
import view.LoginView;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginController {

    private final LoginView view;
    private final UserModel model;

    public LoginController(LoginView loginView, UserModel userModel) {
        this.model = userModel;
        this.view = loginView;

        view.addLoginListener(new LoginListener());
        model.addObserver(view);
    }

    private class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsername().getText();
            String password = new String(view.getPasswordText().getPassword());

            if (model.login(username, password)) {
                view.setMessage("Successfully login!");
                view.setVisible(false);

                // show main page
                MainView mView = new MainView(model);
                MainController controller = new MainController(model, mView);
            } else {
                view.setMessage("Invalid username or password!");

            }
        }
    }

}
