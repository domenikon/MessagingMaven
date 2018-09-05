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

    // TODO: 05.09.2018 change file path of conv file
    boolean conversationFileExist(String filename) {
        Path filePath = Paths.get(filename);
        return fileExist(filePath);
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


}
