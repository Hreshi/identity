package com.hreshi.identity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired ContactRepository contactRepository;
    
    public Contact saveContact(UserData data) {
        Contact contact = new Contact(data);
        contactRepository.save(contact);
        return contact;
    }

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    public void handle(UserData data) {
        
    }
}
