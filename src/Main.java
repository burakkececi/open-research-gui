import controller.login.LoginController;
import dataAccess.IUserRepository;
import dataAccess.UserRepository;
import entities.User;
import model.login.LoginModel;
import view.login.LoginView;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<User> users = new ArrayList<>();

        // create users
        User muslera = new User("muslera", "12345");
        User icardi = new User("icardi", "12345");
        User kerem = new User("kerem", "12345");
        User boey = new User("boey", "12345");
        User mertens = new User("mertens", "12345");

        // adding them to list
        users.add(muslera);
        users.add(icardi);
        users.add(kerem);
        users.add(boey);
        users.add(mertens);

        // saving users to repository pasha'm
        IUserRepository iUserRepository = new UserRepository();
        iUserRepository.saveUsers(users);

        LoginModel model = new LoginModel(); // model
        LoginView view = new LoginView(model); // view
        LoginController controller = new LoginController(view, model); // controller
        view.setVisible(true);
    }
}