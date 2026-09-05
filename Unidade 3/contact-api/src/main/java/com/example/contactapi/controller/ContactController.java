package com.example.contactapi.controller;

import com.example.contactapi.dto.ContactRequestDTO;
import com.example.contactapi.dto.ContactResponseDTO;
import com.example.contactapi.model.Contact;
import com.example.contactapi.repository.ContactRepository;
import com.example.contactapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;

@RestController
@RequestMapping("/api/contacts")
@Tag(name = "Contatos", description = "Gerenciamento de Contatos na Agenda")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @Operation(summary = "Lista todos os contatos com paginação", description = "Retorna uma página de contatos. Use ?page=0&size=10 para paginar.")
    @GetMapping
    public Page<ContactResponseDTO> getAllContacts(Pageable pageable) {
        return contactRepository.findAll(pageable).map(ContactResponseDTO::new);
    }

    @Operation(summary = "Busca contatos por nome usando query customizada e paginação")
    @GetMapping("/search")
    public Page<ContactResponseDTO> searchContactsByName(@RequestParam String name, Pageable pageable) {
        return contactRepository.findByNameContainingIgnoreCase(name, pageable).map(ContactResponseDTO::new);
    }

    @Operation(summary = "Cria um novo contato trafegando DTO")
    @PostMapping
    public ContactResponseDTO createContact(@Valid @RequestBody ContactRequestDTO dto) {
        Contact contact = new Contact();
        contact.setName(dto.getName());
        contact.setEmail(dto.getEmail());
        contact.setPhone(dto.getPhone());
        return new ContactResponseDTO(contactRepository.save(contact));
    }

    @Operation(summary = "Atualiza parcialmente um contato pelo ID usando PATCH")
    @PatchMapping("/{id}")
    public ResponseEntity<ContactResponseDTO> partialUpdateContact(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Contact contact = contactRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado com id " + id));
        
        if (updates.containsKey("name")) contact.setName((String) updates.get("name"));
        if (updates.containsKey("email")) contact.setEmail((String) updates.get("email"));
        if (updates.containsKey("phone")) contact.setPhone((String) updates.get("phone"));
        
        Contact updatedContact = contactRepository.save(contact);
        return ResponseEntity.ok(new ContactResponseDTO(updatedContact));
    }
}
