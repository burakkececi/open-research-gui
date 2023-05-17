import controller.login.LoginController;
import dataAccess.IUserRepository;
import dataAccess.UserRepository;
import entities.User;
import model.login.LoginModel;
import view.login.LoginView;

public class Main {
    public static void main(String[] args) {

        // create users
        User muslera = new User("muslera", "12345");
        User icardi = new User("icardi", "12345");
        User kerem = new User("kerem", "12345");
        User boey = new User("boey", "12345");
        User mertens = new User("mertens", "12345");

        // saving users to repository pasha'm
        IUserRepository iUserRepository = new UserRepository();
        iUserRepository.saveUser(muslera);
        iUserRepository.saveUser(icardi);
        iUserRepository.saveUser(kerem);
        iUserRepository.saveUser(boey);
        iUserRepository.saveUser(mertens);

        LoginModel model = new LoginModel(); // model
        LoginView view = new LoginView(model); // view
        LoginController controller = new LoginController(view, model); // controller
    }
}