package gg.falmer.service;

import gg.falmer.data.repository.ClientDataRepository;
import gg.falmer.data.model.ClientData;
import gg.falmer.data.model.ContactType;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClientDataService implements IClientDataService {
    @Autowired
    private ClientDataRepository repository;

    public long addNewClient(ClientData clientData) {
        return repository.save(clientData).getId();
    }

    public ClientData getClientData(long id) {
        Optional<ClientData> optionalClientData = repository.findById(id);
        if (optionalClientData.isEmpty())
            throw new IllegalArgumentException(String.format("Client with id %s not exists", id));

        return optionalClientData.get();
    }

    public List<ClientData> getAllClients() {
        return Lists.newArrayList(repository.findAll());
    }

    public void addClientContact(long id, ContactType type, String... contact) {
        ClientData clientData = getClientData(id);
        clientData.addContact(type, contact);

        repository.save(clientData);
    }

    public Set<String> getClientContacts(long id) {
        ClientData clientData = getClientData(id);
        return clientData.getContacts();
    }

    public Set<String> getClientContactsByType(long id, ContactType type) {
        ClientData clientData = getClientData(id);
        return clientData.getContactsByType(type);
    }
}
