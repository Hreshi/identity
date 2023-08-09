package com.hreshi.identity;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer>{
    List<Contact> findAll();

    Contact findFirstByEmail(String email);

    Contact findFirstByPhoneNumber(String phoneNumber);

    List<Contact> findAllByLinkedId(int id);

}
