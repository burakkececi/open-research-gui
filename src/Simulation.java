import controller.LoginController;
import entity.User;
import model.UserModel;
import repository.ReadingListRepository;
import repository.UserRepository;
import repository.parser.PaperParser;
import view.LoginView;

import java.io.File;

public class Simulation {

    public void start() {
        try {
            File f1 = new File("src/data/reading_lists.json");
            File f2 =new File("src/data/users.xml");
            f1.delete();
            f2.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        User user1 = new User("user1", "user1");
        User user2 = new User("user2", "user2");
        User user3 = new User("user3", "user3");
        User user4 = new User("user4", "user4");
        User user5 = new User("user5", "user5");

        UserRepository userRepository = new UserRepository();
        userRepository.saveUser(user1);
        userRepository.saveUser(user2);
        userRepository.saveUser(user3);
        userRepository.saveUser(user4);
        userRepository.saveUser(user5);


        PaperParser paperParser = new PaperParser();
        paperParser.parse();


        ReadingListRepository readingListRepository = new ReadingListRepository();
        readingListRepository.createReadingList("user1", "Reading List 1");
        readingListRepository.addPaperToReadingList("user1", "Reading List 1", "A Formally Verified Compiler Back-End");
        readingListRepository.addPaperToReadingList("user1", "Reading List 1", "Introducing Test Automation and Test-Driven Development");

        readingListRepository.createReadingList("user2", "Reading List 1");
        readingListRepository.addPaperToReadingList("user2", "Reading List 1", "A Formally Verified Compiler Back-End");
        readingListRepository.addPaperToReadingList("user2", "Reading List 1", "Introducing Test Automation and Test-Driven Development");

        readingListRepository.createReadingList("user3", "Reading List 1");
        readingListRepository.addPaperToReadingList("user3", "Reading List 1", "An Experimental Evaluation of Test Driven Development vs. Test-Last Development with Industry Professionals");
        readingListRepository.addPaperToReadingList("user3", "Reading List 1", "Generating User Acceptance Test Plans from Test Cases");
        readingListRepository.addPaperToReadingList("user3", "Reading List 1", "Test-Driven Development: Concepts");
        readingListRepository.addPaperToReadingList("user3", "Reading List 1", "On the Effectiveness of the Test-First Approach to Programming");

        readingListRepository.createReadingList("user4", "Reading List 1");
        readingListRepository.addPaperToReadingList("user4", "Reading List 1", "An Experimental Evaluation of Test Driven Development vs. Test-Last Development with Industry Professionals");
        readingListRepository.addPaperToReadingList("user4", "Reading List 1", "Generating User Acceptance Test Plans from Test Cases");
        readingListRepository.addPaperToReadingList("user4", "Reading List 1", "Test-Driven Development: Concepts");
        readingListRepository.addPaperToReadingList("user4", "Reading List 1", "On the Effectiveness of the Test-First Approach to Programming");
        readingListRepository.addPaperToReadingList("user4", "Reading List 1", "A Formally Verified Compiler Back-End");

        readingListRepository.createReadingList("user5", "Reading List 1");
        readingListRepository.addPaperToReadingList("user5", "Reading List 1", "An Experimental Evaluation of Test Driven Development vs. Test-Last Development with Industry Professionals");
        readingListRepository.addPaperToReadingList("user5", "Reading List 1", "Generating User Acceptance Test Plans from Test Cases");
        readingListRepository.addPaperToReadingList("user5", "Reading List 1", "An Initial Investigation of Test Driven Development in Industry");
        readingListRepository.addPaperToReadingList("user5", "Reading List 1", "On the Effectiveness of the Test-First Approach to Programming");
        readingListRepository.addPaperToReadingList("user5", "Reading List 1", "A Formally Verified Compiler Back-End");

        readingListRepository.createReadingList("user5", "Reading List 2");
        readingListRepository.addPaperToReadingList("user5", "Reading List 2", "An Experimental Evaluation of Test Driven Development vs. Test-Last Development with Industry Professionals");
        readingListRepository.addPaperToReadingList("user5", "Reading List 2", "Generating User Acceptance Test Plans from Test Cases");
        readingListRepository.addPaperToReadingList("user5", "Reading List 2", "An Initial Investigation of Test Driven Development in Industry");
        readingListRepository.addPaperToReadingList("user5", "Reading List 2", "On the Effectiveness of the Test-First Approach to Programming");
        readingListRepository.addPaperToReadingList("user5", "Reading List 2", "A Formally Verified Compiler Back-End");


        userRepository.addFollowing("user1", "user2");
        userRepository.addFollowing("user1", "user3");
        userRepository.addFollowing("user2", "user4");
        userRepository.addFollowing("user2", "user5");
        userRepository.addFollowing("user3", "user1");
        userRepository.addFollowing("user4", "user5");


        UserModel model = new UserModel();
        LoginView view = new LoginView(model); // view
        LoginController controller = new LoginController(view, model); // controller
    }

}
