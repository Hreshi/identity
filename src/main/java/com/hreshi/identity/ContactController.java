package com.hreshi.identity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

    @Value("delete.key")
    String deleteKey;

    @Autowired ContactService contactService;
    
    @PostMapping("/identify")
    public ResponseEntity<Result> identifyUser(@RequestBody UserData userData) {
        Result result = contactService.handle(userData);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok(contactService.findAll());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEverything(@RequestParam("key") String key) {
        if(deleteKey.equals(key)) {
            contactService.deleteEverything();
        }
        return ResponseEntity.ok("Everything deleted");
    }
}
