package com.example.contactapi.repository;

import com.example.contactapi.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    
    // Atualizado para retornar dados Paginados
    Page<Contact> findByNameContainingIgnoreCase(String name, Pageable pageable);
    
}
