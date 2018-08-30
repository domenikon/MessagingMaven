package messaging_project;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class UserServiceImpl {
    private Scanner scanner = new Scanner(System.in);
    User user = new User();

    void printOptions() {
        System.out.println("Choose option: ");
        System.out.println("1 - Sign up! ");
        System.out.println("2 - Login. ");
        System.out.println("3 - Exit. ");
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
             writer.append(email).append(" ")
             .append(password).append(" ")
             .append(name).append(" ")
             .append(String.valueOf(age));
//            writer.println(email);
//            writer.println(password);
//            writer.println(name);
//            writer.println(age);
            writer.close();
        }
    }

    void login() throws IOException {
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        Path filePath = Paths.get(email + ".txt");

        if (filePath.getFileName().toString().equals(email + ".txt")) {
            {
                try {
                    List<String> records = Files.readAllLines(filePath);
                    System.out.println("Enter password: ");
                    String password = scanner.nextLine();

                    if (!records.get(1).equals(password)) {
                        System.out.println("Email or password is incorrect!");
                        login();
                    } else {
                        if (records.get(0).equals(email) && records.get(1).equals(password)) {
                            ;
                            System.out.println("Welcome back:) " + records.get(2));
                        }
                    }
                } catch (IOException e) {
                    System.out.println("No such user! Try again.");
                    login();
                }
                ;
            }
        }

    }

    // TODO: 30.08.2018 to implement
    void loginSecond_with_parse() throws IOException {
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        Path filePath = Paths.get(email + ".txt");

        if (filePath.getFileName().toString().equals(email + ".txt")) {
            {
                String content = new String(Files.readAllBytes(Paths.get(email + ".txt")));

                List<String> list = new ArrayList<>();
                list.add(content);

                if(list.contains(email) && list.contains(password)){
                    System.out.println("You are logged in!");
                }else {
                    System.out.println("Email or password is incorrect!");
                }


////                for (String string : list) {
////                    if (string.contains(email)) {
////                        System.out.println("Enter password: ");
////                        String password = scanner.nextLine();
////                        if (string.contains(password) ){}
////                    }
//
//                }

            }
        }

    }
}
