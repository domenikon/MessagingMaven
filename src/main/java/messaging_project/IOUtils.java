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

    public IOUtils(byte[] sizeNew) {
        this.sizeNew = sizeNew;
    }

    private byte[] sizeNew;

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
        String line = lines.get(lines.size() - 1);
        System.out.println(line);
        return lines;
    }

    synchronized void readChat(String fileName) throws IOException {
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

    public String findConversationBetweenTwoUsers(String sender, String receiver) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(sender + "_" + receiver + ".txt")));
        byte[] size = content.getBytes();
//        String message = "";
//        if (((content.contains(sender) && content.contains(receiver)) ||
//                ((content.contains(receiver) && (content.contains(sender))))))
//        {
//            writeMessage("found");
//        }
    return content;}

    // TODO: 04.09.2018
    public void findConversation(String email) throws IOException {
        String content = String.valueOf(Files.readAllLines(Paths.get(email)));
        if (!content.contains(email)) {
            System.out.println("Found!");
        }
    }


}
