package com.rfigueroa.figscrm.dao;

import com.rfigueroa.figscrm.projections.ContactDetailProjection;
import com.rfigueroa.figscrm.projections.ContactTableProjection;
import com.rfigueroa.figscrm.projections.ContactsDropdownProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfigueroa.figscrm.entity.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    

    // additional method to search contacts by customerId
//    @RestResource(path = "byCustomerId", rel = "byCustomerId" )
//    Page<Contact> findAllByCustomerId(@Param("customerId") Integer customerId, Pageable pageable );

    // refined method using projection
    Page<ContactTableProjection> findAllByCustomerId(Integer customerId, Pageable pageable);

    Optional<ContactDetailProjection> findContactById(Integer contactId);

    // contacts dropdown by customer id -> contactId, avatarId, fullName
    List<ContactsDropdownProjection> findAllDropdownByCustomerId(Integer customerId);
}
