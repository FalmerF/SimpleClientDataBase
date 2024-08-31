package gg.falmer;

import gg.falmer.data.model.ClientData;
import gg.falmer.data.model.ContactType;
import gg.falmer.service.IClientDataService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource()
public class SimpleClientDataBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleClientDataBaseApplication.class, args);
    }

    public SimpleClientDataBaseApplication(IClientDataService clientDataService) {
        // Пример работы с сервисом клиентов
        ClientData clientData = new ClientData();
        clientData.setName("Ivan");
        clientData.addContact(ContactType.PHONE_NUMBER, "+5 678 901 23 45");

        long clientId = clientDataService.addNewClient(clientData);

        clientDataService.addClientContact(clientId, ContactType.EMAIL, "email@example.com");
    }
}