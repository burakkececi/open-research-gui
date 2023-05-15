package dataAccess;

import entities.User;

import javax.management.InstanceAlreadyExistsException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class UserRepository implements IUserRepository {

    @Override
    public void saveUsers(List<User> users) {

        // File path to save user data
        String filePath = "users.txt";

        if(users.size() == 0) return;

        try {
            FileWriter fileWriter = new FileWriter(filePath, true); // true to append data to the file
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            if (!Files.exists(Paths.get(filePath))) {
                // File does not exist, create a new file
                fileWriter = new FileWriter(filePath);
                bufferedWriter = new BufferedWriter(fileWriter);

                System.out.println("New file created.");
            }

            for (User user : users) {
                // check user already exists
                if (exists(user)) {
                    throw new InstanceAlreadyExistsException();
                }
                if(user == null)
                    throw new NullPointerException();
                // Write user data to the file
                bufferedWriter.write(user.getUsername() + "," + user.getPassword());
                bufferedWriter.newLine();
            }

            // Close the resources
            bufferedWriter.close();
            fileWriter.close();

            if (Files.exists(Paths.get(filePath))) {
                System.out.println("User data saved successfully.");
            } else {
                System.out.println("Unable to save user data.");
            }
        } catch (InstanceAlreadyExistsException e) {
            System.out.println("User already exist!");
        } catch (IOException e) {
            System.out.println("An error occurred while working with the file: " + e.getMessage());
        }
    }

    @Override
    public boolean exists(User user) {
        // File path to read user data from
        String filePath = "users.txt";
        Scanner scanner = null;

        if (user == null)
            throw new NullPointerException();

        try {
            scanner = new Scanner(new File(filePath));
            String line;
            while (scanner.hasNextLine()) {
                // Process each line of user data
                line = scanner.nextLine();

                String[] userData = line.split(",");
                String username = userData[0];
                String password = userData[1];

                if (username.equals(user.getUsername()) &&
                        password.equals(user.getPassword())) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());

        }
        return false;

    }
}
