package tickets;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AdultTicketMashne extends TicketMachine {

    private Clock clock;
    private DiscountCalculator discountCalculator;
    private int price;

    private List<Ticket1> history;

    public AdultTicketMashne(DiscountCalculator discountCalculator, int price, Clock clock) {
        this.discountCalculator = discountCalculator;
        this.price = price;
        this.clock = clock;
    }

    public Ticket1 buy(Person person) throws NoPersonalDataException {
        if (person == null) {
            throw new NoPersonalDataException("Sorry, no person data!", LocalDateTime.now(clock));
        }
        if (discountCalculator == null) {
            return new Ticket1(person, price, LocalDateTime.now(clock));
        }
        if (person.getAge() < 18) {
            throw new ForbidenAgeException(person.getAge(), LocalDateTime.now(clock));
        }

        int discountPercentage = discountCalculator.calculate(person);
        double discount = price * (discountPercentage / 100d);
        double discountedPrice = price - discount;

        Ticket1 ticket1 = new Ticket1(person, price, LocalDateTime.now(clock));
        getHistory().add(ticket1);
        return ticket1;

    }

    public List<Ticket1> getHistory() {
        if (history == null) {
            history = new ArrayList<>();
        }
        return history;
        //return new Ticket1(person, (int) Math.floor(discountedPrice), LocalDateTime.now(clock));
    }

}
