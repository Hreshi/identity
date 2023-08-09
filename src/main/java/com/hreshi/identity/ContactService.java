package com.hreshi.identity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    public Contact saveContact(UserData data) {
        Contact contact = new Contact(data);
        contactRepository.save(contact);
        return contact;
    }

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    public Result handle(UserData data) {
        Contact primaryContactByEmail = findPrimaryContactByEmail(data.getEmail());
        Contact primaryContactByPhoneNumber = findPrimaryContactByPhoneNumber(data.getPhoneNumber());

        List<Contact> contactList = new ArrayList<>();

        if (primaryContactByEmail != null) {
            contactList.add(primaryContactByEmail);
            contactList.addAll(contactRepository.findAllByLinkedId(primaryContactByEmail.getId()));
        }

        // If both are different
        // primaryContactByEmail != primaryContactByPhoneNumber
        if (primaryContactByPhoneNumber != null && !primaryContactByPhoneNumber.equals(primaryContactByEmail)) {
            contactList.add(primaryContactByPhoneNumber);
            contactList.addAll(contactRepository.findAllByLinkedId(primaryContactByPhoneNumber.getId()));
        }

        Contact latest = new Contact(data);
        boolean alreadyExists = false;

        for (Contact contact : contactList) {
            if (contact.equals(latest)) {
                alreadyExists = true;
                break;
            }
        }

        if (!alreadyExists) {
            contactList.add(latest);
        }

        Contact oldestContact = findOldestContact(contactList);
        LocalDateTime now = LocalDateTime.now();
        
        if(!"primary".equals(oldestContact.getLinkPrecedence())) {
            oldestContact.setLinkPrecedence("primary");
            oldestContact.setUpdatedAt(now);
        }
        if(oldestContact.getLinkedId() != null) {
            oldestContact.setLinkedId(null);
            oldestContact.setUpdatedAt(now);
        }
        for(Contact contact : contactList) {
            if(contact == oldestContact) continue;

            if(!"secondary".equals(contact.getLinkPrecedence())) {
                contact.setLinkPrecedence("secondary");
                contact.setUpdatedAt(now);
            }
            if(contact.getLinkedId() == null || contact.getLinkedId() != oldestContact.getId()) {
                contact.setLinkedId(oldestContact.getId());
                contact.setUpdatedAt(now);
            }
        }

        contactRepository.saveAll(contactList);
        return prepareResult(contactList, oldestContact);
    }

    private Result prepareResult(List<Contact> contactList, Contact oldestContact) {
        ContactResult result = new ContactResult();
        Set<String> emailSet = new HashSet<>();
        Set<String> phoneNumberSet = new HashSet<>();
        result.setPrimaryContactId(oldestContact.getId());
        if(oldestContact.getEmail() != null) {
            result.getEmails().add(oldestContact.getEmail());
            emailSet.add(oldestContact.getEmail());
        }
        if(oldestContact.getPhoneNumber() != null) {
            result.getPhoneNumbers().add(oldestContact.getPhoneNumber());
            phoneNumberSet.add(oldestContact.getPhoneNumber());
        }

        for(Contact contact : contactList) {
            if(contact.getEmail() != null && !emailSet.contains(contact.getEmail())) {
                emailSet.add(contact.getEmail());
                result.getEmails().add(contact.getEmail());
            }
            if(contact.getPhoneNumber() != null && !phoneNumberSet.contains(contact.getPhoneNumber())) {
                phoneNumberSet.add(contact.getPhoneNumber());
                result.getPhoneNumbers().add(contact.getPhoneNumber());
            }
            if(contact != oldestContact) {
                result.getSecondaryContactIds().add(contact.getId());
            }
        }
        return new Result(result);
    }

    private Contact findOldestContact(List<Contact> contactList) {
        Contact oldestContact = null;
        for (Contact contact : contactList) {
            if (contact.compareTo(oldestContact) < 0) {
                oldestContact = contact;
            }
        }
        return oldestContact;
    }

    private Contact findPrimaryContactByEmail(String email) {
        if (email == null || email.equals(""))
            return null;

        Contact contact = contactRepository.findFirstByEmail(email);
        if (contact == null) {
            return null;
        }

        if (contact.isPrimary()) {
            return contact;
        }

        int primaryId = contact.getLinkedId();
        contact = contactRepository.findById(primaryId).get();
        return contact;
    }

    private Contact findPrimaryContactByPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.equals(""))
            return null;

        Contact contact = contactRepository.findFirstByPhoneNumber(phoneNumber);
        if (contact == null) {
            return null;
        }

        if (contact.isPrimary()) {
            return contact;
        }

        int primaryId = contact.getLinkedId();
        contact = contactRepository.findById(primaryId).get();
        return contact;
    }
}
