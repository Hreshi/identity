package com.hreshi.identity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ContactController {

    @Autowired ContactService contactService;
    
    @PostMapping("/identify")
    public ResponseEntity<Contact> identifyUser(@RequestBody UserData userData) {
        Contact contact = contactService.saveContact(userData);
        return ResponseEntity.ok(contact);
    }

    @GetMapping("/")
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok(contactService.findAll());
    }
}
