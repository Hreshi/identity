package com.hreshi.identity;

import java.util.ArrayList;
import java.util.List;

public class ContactResult {
    int primaryContactId;
    List<String> emails;
    List<String> phoneNumbers;
    List<Integer> secondaryContactIds;
    
    public ContactResult() {
        this.emails = new ArrayList<>();
        this.phoneNumbers = new ArrayList<>();
        this.secondaryContactIds = new ArrayList<>();
    }
    public int getPrimaryContactId() {
        return primaryContactId;
    }
    public void setPrimaryContactId(int primaryContactId) {
        this.primaryContactId = primaryContactId;
    }
    public List<String> getEmails() {
        return emails;
    }
    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }
    public List<Integer> getSecondaryContactIds() {
        return secondaryContactIds;
    }
}
