package com.rfigueroa.figscrm.service;

import com.rfigueroa.figscrm.dao.CustomerRepository;
import com.rfigueroa.figscrm.dto.CustomerPatchRequest;
import com.rfigueroa.figscrm.dto.PageDTO;
import com.rfigueroa.figscrm.dto.RestResponseDTO;
import com.rfigueroa.figscrm.entity.Customer;
import com.rfigueroa.figscrm.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // return page of customers
    public RestResponseDTO<Customer> getCustomersByPageRequest(int page, int size, String[] sortString) {

        // set sorting parameters
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.fromString(sortString[1]), sortString[0]));

        // create pageable input for repository
        Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

        Page<Customer> customersPage = customerRepository.findAll(pagingSort);
        // List for storing customers
        List<Customer> customersList;
        customersList = customersPage.getContent();

        if(customersList.isEmpty() && page != 0) {
            // message is for non-zero based page index
            throw new EntityNotFoundException("No Customers found for the current page, try a page less than " + (page + 1));
        }

        // structure page data for response
        PageDTO pageState = new PageDTO(
                customersPage.getNumber(),
                size,
                customersPage.getTotalElements(),
                customersPage.getTotalPages()
        );

        return new RestResponseDTO<>(customersList, pageState);
    }

    // batch delete customers by id
    public void deleteAllCustomersById(List<Integer> customerIds) {
        customerRepository.deleteAllById(customerIds);
    }

    // return single customer
    public Customer getCustomerById(Integer customerId) {

        Optional<Customer> customer = customerRepository.findById(customerId);

        if (customer.isEmpty()) {
            // no customer in repository with id = customerId
            throw new EntityNotFoundException("No Customer found with id of " + customerId );
        }

        return customer.get();
    }

    // create a single customer
    public Customer createCustomer(Customer inputCustomer) {

        // ensure that we are not overwriting an existing customer
        inputCustomer.setId(-1);

        return customerRepository.save(inputCustomer);
    }

    // delete a single customer
    public void deleteCustomerById(Integer id) {
        customerRepository.deleteById(id);
    }

    // update the fields on a customer
    @Transactional
    public Customer updateCustomer(CustomerPatchRequest updatedFields) {
        Optional<Customer> customerOptional = customerRepository.findById(updatedFields.getId());

        if (customerOptional.isEmpty()) throw
                new EntityNotFoundException("Customer does not exist with id of " + updatedFields.getId());

        Customer persistedCustomer = customerOptional.get();
        // update any fields that have been provided...
        if (updatedFields.getName() != null) persistedCustomer.setName(updatedFields.getName());
        if (updatedFields.getAlias() != null) persistedCustomer.setAlias(updatedFields.getAlias());
        if (updatedFields.getCompanyType() != null) persistedCustomer.setCompanyType(updatedFields.getCompanyType());
        if (updatedFields.getActive() != null) persistedCustomer.setIsActive(updatedFields.getActive());
        if (updatedFields.getVerified() != null) persistedCustomer.setIsVerified(updatedFields.getVerified());
        if (updatedFields.getAddress1() != null) persistedCustomer.setAddress1(updatedFields.getAddress1());
        if (updatedFields.getAddress2() != null) persistedCustomer.setAddress2(updatedFields.getAddress2());
        if (updatedFields.getCity() != null) persistedCustomer.setCity(updatedFields.getCity());
        if (updatedFields.getState() != null) persistedCustomer.setState(updatedFields.getState());
        if (updatedFields.getZip() != null) persistedCustomer.setZip(updatedFields.getZip());

        return customerRepository.save(persistedCustomer);
    }
}
