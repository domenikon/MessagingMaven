package messaging_project;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOUtils {

    Scanner scanner;

    public IOUtils(Scanner scanner) {
        this.scanner = new Scanner(System.in);
    }

    public boolean fileExist(Path fileName) {
        return Files.exists(fileName);
    }

    public boolean fileExist(String filename) {
        Path filePath = Paths.get(filename + ".txt");
        return fileExist(filePath);
    }

    public boolean conversationFileExist(String filename) {
        Path filePath = Paths.get(filename);
        return fileExist(filePath);
    }

    public void writeMessage(String message) {
        System.out.println(message);
    }

    public String readNextLine() {
        return scanner.nextLine();
    }

    public int readNextInt() {
        return scanner.nextInt();
    }

    public void readChat(String fileName) throws IOException {
        if (!conversationFileExist(fileName)){
            System.out.println("No conversation with that user!");
        }else {
            //Path filePath = Paths.get(fileName);
            String content = new String(Files.readAllBytes(Paths.get(fileName)));
            System.out.println(content);
        }

    }

    public void writeMessageToUser(String message, String fileName) throws IOException {
        if (!conversationFileExist(fileName)) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.append(message);
            writer.close();
        } else {
            FileWriter fileWrite = new FileWriter(fileName, true);
            fileWrite.write("\n" + message);
            fileWrite.close();
        }
        ;
    }

    public void createNewConversationFile(String sender, String receiver) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(sender + "_" + receiver + ".txt");
        writer.close();
    }

}
