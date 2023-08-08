package com.hreshi.identity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String phoneNumber;
    private String email;
    private int linkedId;
    private String linkPrecedence;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;


    public Contact() {
    }
    public Contact(UserData data) {
        this.email = data.getEmail();
        this.phoneNumber = data.getPhoneNumber();
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getLinkedId() {
        return linkedId;
    }
    public void setLinkedId(int linkedId) {
        this.linkedId = linkedId;
    }
    public String getLinkPrecedence() {
        return linkPrecedence;
    }
    public void setLinkPrecedence(String linkPrecedence) {
        this.linkPrecedence = linkPrecedence;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }
    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

}
