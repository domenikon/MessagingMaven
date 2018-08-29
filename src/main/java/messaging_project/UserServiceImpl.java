package messaging_project;

import com.company.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class UserServiceImpl {
    Scanner scanner = new Scanner(System.in);
    com.company.User user = new User();

    public void printOptions() {
        System.out.println("Choose option: ");
        System.out.println("1 - Sign up! ");
        System.out.println("2 - Login. ");
    }

    public void addUser() throws IOException {
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        Path filePath = Paths.get(email + ".txt");
        if (filePath.getFileName().equals(email)) {
            System.out.println("User already exists! Please login.");
            login();
        } else {
            System.out.println("Enter password: ");
            String password = scanner.nextLine();
            System.out.println("Enter full name: ");
            String name = scanner.nextLine();
            System.out.println("Enter age: ");
            int age = scanner.nextInt();
            PrintWriter writer = new PrintWriter(email + ".txt");
            writer.println(email);
            writer.println(password);
            writer.println(name);
            writer.println(age);
            writer.close();
        }
    }

    public void login() throws IOException {
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        Path filePath = Paths.get(email + ".txt");
        List<String> records = Files.readAllLines(filePath);
        if (records.get(0).equals(email) && records.get(1).equals(password)) {
            System.out.println("Welcome " + records.get(2));
        } else {
            System.out.println("Email or password is incorrect!");
        }
    }
}
