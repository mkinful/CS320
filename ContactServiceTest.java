import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {
    private ContactService contactService;

    @BeforeEach
    public void setUp() {
        contactService = new ContactService();
    }

    @Test
    public void testAddContact() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        assertEquals(contact, contactService.getContact("1234567890"));
    }

    @Test
    public void testAddDuplicateContactID() {
        Contact contact1 = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        Contact contact2 = new Contact("1234567890", "Jane", "Smith", "0987654321", "456 Elm St");
        contactService.addContact(contact1);
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact(contact2);
        });
    }

    @Test
    public void testDeleteContact() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        contactService.deleteContact("1234567890");
        assertNull(contactService.getContact("1234567890"));
    }

    @Test
    public void testDeleteNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.deleteContact("1234567890");
        });
    }

    @Test
    public void testUpdateContact() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        contactService.updateContact("1234567890", "Jane", "Smith", "0987654321", "456 Elm St");
        assertEquals("Jane", contactService.getContact("1234567890").getFirstName());
        assertEquals("Smith", contactService.getContact("1234567890").getLastName());
        assertEquals("0987654321", contactService.getContact("1234567890").getPhone());
        assertEquals("456 Elm St", contactService.getContact("1234567890").getAddress());
    }

    @Test
    public void testUpdateNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateContact("1234567890", "Jane", "Smith", "0987654321", "456 Elm St");
        });
    }
}
