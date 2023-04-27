package com.rfigueroa.figscrm.controller;

import com.rfigueroa.figscrm.dto.ContactDTO;
import com.rfigueroa.figscrm.dto.RestPageResponseDTO;
import com.rfigueroa.figscrm.projections.ContactDetailProjection;
import com.rfigueroa.figscrm.projections.ContactTableProjection;
import com.rfigueroa.figscrm.projections.ContactsDropdownProjection;
import com.rfigueroa.figscrm.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://master.d2b1tg1ojgscyw.amplifyapp.com"})
@RequestMapping("/api/v2/contacts")
public class ContactController {

    private ContactService contactService; // service layer for contact repository

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<RestPageResponseDTO<ContactTableProjection>> getContactsPage(
            @RequestParam(required = true) Integer customerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "firstName,asc") String[] sort ) {

        return new ResponseEntity<>(contactService.getContactsByPageRequest(page, size, sort, customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ContactDetailProjection> createNewContact(@Valid @RequestBody ContactDTO contactDTO) {
        return new ResponseEntity<>(contactService.createNewContact(contactDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{contactId}")
    public ResponseEntity<ContactDetailProjection> getSingleContact(@PathVariable Integer contactId) {
        return new ResponseEntity<>(contactService.getContactWithDetails(contactId), HttpStatus.OK);
    }


    @PatchMapping("/{contactId}")
    public ResponseEntity<ContactDetailProjection> updateExistingContact(@RequestBody ContactDTO contactDTO, @PathVariable Integer contactId) {

        return new ResponseEntity<>(contactService.updateExistingContact(contactDTO, contactId), HttpStatus.OK);
    }

    @DeleteMapping("/{contactId}")
    public HttpStatus deleteContact(@PathVariable Integer contactId) {

        contactService.deleteContactById(contactId);
        return HttpStatus.OK;
    }

    @DeleteMapping("/contact-method/{contactMethodId}")
    public HttpStatus deleteContactMethod(@PathVariable Integer contactMethodId) {

        contactService.deleteContactDetailById(contactMethodId);
        return HttpStatus.OK;
    }

    @GetMapping("/list/{customerId}")
    public ResponseEntity<List<ContactsDropdownProjection>> getContactsList(@PathVariable Integer customerId) {

        return new ResponseEntity<>(
                contactService.getContactsMapListByCustomerId(customerId),
                HttpStatus.OK
        );
    }
}
