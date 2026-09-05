package com.example.contactapi.dto;

import com.example.contactapi.model.Address;

public class AddressResponseDTO {
    private Long id;
    private String street;
    private String city;
    private Long contactId;
    
    public AddressResponseDTO(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.city = address.getCity();
        if (address.getContact() != null) {
            this.contactId = address.getContact().getId();
        }
    }

    public Long getId() { return id; }
    public String getStreet() { return street; }
    public String getCity() { return city; }
    public Long getContactId() { return contactId; }
}
