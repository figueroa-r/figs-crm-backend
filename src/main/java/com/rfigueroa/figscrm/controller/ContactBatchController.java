package com.rfigueroa.figscrm.controller;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rfigueroa.figscrm.dao.ContactDetailRepository;
import com.rfigueroa.figscrm.dao.ContactRepository;
import com.rfigueroa.figscrm.entity.Contact;
import com.rfigueroa.figscrm.entity.ContactDetail;

@RestController
@RequestMapping("api/v1/contactsBatch/")
public class ContactBatchController {

    private ContactRepository contactRepository;

    private ContactDetailRepository contactDetailRepository;

    @Autowired
    public ContactBatchController(ContactRepository contactRepository, ContactDetailRepository contactDetailRepository) {
        this.contactRepository = contactRepository;
        this.contactDetailRepository = contactDetailRepository;
    }

    // batch save method...
    @Transactional
    @CrossOrigin(origins = {"http://localhost:3000", "https://master.d2b1tg1ojgscyw.amplifyapp.com"})
    @PatchMapping("{id}")
    public ResponseEntity<EntityModel<Contact>> updateContactEntity(@PathVariable Integer id, @RequestBody Contact updatedContact) {
        System.out.println(updatedContact.toString());
        Optional<Contact> contactOptional = contactRepository.findById(id);

        if(contactOptional.isPresent()) {
            Contact persistedContact = contactOptional.get();
            // make changes to any fields from our http request that are not null
            if(updatedContact.getFirstName() != null) {
                persistedContact.setFirstName(updatedContact.getFirstName());
            }
            if(updatedContact.getLastName() != null) {
                persistedContact.setLastName(updatedContact.getLastName());
            }
            if(updatedContact.getTitle() != null) {
                persistedContact.setTitle(updatedContact.getTitle());
            }
            if(updatedContact.getDepartment() != null) {
                persistedContact.setDepartment(updatedContact.getDepartment());
            }
            if(updatedContact.getIsActive() != null) {
                persistedContact.setIsActive(updatedContact.getIsActive());
            }
            if(updatedContact.getAvatarId() != null) {
                persistedContact.setAvatarId(updatedContact.getAvatarId());
            }


            if(updatedContact.getContactsList() != null) {
                for (ContactDetail detail : updatedContact.getContactsList()) {
                    if (detail.getId() > 0) {
                        // retrieve the existing detail
                        Optional<ContactDetail> existingDetail = contactDetailRepository.findById(detail.getId());

                        // lambda function to update the fields with the http request fields...
                        existingDetail.ifPresent(contactDetail -> {
                            contactDetail.setContactType(detail.getContactType());
                            contactDetail.setContactDetail(detail.getContactDetail());
                            contactDetailRepository.save(contactDetail);
                        });
                    } else { // for new Contact Details...
                        detail.setId(null);
                        // add relationship
                        persistedContact.addContactDetail(detail);
                    }
                }
            }

            contactRepository.save(persistedContact);

            // create model...
            EntityModel<Contact> returnContact = EntityModel.of(persistedContact);



            return ResponseEntity.ok(returnContact);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
