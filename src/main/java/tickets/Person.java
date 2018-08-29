package tickets;

import java.util.Objects;

public class Person {

    private int age;
    private PersonStatus status;

    public Person(int age) {
        this(age, PersonStatus.COMMON);
    }

    public Person(int age, PersonStatus status) {
        this.age = age;
        this.status = status;
    }

    public int getAge() {
        return age;
    }

    public PersonStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return age == person.age &&
                status == person.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, status);
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", status=" + status +
                '}';
    }
}
