package com.hreshi.identity;

import java.util.ArrayList;
import java.util.List;

public class ContactResult {
    int primaryContatctId;
    List<String> emails;
    List<String> phoneNumbers;
    List<Integer> secondaryContactIds;
    
    public ContactResult() {
        this.emails = new ArrayList<>();
        this.phoneNumbers = new ArrayList<>();
        this.secondaryContactIds = new ArrayList<>();
    }
    public int getPrimaryContatctId() {
        return primaryContatctId;
    }
    public void setPrimaryContatctId(int primaryContactId) {
        this.primaryContatctId = primaryContactId;
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
