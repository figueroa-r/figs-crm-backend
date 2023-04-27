package com.rfigueroa.figscrm.controller;

import com.rfigueroa.figscrm.dto.ContactMeFormDTO;
import com.rfigueroa.figscrm.service.ContactMeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://master.d2b1tg1ojgscyw.amplifyapp.com"})
@RequestMapping("/api/v2/contact-me")
public class ContactMeController {

    ContactMeService contactMeService;

    @Autowired
    public ContactMeController(ContactMeService contactMeService) {
        this.contactMeService = contactMeService;
    }

    @PostMapping
    public HttpStatus submitFormData(@Valid @RequestBody ContactMeFormDTO contactMeFormDTO) {

        // call service and create contact me persisted entity
        contactMeService.submitContactMeForm(contactMeFormDTO);
        return HttpStatus.OK;
    }
}
