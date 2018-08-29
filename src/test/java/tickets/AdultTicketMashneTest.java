package tickets;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Java6Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.catchThrowable;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
public class AdultTicketMashneTest {

    // @Mock
    private DiscountCalculator discountCalculator;

    private Clock clock = Clock.fixed(Instant.parse("2018-08-27T10:00:00Z"), ZoneId.of("Europe/Tallinn"));

    @Before
    public void setUp() throws Exception {
        discountCalculator = mock(DiscountCalculator.class);
    }

    @Test
    public void buy_throws_NoPersonalDataException__if_person_null() {
        //given
        Person person = null;
        AdultTicketMashne adultTicketMashne = new AdultTicketMashne(discountCalculator, 100, clock);
        //when
        Throwable throwable = catchThrowable(() -> adultTicketMashne.buy(person));

        Assertions.assertThat(throwable)
                .hasMessage("Sorry, no person data!")
                .isInstanceOfSatisfying(NoPersonalDataException.class, e -> {
                       Assertions.assertThat(e.getTimestamp()).isEqualTo(LocalDateTime.now(clock));

                });

        try {
            adultTicketMashne.buy(person);
            fail("no exception was thrown");
        } catch (NoPersonalDataException e) {
            //then
            Assertions.assertThat(e).hasMessage("Sorry, no person data!");
            Assertions.assertThat(e.getTimestamp()).isEqualTo(LocalDateTime.now(clock));

//            assertEquals("Sorry, no person data!", e.getMessage());
//            assertEquals(LocalDateTime.now(clock), e.getTimestamp());

            verify(discountCalculator).calculate(any());
        }

    }

    @Test
    public void buy_returns_fill_price_ticket_if_person_have_nodiscount() throws NoPersonalDataException {

        Person child = new Person(50, PersonStatus.DISABLED);
        DiscountCalculator discountCalculator = null;
        AdultTicketMashne adultTicketMashne = new AdultTicketMashne(discountCalculator, 100, clock);

        Ticket1 result = adultTicketMashne.buy(child);

        Assertions.assertThat(result.getPrice());

        assertEquals(100, result.getPrice());
        assertEquals(child, result.getPerson());
        assertNotNull(result.getTimestamp());
    }

    @Test
    public void buy_returns_fill_price_ticket_if_person_have_with_discount_calculator() throws NoPersonalDataException {
        Person person = new Person(50, PersonStatus.DISABLED);
        when(discountCalculator.calculate(person)).thenReturn(90);
        AdultTicketMashne adultTicketMashne = new AdultTicketMashne(discountCalculator, 100, clock);

        Ticket1 result = adultTicketMashne.buy(person);

        assertEquals(10, result.getPrice());
        assertEquals(person, result.getPerson());
        assertNotNull(result.getTimestamp());
        verify(discountCalculator).calculate(person);
    }

    @Test
    public void check_if_user_is_children() throws NoPersonalDataException {
        Person child = new Person(10);
        AdultTicketMashne adultTicketMashne = new AdultTicketMashne(discountCalculator, 100, clock);

        Ticket1 result = adultTicketMashne.buy(child);

        try {
            adultTicketMashne.buy(child);
            fail("no exception was thrown");
        } catch (ForbidenAgeException e) {

            assertEquals("Person is not valid age:", e.getMessage());
            assertEquals(LocalDateTime.now(clock), e.getTimestamp());
        }
    }

    @Test
    public void if_user_is_18() throws NoPersonalDataException {

        Person person = new Person(18);
        AdultTicketMashne adultTicketMashne = new AdultTicketMashne(discountCalculator, 100, clock);

        Ticket1 result = adultTicketMashne.buy(person);

        assertEquals(100, result.getPrice());
        assertEquals(person, result.getPerson());
    }

    @Test
    public void how_many_tickets_were_sold() throws NoPersonalDataException {
        Person person = new Person(18);
        AdultTicketMashne adultTicketMashne = new AdultTicketMashne(discountCalculator, 100, clock);

        List<Ticket1> ticket1List = adultTicketMashne.getHistory();
        adultTicketMashne.buy(person);

        assertEquals(1, ticket1List.size());
        assertEquals(new Ticket1(person, 100, LocalDateTime.now(clock)), ticket1List.get(0));
    }

    @Test
    public void check_that_no_tickets_were_sold() throws NoPersonalDataException {
        Person person = new Person(20);
        AdultTicketMashne adultTicketMashne = new AdultTicketMashne(discountCalculator, 100, clock);

        List<Ticket1> ticket1List = adultTicketMashne.getHistory();

        assertTrue(ticket1List.isEmpty());

    }

    @Test
    public void check_order() throws NoPersonalDataException {
        Person person = new Person(20);
        AdultTicketMashne adultTicketMashne = new AdultTicketMashne(discountCalculator, 100, clock);

        adultTicketMashne.buy(new Person(24));
        adultTicketMashne.buy(new Person(34));
        adultTicketMashne.buy(new Person(56));
        adultTicketMashne.buy(new Person(23));
        adultTicketMashne.buy(new Person(22));

        List<Ticket1> ticket1List = adultTicketMashne.getHistory();

        assertEquals(new Ticket1(new Person(24), 100, LocalDateTime.now(clock)), ticket1List.get(0));
        assertEquals(new Ticket1(new Person(34), 100, LocalDateTime.now(clock)), ticket1List.get(1));
        assertEquals(new Ticket1(new Person(56), 100, LocalDateTime.now(clock)), ticket1List.get(2));
        assertEquals(new Ticket1(new Person(23), 100, LocalDateTime.now(clock)), ticket1List.get(3));
        assertEquals(new Ticket1(new Person(22), 100, LocalDateTime.now(clock)), ticket1List.get(4));

        Assertions.assertThat(ticket1List).containsOnly(
        new Ticket1(new Person(24), 100, LocalDateTime.now(clock)),
        new Ticket1(new Person(34), 100, LocalDateTime.now(clock)),
        new Ticket1(new Person(56), 100, LocalDateTime.now(clock)),
        new Ticket1(new Person(23), 100, LocalDateTime.now(clock)),
        new Ticket1(new Person(22), 100, LocalDateTime.now(clock))
);
    }
}