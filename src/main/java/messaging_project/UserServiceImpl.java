package messaging_project;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class UserServiceImpl {

    IOUtils ioUtils;
    ConversationImpl conversation;

    public UserServiceImpl(IOUtils ioUtils) {
        this.ioUtils = ioUtils;
    }

    void printOptions() {
        ioUtils.writeMessage("Choose option: ");
        ioUtils.writeMessage("1 - Sign up! ");
        ioUtils.writeMessage("2 - Login. ");
        ioUtils.writeMessage("3 - Exit. ");
    }

    void addUser() throws IOException {
        ioUtils.writeMessage("Enter email: ");
        String email = ioUtils.readNextLine();
        if (ioUtils.fileExist(email + ".txt")) {
            ioUtils.writeMessage("User already exists! Please login.");
        } else {
            ioUtils.writeMessage("Enter password: ");
            String password = ioUtils.readNextLine();
            ioUtils.writeMessage("Enter full name: ");
            String name = ioUtils.readNextLine();
            ioUtils.writeMessage("Enter age: ");
            int age = ioUtils.readNextInt();
            //String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
            PrintWriter writer = new PrintWriter(email + ".txt");
            writer.append(email).append(" ")
                    .append(password).append(" ")
                    .append(name).append(" ")
                    .append(String.valueOf(age));
            writer.close();
        }
    }

    void loginSecond_with_parse() throws IOException {
        ioUtils.writeMessage("Enter email: ");
        String email = ioUtils.readNextLine();
        ioUtils.writeMessage("Enter password: ");
        String password = ioUtils.readNextLine();
        Path filePath = Paths.get(email + ".txt");
        if (ioUtils.fileExist(filePath)) {

            String content = new String(Files.readAllBytes(Paths.get(filePath.getFileName().toString())));

            if (content.contains(email) && content.contains(password)) {
                ioUtils.writeMessage("You are logged in!");
//                Thread thread = new Thread(() -> {
//                    try {
//                        Thread.sleep(1000);
//                        ioUtils.readChat(email);
//                    } catch (IOException | InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                });
//                thread.start();
//            }
            ioUtils.writeMessage("Choose option: ");
            ioUtils.writeMessage("1 - Write message to user: ");
            ioUtils.writeMessage("2 - Read message from user: ");
            ioUtils.writeMessage("3 - Sign out: ");

            int option = ioUtils.readNextInt();

            switch (option) {
                case 1:
                    System.out.println("Enter user email: ");
                    String receiver = ioUtils.readNextLine();
                    String fileName = email + "_" + receiver + ".txt";
                    if (ioUtils.conversationFileExist(fileName)) {
                        System.out.println("Your message here: ");
                        ioUtils.writeMessageToUser(ioUtils.readNextLine(), fileName);
                    } else {
                        System.out.println("Conversation does not exists! Creating new one.");
                        conversation.createNewConversationFile(email, receiver);
                    }
                    break;
                case 2:
                    System.out.println("Enter user email: ");
                    String receiver1 = ioUtils.readNextLine();
                    String chatFile = email + "_" + receiver1 + ".txt";
                    if (ioUtils.conversationFileExist(chatFile)) {
                        ioUtils.readChat(chatFile);
                    } //else {// TODO: 04.09.2018
                   // }
                    break;
                case 3:
                    return;
            }

        } else {
            ioUtils.writeMessage("Email or password is incorrect!");
        }
    }

}}
