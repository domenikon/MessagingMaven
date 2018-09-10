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

//    private void login() throws IOException {
//        ioUtils.writeMessage("Enter email: ");
//        String email = ioUtils.readNextLine();
//        Path filePath = Paths.get(email + ".txt");
//
//        if (filePath.getFileName().toString().equals(email + ".txt")) {
//            {
//                try {
//                    List<String> records = Files.readAllLines(filePath);
//                    ioUtils.writeMessage("Enter password: ");
//                    String password = ioUtils.readNextLine();
//
//                    if (!records.get(1).equals(password)) {
//                        ioUtils.writeMessage("Email or password is incorrect!");
//                        login();
//                    } else {
//                        if (records.get(0).equals(email) && records.get(1).equals(password)) {
//                            ;
//                            ioUtils.writeMessage("Welcome back:) " + records.get(2));
//                            ioUtils.writeMessage("Choose option: ");
//                            ioUtils.writeMessage("1 - Write message to user: ");
//                            ioUtils.writeMessage("2 - Read message from user: ");
//                            ioUtils.writeMessage("3 - Sign out: ");
//                        }
//                    }
//                } catch (IOException e) {
//                    ioUtils.writeMessage("No such user! Try again.");
//                    login();
//                }
//                ;
//            }
//        }
//
//    }

    // TODO: 30.08.2018 to implement
    void loginSecond_with_parse() throws IOException {
        ioUtils.writeMessage("Enter email: ");
        String email = ioUtils.readNextLine();
        ioUtils.writeMessage("Enter password: ");
        String password = ioUtils.readNextLine();

        String content = new String(Files.readAllBytes(Paths.get(email + ".txt")));

        if (content.contains(email) && content.contains(password)) {
            ioUtils.writeMessage("You are logged in!");
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
                        System.out.println("Conversation does not exst! Creating new one.");
                        ioUtils.createNewConversationFile(email, receiver);
                    }
                    break;
                case 2:
                    System.out.println("Enter user email: ");
                    String receiver1 = ioUtils.readNextLine();
                    String chatFile = email + "_" + receiver1 + ".txt";

                    // ioUtils.readTextFileByLines(chatFile);
                    //if(ioUtils){

                    ThreadChat threadChat = new ThreadChat(){

                    };

                    Thread thread = new Thread(() -> {
                        try {
                            Thread.sleep(1000);
                            ioUtils.readTextFileByLines(chatFile);

                        } catch (InterruptedException | IOException e) {
                            e.printStackTrace();
                        }
                    });
                    thread.start();

                    if (ioUtils.conversationFileExist(chatFile)) {
                        ioUtils.readChat(chatFile);
                    } else {
                        System.out.println("No such conversation!");
                    }
                    break;
                case 3:
                    break;

            }

        } else {
            ioUtils.writeMessage("Email or password is incorrect!");
        }
    }

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
