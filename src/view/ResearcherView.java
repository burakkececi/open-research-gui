package view;

import controller.ResearcherDetailsController;
import model.ReadingListModel;
import model.UserModel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class ResearcherView extends JFrame implements Observer {
    private ReadingListModel readingListModel;
    private UserModel model;
    private JButton researcherButton, backButton;

    public ResearcherView(ReadingListModel readingListModel, UserModel model) {
        this.readingListModel = readingListModel;
        this.model = model;

        this.setTitle("OpenResearcher");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // go back button
        backButton = new JButton("<-");
        backButton.setBounds(10, 10, 50, 40);
        this.add(backButton);

        int i = 0;
        for (String user: model.getAllUserNames()) {
            if (user.equals(model.getAuthenticatedUserName())) {
                continue;
            }
            researcherButton = new JButton(user);
            // each button have different y position
            researcherButton.setBounds(300, 50 + i * 50, 200, 40);
            researcherButton.addActionListener(e -> {
                setVisible(false);
                System.out.println(user);
                ResearcherDetailsView researcherView = new ResearcherDetailsView(readingListModel, model, user);
                ResearcherDetailsController researcherController = new ResearcherDetailsController(readingListModel,researcherView,model);
                researcherView.setVisible(true);
            }   );
            this.add(researcherButton);
            i++;
        }

    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    };

    @Override
    public void update(Observable o, Object arg) {

    }
}
