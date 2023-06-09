package controller.login;

import controller.main.MainController;
import entities.User;
import model.login.LoginModel;
import model.main.MainModel;
import view.login.LoginView;
import view.main.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginController {

    LoginView view;
    LoginModel model;

    public LoginController(LoginView loginView, LoginModel loginModel) {
        this.model = loginModel;
        this.view = loginView;

        view.addLoginListener(new LoginListener());
        model.addObserver(view);

    }

    private class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsername().getText();
            String passw = new String(view.getPasswordText().getPassword());

            User user = new User(username, passw);
            model.setUser(user);

            if (model.isUserValid()) {
                model.setText("Successfully login!");
                // set login view invisible
                view.setVisible(false);

                // show main page
                MainModel mModel = new MainModel(user);
                MainView mView = new MainView(mModel);
                MainController controller = new MainController(mModel, mView);

            } else {
                model.setText("Invalid username or password!");

            }
        }
    }
}
