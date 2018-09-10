package messaging_project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConversationImpl {

    public void findConversation(String email) throws IOException {
        String content = String.valueOf(Files.readAllLines(Paths.get(email)));

        if (content.contains(email)) {
            return;
        }
    }

    void createNewConversationFile(String sender, String receiver) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(sender + "_" + receiver + ".txt");
        writer.close();
    }
}
