package gg.falmer.data.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
public class ContactData {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private ContactType type;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @EqualsAndHashCode.Exclude
    private ClientData clientData;

    private String contact;

    public static ContactData of(ClientData clientData,ContactType type, String contact) {
        ContactData contactData = new ContactData();
        contactData.setClientData(clientData);
        contactData.setType(type);
        contactData.setContact(contact);
        return contactData;
    }
}
