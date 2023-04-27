package com.rfigueroa.figscrm.service;

import com.rfigueroa.figscrm.dao.ContactMeFormRepository;
import com.rfigueroa.figscrm.dto.ContactMeFormDTO;
import com.rfigueroa.figscrm.entity.ContactMeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactMeService {

    ContactMeFormRepository contactMeFormRepository;

    @Autowired
    public ContactMeService(ContactMeFormRepository contactMeFormRepository) {
        this.contactMeFormRepository = contactMeFormRepository;
    }


    public void submitContactMeForm(ContactMeFormDTO contactMeFormDTO) {

        // create a new entity
        ContactMeForm persistedContactInfo = new ContactMeForm();

        persistedContactInfo.setFullName(contactMeFormDTO.getFullName());
        persistedContactInfo.setEmail(contactMeFormDTO.getEmail());
        persistedContactInfo.setPhone(contactMeFormDTO.getPhone());
        persistedContactInfo.setContactNote(contactMeFormDTO.getContactNote());

        contactMeFormRepository.save(persistedContactInfo);
    }
}
