package messaging_project;

import com.company.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class UserServiceImpl {
    private Scanner scanner = new Scanner(System.in);
    com.company.User user = new User();

    void printOptions() {
        System.out.println("Choose option: ");
        System.out.println("1 - Sign up! ");
        System.out.println("2 - Login. ");
    }

    void addUser() throws IOException {
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
            String age1 = Integer.toString(age);
           // writer.write(email + " " + password + " "+ name + " " + age);
            writer.println(email);
            writer.println(password);
            writer.println(name);
            writer.println(age);
            writer.close();
        }
    }

    void login() throws IOException {
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        Path filePath = Paths.get(email + ".txt");

        if(!filePath.getFileName().toString().equals(email + ".txt" ) ){
            System.out.println("No such user! Try again.");
            login();
        }else {
            System.out.println("Enter password: ");
            String password = scanner.nextLine();
          //  Path filePath1 = Paths.get(email + ".txt");

           List<String> records = Files.readAllLines(filePath);

            if (records.get(0).equals(email) && records.get(1).equals(password)) {
                System.out.println("Welcome " + records.get(2));
            } else {
                System.out.println("Email or password is incorrect!");
            }
        }
    }
}
