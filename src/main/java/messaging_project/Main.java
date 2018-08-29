package messaging_project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        UserServiceImpl userService = new UserServiceImpl();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose option: ");
        System.out.println("Sign up! ");
        System.out.println("Login. ");
        int option = scanner.nextInt();

        switch (option){

            case 1:
                userService.addUser();
                break;
            case 2:
                userService.login();
                break;
        }

    }
}
