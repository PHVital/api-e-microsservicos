package com.example.contactapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddressRequestDTO {
    
    @NotBlank(message = "A rua é obrigatória")
    private String street;
    
    @NotBlank(message = "A cidade é obrigatória")
    private String city;
    
    @NotNull(message = "O id do contato é obrigatório")
    private Long contactId;

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    public Long getContactId() { return contactId; }
    public void setContactId(Long contactId) { this.contactId = contactId; }
}
