package gg.falmer;

import gg.falmer.data.model.ClientData;
import gg.falmer.data.model.ContactType;
import gg.falmer.data.repository.ClientDataRepository;
import gg.falmer.service.IClientDataService;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SimpleClientDataBaseApplication.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SimpleClientDataBaseTest {
    private static long clientDataId;
    @Autowired
    private ClientDataRepository repository;
    @Autowired
    private IClientDataService service;

    @Before
    public void setup() {
    }

    @Test
    @Commit
    @Order(1)
    @Transactional
    public void testAddClientData() {
        ClientData clientData = new ClientData();
        clientData.setName("Ivan");
        clientData.addContact(ContactType.PHONE_NUMBER, "+1 234 567 89 01");
        clientData.addContact(ContactType.EMAIL, "email@example.com");

        clientDataId = service.addNewClient(clientData);

        Assertions.assertEquals(1, repository.count());
        Assertions.assertTrue(repository.existsById(clientDataId));
    }

    @Test
    @Commit
    @Order(2)
    @Transactional
    public void testAddPhoneNumber() {
        service.addClientContact(clientDataId, ContactType.PHONE_NUMBER, "+5 678 901 23 45");

        ClientData clientData = service.getClientData(clientDataId);
        Set<String> phoneNumbers = clientData.getContactsByType(ContactType.PHONE_NUMBER);

        Assertions.assertEquals(2, phoneNumbers.size());
        Assertions.assertTrue(phoneNumbers.contains("+5 678 901 23 45"));
    }

    @Test
    @Commit
    @Order(3)
    @Transactional
    public void testAddEmail() {
        service.addClientContact(
                clientDataId, ContactType.EMAIL,
                "first_email@example.com", "second_email@example.com"
        );

        ClientData clientData = service.getClientData(clientDataId);
        Set<String> emails = clientData.getContactsByType(ContactType.EMAIL);

        Assertions.assertEquals(3, emails.size());
        Assertions.assertTrue(emails.contains("first_email@example.com"));
        Assertions.assertTrue(emails.contains("second_email@example.com"));
    }

    @Test
    @Order(4)
    public void testClientsCount() {
        List<ClientData> clients = service.getAllClients();

        Assertions.assertEquals(1, clients.size());
    }

    @Test
    @Order(5)
    @Transactional
    public void testGetClientContacts() {
        Set<String> contacts = service.getClientContacts(clientDataId);

        Assertions.assertEquals(5, contacts.size());
    }

    @Test
    @Order(5)
    @Transactional
    public void testGetClientContactsByType() {
        Set<String> contacts = service.getClientContactsByType(clientDataId, ContactType.EMAIL);

        Assertions.assertEquals(3, contacts.size());
    }
}
