package controller.login;

import entities.User;
import model.login.LoginModel;
import view.login.LoginView;
import view.main.IMainView;
import view.main.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginController implements ActionListener {

    LoginView view;
    LoginModel model;

    public LoginController(LoginView loginView, LoginModel loginModel) {
        this.model = loginModel;
        this.view = loginView;

        model.addObserver(view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = view.getUsername().getText();
        String passw = new String(view.getPasswordText().getPassword());

        if (model.isUserValid(new User(user, passw))) {
            view.getMessageLabel().setText("Successfully login!");
            // set login view invisible
            view.setVisible(false);

            // show main page
            MainView iMainView = new MainView();
            iMainView.showWindow();
            iMainView.setVisible(true);

        } else {
            view.getMessageLabel().setText("Invalid username or password!");
        }
    }
}
