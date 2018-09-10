package messaging_project;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class IOUtils {

    Scanner scanner;

    IOUtils(Scanner scanner) {
        this.scanner = new Scanner(System.in);
    }

    private boolean fileExist(Path fileName) {
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
        if (!content.contains(email)) {
            System.out.println("No such conversation!");
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

    public List<String> readTextFileByLines(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        int size = lines.size();

        System.out.println(size);
        String line = lines.get(lines.size() - 1);
        System.out.println(line);
        return lines;
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

    public void checkMessages(String email) throws IOException {

        List<String> userLines = Files.readAllLines(Paths.get(email + ".txt"));
        int messagesCount = 0;
        for (String line : userLines) {
            String[] split = line.split(",");
            if (split[0].equals(email)) {
                messagesCount = userLines.size() - Integer.parseInt(split[1]);
                break;
            }
        }
    }


}
