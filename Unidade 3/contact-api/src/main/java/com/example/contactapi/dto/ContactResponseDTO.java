package com.example.contactapi.dto;

import com.example.contactapi.model.Contact;
import java.util.List;
import java.util.stream.Collectors;

public class ContactResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private List<AddressResponseDTO> addresses;
    
    public ContactResponseDTO(Contact contact) {
        this.id = contact.getId();
        this.name = contact.getName();
        this.email = contact.getEmail();
        this.phone = contact.getPhone();
        if (contact.getAddresses() != null) {
            this.addresses = contact.getAddresses().stream()
                .map(AddressResponseDTO::new)
                .collect(Collectors.toList());
        }
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public List<AddressResponseDTO> getAddresses() { return addresses; }
}
