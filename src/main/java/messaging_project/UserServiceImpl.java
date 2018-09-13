package messaging_project;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class UserServiceImpl {

    IOUtils ioUtils;

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
            //login();
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
//            writer.println(email);
//            writer.println(password);
//            writer.println(name);
//            writer.println(age);
            writer.close();
        }
    }


    // TODO: 30.08.2018 to implement
    void loginSecond_with_parse() throws IOException {
        ioUtils.writeMessage("Enter email: ");
        String email = ioUtils.readNextLine();
        ioUtils.writeMessage("Enter password: ");
        String password = ioUtils.readNextLine();

        String content = new String(Files.readAllBytes(Paths.get(email + ".txt")));

        while (true) {
            if (content.contains(email) && content.contains(password)) {
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
                            System.out.println("Conversation does not exst!");
                        }
                        break;
                    case 2:
                        System.out.println("Enter user email: ");
                        String receiver1 = ioUtils.readNextLine();
                        String chatFile = email + "_" + receiver1 + ".txt";

                        Thread thread = new Thread(() -> {
                            // while (true) {
                            try {
                                Thread.sleep(1000);
                                ioUtils.readTextFileByLines(chatFile);
                            } catch (IOException | InterruptedException e) {
                                e.printStackTrace();
                            }
                            // }
                        });
                        thread.start();

                        break;
                    case 3:
                        return;
                }

            } else {
                ioUtils.writeMessage("Email or password is incorrect!");
            }
        }
    }

//    public void checkForNewMessages(User user, String sender, String receiver) throws IOException {
//        Files.walk(Paths.get(ioUtils.findConversationBetweenTwoUsers(sender, receiver)))
//                .filter(Files::isRegularFile)
//                .forEach(path -> {
//                    try {
//                        ioUtils.checkChatFileForNewMessages(path, user);
//                    } catch (IOException e) {
//                        ioUtils.writeMessage(e.getMessage());
//                    }
//                });
//
//    }

    class ThreadChat extends Thread {

        String fileName;
        String message;
        FileWriter fileWriter;

        @Override
        public void run() {
            try {
                // Path file = Paths.get(fileName);
                fileWriter = new FileWriter(fileName, true);
                BufferedWriter bufferFileWriter = new BufferedWriter(fileWriter);
                bufferFileWriter.append(message);
                bufferFileWriter.newLine();
                bufferFileWriter.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }


    }
//        }

//    }
}
