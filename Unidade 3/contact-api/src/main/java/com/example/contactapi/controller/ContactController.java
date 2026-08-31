package com.example.contactapi.controller;

import com.example.contactapi.model.Contact;
import com.example.contactapi.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @PostMapping
    public Contact createContact(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    // EXERCÍCIO 1 - Busca por nome
    @GetMapping("/search")
    public List<Contact> searchContactsByName(@RequestParam String name) {
        return contactRepository.findByNameContainingIgnoreCase(name);
    }

    // EXERCÍCIO 2 - Método PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<Contact> partialUpdateContact(@PathVariable Long id, @RequestBody Map<String, Object> updates) {

        // --- PONTO DE ATENÇÃO: COMO FOI RESOLVIDO O RETORNO 404 SEM EXCEPTION HANDLER ---
        // Se usássemos 'contactRepository.findById(id).orElseThrow()', receberíamos uma RuntimeException
        // que, por não ser tratada no Spring ainda, devolveria o HTTP 500 (Internal Server Error).
        // A solução aqui adotada foi usar um Optional direto: verificamos se está empty e, caso positivo,
        // geramos um retorno manual 'ResponseEntity.notFound().build()' que preenche e força o Status 404 explícito.
        // Poderia ser feito também um Global Handler para lidar de forma personalizada com os erros.

        Optional<Contact> optionalContact = contactRepository.findById(id);
        
        if (optionalContact.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Contact contact = optionalContact.get();

        if (updates.containsKey("name")) {
            contact.setName((String) updates.get("name"));
        }
        if (updates.containsKey("email")) {
            contact.setEmail((String) updates.get("email"));
        }
        if (updates.containsKey("phone")) {
            contact.setPhone((String) updates.get("phone"));
        }
        
        Contact updatedContact = contactRepository.save(contact);
        return ResponseEntity.ok(updatedContact);
    }
}
