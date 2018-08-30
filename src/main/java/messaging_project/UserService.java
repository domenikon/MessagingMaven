package messaging_project;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class UserService {

    User user = new User();

    File newFile = new File("db.txt");

    FileWriter fileWrite = new FileWriter(newFile, true);
    BufferedWriter bufferedWrite = new BufferedWriter(fileWrite);
    PrintWriter write = new PrintWriter(bufferedWrite);

    public UserService() throws IOException {


    }

    public void addUser(String email, String name, int age, String password) throws IOException {
        Path filePath = Paths.get(user.getEmail() + ".txt");
        String userEmail = user.getEmail() + ".txt";
        if (userEmail == filePath.toString()) {
            System.out.println("User exist!");
        } else {
            if (!userEmail.equals(filePath)) {
                File newFile = new File(userEmail);
                bufferedWrite.append("Email: " + user.getEmail()
                        + " Name: " + user.getName()
                        + " Age: " + user.getAge()
                        + " Password: ");
            }
            write.close();
        }
    }

    public void findUser() {
    }
}
