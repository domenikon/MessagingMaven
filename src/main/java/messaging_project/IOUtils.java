package messaging_project;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IOUtils {

    Scanner scanner;

    IOUtils(Scanner scanner) {
        this.scanner = new Scanner(System.in);
    }

    public boolean fileExist(Path fileName) {
        return Files.exists(fileName);
    }

    boolean fileExist(String filename) {
        Path filePath = Paths.get(filename + ".txt");
        return fileExist(filePath);
    }

    boolean conversationFileExist(String filename) {
        Path filePath = Paths.get(filename);
        return fileExist(filePath);
    }

    // TODO: 04.09.2018
    public void findConversation(String email) throws IOException {
        String content = String.valueOf(Files.readAllLines(Paths.get(email)));

        if (content.contains(email)) {
            return;
        }
    }

    void writeMessage(String message) {
        System.out.println(message);
    }

    String readNextLine() {
        return scanner.nextLine();
    }

    int readNextInt() {
        int i = scanner.nextInt();
        scanner.nextLine();
        return i;
    }

    void readChat(String fileName) throws IOException {
        if (!conversationFileExist(fileName)) {
            System.out.println("No conversation with that user!");
        } else {
            String content = new String(Files.readAllBytes(Paths.get(fileName)));
            System.out.println(content);
        }
    }

    void writeMessageToUser(String message, String fileName) throws IOException {
        FileWriter fileWrite = new FileWriter(fileName, true);
        fileWrite.write("\n" + message);
        fileWrite.close();
    }

    void createNewConversationFile(String sender, String receiver) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(sender + "_" + receiver + ".txt");
        writer.close();
    }

}
