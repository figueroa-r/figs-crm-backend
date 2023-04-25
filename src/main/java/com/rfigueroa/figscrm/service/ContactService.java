package com.rfigueroa.figscrm.service;

import com.rfigueroa.figscrm.dao.ContactDetailRepository;
import com.rfigueroa.figscrm.dao.ContactRepository;
import com.rfigueroa.figscrm.dao.CustomerRepository;
import com.rfigueroa.figscrm.dto.ContactDTO;
import com.rfigueroa.figscrm.dto.ContactDetailDTO;
import com.rfigueroa.figscrm.dto.PageDTO;
import com.rfigueroa.figscrm.dto.RestPageResponseDTO;
import com.rfigueroa.figscrm.entity.Contact;
import com.rfigueroa.figscrm.entity.ContactDetail;
import com.rfigueroa.figscrm.entity.Customer;
import com.rfigueroa.figscrm.exception.EntityNotFoundException;
import com.rfigueroa.figscrm.projections.ContactDetails;
import com.rfigueroa.figscrm.projections.ContactTableProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private ContactRepository contactRepository;

    private CustomerRepository customerRepository;

    private ContactDetailRepository contactDetailRepository;

    private ProjectionFactory projectionFactory;

    @Autowired
    public ContactService(ContactRepository contactRepository,
                          CustomerRepository customerRepository,
                          ContactDetailRepository contactDetailRepository, ProjectionFactory projectionFactory) {
        this.contactRepository = contactRepository;
        this.customerRepository = customerRepository;
        this.contactDetailRepository = contactDetailRepository;
        this.projectionFactory = projectionFactory;
    }

    // get list of contacts for table
    public RestPageResponseDTO<ContactTableProjection> getContactsByPageRequest(int page,
                                                                                int size,
                                                                                String[] sortString,
                                                                                Integer customerId) {

        // set sorting parameters
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.fromString(sortString[1]), sortString[0]));

        // create pageable input for repository
        Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

        // get pageable from repo
        Page<ContactTableProjection> contactsPage = contactRepository.findAllByCustomerId(customerId, pagingSort);

        // list of contacts
        List<ContactTableProjection> contactsList;
        contactsList = contactsPage.getContent();

        if(contactsList.isEmpty() && page != 0) {
            // message is for non-zero based page index
            throw new EntityNotFoundException("No Contacts found for the current page, try a page less than " + page);
        }

        // structure page data for response
        PageDTO pageState = new PageDTO(
                contactsPage.getNumber(),
                size,
                contactsPage.getTotalElements(),
                contactsPage.getTotalPages()
        );

        return new RestPageResponseDTO<>(contactsList, pageState);
    }

    // get single contact as well as contact details
    @Transactional
    public ContactDetails getContactWithDetails(Integer contactId) {
        Optional<ContactDetails> contactOptional = contactRepository.findContactById(contactId);

        if(contactOptional.isEmpty()) throw new EntityNotFoundException("Could not find contact with id of " + contactId);

        // get the contact as well as the associated entity
        ContactDetails contact = contactOptional.get();
        contact.getContactsList();

        return contact;
    }

    // save a contact
    @Transactional
    public ContactDetails createNewContact(ContactDTO newContact) {

        // get reference to customer by the provided id and add bidirectional relationship
        Customer customerReference = customerRepository.getReferenceById(newContact.getCustomerId());

        // create contact entity and add to customerContacts
        Contact persistContact = new Contact();

        persistContact.setFirstName(newContact.getFirstName());
        persistContact.setLastName(newContact.getLastName());
        persistContact.setIsActive(newContact.getIsActive());
        persistContact.setTitle(newContact.getTitle());
        persistContact.setDepartment(newContact.getDepartment());
        persistContact.setAvatarId(newContact.getAvatarId());
        
        // add bidirectional association
        customerReference.addContact(persistContact);
        
        // create new contact methods for each 
        for (ContactDetailDTO contactDetail : newContact.getContactMethods() ) {

            // create entity
            ContactDetail newContactDetail = new ContactDetail();

            // set fields
            newContactDetail.setContactType(contactDetail.getContactType());
            newContactDetail.setContactDetail(contactDetail.getContactDetail());

            // add relationship
            persistContact.addContactDetail(newContactDetail);
        }

        Contact createdContact = contactRepository.save(persistContact);

        return projectionFactory.createProjection(ContactDetails.class, createdContact);
    }

    @Transactional
    public ContactDetails updateExistingContact(ContactDTO contactDTO, Integer existingContactId) {

        // get reference to existing entity...
        Contact existingContact = contactRepository.getReferenceById(existingContactId);

        // go through non-null values and update
        if(contactDTO.getFirstName() != null) existingContact.setFirstName(contactDTO.getFirstName());
        if(contactDTO.getLastName() != null) existingContact.setLastName(contactDTO.getLastName());
        if(contactDTO.getTitle() != null) existingContact.setTitle(contactDTO.getTitle());
        if(contactDTO.getDepartment() != null) existingContact.setDepartment(contactDTO.getDepartment());
        if(contactDTO.getAvatarId() != null) existingContact.setAvatarId(contactDTO.getAvatarId());
        if(contactDTO.getIsActive() != null) existingContact.setIsActive(contactDTO.getIsActive());

        if(contactDTO.getContactMethods() != null) {
            // create a new ArrayList to save the modified/ created detail entities
            List<ContactDetail> updatedList = new ArrayList<>();

            // loop through list of contact methods and update fields or create new contactDetail
            for (ContactDetailDTO contactMethod: contactDTO.getContactMethods()) {

                ContactDetail newContactDetail = new ContactDetail();

                newContactDetail.setId(contactMethod.getId());
                newContactDetail.setContactType(contactMethod.getContactType());
                newContactDetail.setContactDetail(contactMethod.getContactDetail());
                newContactDetail.setContact(existingContact);

                // add the updated/new entity to the contact
                updatedList.add(newContactDetail);

            }

            updatedList = contactDetailRepository.saveAll(updatedList);

            existingContact.setContactsList(updatedList);
        }

        // save entity
        Contact updatedContact = contactRepository.save(existingContact);

        return projectionFactory.createProjection(ContactDetails.class, updatedContact);
    }

    public void deleteContactById(Integer id) {
        contactRepository.deleteById(id);
    }

    public void deleteContactDetailById(Integer id) {
        contactDetailRepository.deleteById(id);
    }
}
