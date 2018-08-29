package tickets;

import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket1 {

    private Person person;
    private int price;
    private LocalDateTime timestamp;

    public Ticket1(Person person, int price, LocalDateTime timestamp) {
        this.person = person;
        this.price = price;
        this.timestamp = timestamp;
    }

    public Person getPerson() {
        return person;
    }

    public int getPrice() {
        return price;
    }

    public LocalDateTime getTimestamp(){
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket1)) return false;
        Ticket1 ticket1 = (Ticket1) o;
        return price == ticket1.price &&
                Objects.equals(person, ticket1.person) &&
                Objects.equals(timestamp, ticket1.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, price, timestamp);
    }

    @Override
    public String toString() {
        return "Ticket1{" +
                "person=" + person +
                ", price=" + price +
                ", timestamp=" + timestamp +
                '}';
    }


}
