package tickets;

import java.time.LocalDateTime;

public class ForbidenAgeException extends RuntimeException {

    private LocalDateTime timestamp;

    public ForbidenAgeException(int age, LocalDateTime localDateTime) {
        super("Person is not valid age: " + age);
        this.timestamp = localDateTime;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
