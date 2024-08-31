package gg.falmer.service;

import gg.falmer.data.model.ClientData;
import gg.falmer.data.model.ContactType;

import java.util.List;
import java.util.Set;

public interface IClientDataService {

    default long addNewClient(String name) {
        ClientData clientData = new ClientData();
        clientData.setName(name);

        return addNewClient(clientData);
    }

    long addNewClient(ClientData clientData);

    ClientData getClientData(long id);
    List<ClientData> getAllClients();

    void addClientContact(long id, ContactType type, String... contact);

    Set<String> getClientContacts(long id);

    Set<String> getClientContactsByType(long id, ContactType type);
}
