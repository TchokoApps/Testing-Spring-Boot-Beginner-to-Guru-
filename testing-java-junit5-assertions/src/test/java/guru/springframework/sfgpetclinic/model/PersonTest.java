package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest {

    @Test
    public void groupedAssertions() {
        final Person person = new Person(1l, "Charles", "Tientcheu");
        assertAll("Test Props Set",
                () -> assertEquals(person.getId(), Long.valueOf(1l),"Primary key failed"),
                () -> assertEquals(person.getFirstName(), "Charles", "First name failed"),
                () -> assertEquals(person.getLastName(), "Tientcheu","Last name failed")
        );
    }

}