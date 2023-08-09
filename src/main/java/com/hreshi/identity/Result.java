package com.hreshi.identity;

public class Result {
    ContactResult contact;

    public Result(ContactResult contact) {
        this.contact = contact;
    }

    public ContactResult getContact() {
        return contact;
    }

    public void setContact(ContactResult contact) {
        this.contact = contact;
    }
}
