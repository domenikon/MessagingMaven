package messaging_project;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchFieldException {

        UserServiceImpl userService = new UserServiceImpl(new IOUtils(null));
        Scanner scanner = new Scanner(System.in);


        while (true) {
            userService.printOptions();
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    userService.addUser();
                    break;
                case 2:
                    //userService.login();
                    userService.loginSecond_with_parse();
                    break;
                case 3:
                    return;
            }
        }
    }
}
