package gg.falmer.data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "clients")
public class ClientData {
    @Id
    @GeneratedValue
    @Column(name = "client_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "contacts")
    @OneToMany(mappedBy = "clientData", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ContactData> contacts = new HashSet<>();

    public void addContact(ContactType type, String... newContacts) {
        for (String contact : newContacts)
            contacts.add(ContactData.of(this, type, contact));
    }

    public Set<String> getContactsByType(ContactType type) {
        return contacts.stream()
                .filter(contactData -> contactData.getType().equals(type))
                .map(ContactData::getContact)
                .collect(Collectors.toSet());
    }

    public Set<String> getContacts() {
        return contacts.stream()
                .map(ContactData::getContact)
                .collect(Collectors.toSet());
    }
}
