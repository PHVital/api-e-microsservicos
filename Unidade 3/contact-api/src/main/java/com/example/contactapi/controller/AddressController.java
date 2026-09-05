package com.example.contactapi.controller;

import com.example.contactapi.dto.AddressRequestDTO;
import com.example.contactapi.dto.AddressResponseDTO;
import com.example.contactapi.model.Address;
import com.example.contactapi.model.Contact;
import com.example.contactapi.repository.AddressRepository;
import com.example.contactapi.repository.ContactRepository;
import com.example.contactapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/addresses")
@Tag(name = "Endereços", description = "Gerenciamento de Endereços relacionados aos Contatos")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ContactRepository contactRepository;

    @Operation(summary = "Lista todos os endereços com paginação")
    @GetMapping
    public Page<AddressResponseDTO> getAll(Pageable pageable) {
        return addressRepository.findAll(pageable).map(AddressResponseDTO::new);
    }

    @Operation(summary = "Adiciona um novo endereço vinculando-o a um Contato")
    @PostMapping
    public AddressResponseDTO create(@Valid @RequestBody AddressRequestDTO dto) {
        Contact contact = contactRepository.findById(dto.getContactId())
            .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado com id " + dto.getContactId()));
        
        Address addr = new Address();
        addr.setStreet(dto.getStreet());
        addr.setCity(dto.getCity());
        addr.setContact(contact);
        
        return new AddressResponseDTO(addressRepository.save(addr));
    }
}
