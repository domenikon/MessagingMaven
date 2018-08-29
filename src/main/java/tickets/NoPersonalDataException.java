package tickets;

import java.time.LocalDateTime;

public class NoPersonalDataException extends Exception {

    private LocalDateTime timestamp;

    public NoPersonalDataException(String message, LocalDateTime localDateTime) {
        super(message);
        this.timestamp = localDateTime;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
