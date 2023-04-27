package com.rfigueroa.figscrm.service;

import com.rfigueroa.figscrm.dao.CustomerRepository;
import com.rfigueroa.figscrm.dto.CustomerDTO;
import com.rfigueroa.figscrm.dto.PageDTO;
import com.rfigueroa.figscrm.dto.RestPageResponseDTO;
import com.rfigueroa.figscrm.entity.Customer;
import com.rfigueroa.figscrm.exception.EntityNotFoundException;
import com.rfigueroa.figscrm.projections.CustomerDetails;
import com.rfigueroa.figscrm.projections.CustomerTableProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    private ProjectionFactory projectionFactory;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ProjectionFactory projectionFactory) {
        this.customerRepository = customerRepository;
        this.projectionFactory = projectionFactory;
    }

    // return page of customers
    public RestPageResponseDTO<CustomerTableProjection> getCustomersByPageRequest(int page, int size, String[] sortString) {

        // set sorting parameters
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.fromString(sortString[1]), sortString[0]));

        // create pageable input for repository
        Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

        Page<CustomerTableProjection> customersPage = customerRepository.findAllProjectedBy(pagingSort);
        // List for storing customers
        List<CustomerTableProjection> customersList;
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

        return new RestPageResponseDTO<>(customersList, pageState);
    }

    // batch delete customers by id
    public void deleteAllCustomersById(List<Integer> customerIds) {
        customerRepository.deleteAllById(customerIds);
    }

    // return single customer
    public CustomerDetails getCustomerById(Integer customerId) {

        Optional<CustomerDetails> customer = customerRepository.findCustomerById(customerId);

        if (customer.isEmpty()) {
            // no customer in repository with id = customerId
            throw new EntityNotFoundException("No Customer found with id of " + customerId );
        }

        return customer.get();
    }

    // create a single customer
    public CustomerDetails createCustomer(CustomerDTO inputCustomer) {

        // create a new customer and map values
        Customer customerToSave = new Customer();

        // map values to Customer object
        customerToSave.setAvatarUrl(inputCustomer.getAvatarUrl());
        customerToSave.setName(inputCustomer.getName());
        customerToSave.setAlias(inputCustomer.getAlias());
        customerToSave.setCompanyType(inputCustomer.getCompanyType());
        customerToSave.setIsActive(inputCustomer.getIsActive());
        customerToSave.setIsVerified(inputCustomer.getIsVerified());
        customerToSave.setAddress1(inputCustomer.getAddress1());
        customerToSave.setAddress2(inputCustomer.getAddress2());
        customerToSave.setCity(inputCustomer.getCity());
        customerToSave.setState(inputCustomer.getState());
        customerToSave.setZip(inputCustomer.getZip());

        Customer persistedCustomer = customerRepository.save(customerToSave);


        return projectionFactory.createProjection(CustomerDetails.class, persistedCustomer);
    }

    // delete a single customer
    public void deleteCustomerById(Integer id) {
        customerRepository.deleteById(id);
    }

    // update the fields on a customer
    @Transactional
    public CustomerDetails updateCustomer(CustomerDTO updatedFields, Integer customerId) {

        // retrieve customer from repository
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isEmpty()) throw
                new EntityNotFoundException("Customer does not exist with id of " + customerId);

        Customer persistedCustomer = customerOptional.get();
        // update any fields that have been provided...
        if (updatedFields.getName() != null) persistedCustomer.setName(updatedFields.getName());
        if (updatedFields.getAlias() != null) persistedCustomer.setAlias(updatedFields.getAlias());
        if (updatedFields.getCompanyType() != null) persistedCustomer.setCompanyType(updatedFields.getCompanyType());
        if (updatedFields.getIsActive() != null) persistedCustomer.setIsActive(updatedFields.getIsActive());
        if (updatedFields.getIsVerified() != null) persistedCustomer.setIsVerified(updatedFields.getIsVerified());
        if (updatedFields.getAddress1() != null) persistedCustomer.setAddress1(updatedFields.getAddress1());
        if (updatedFields.getAddress2() != null) persistedCustomer.setAddress2(updatedFields.getAddress2());
        if (updatedFields.getCity() != null) persistedCustomer.setCity(updatedFields.getCity());
        if (updatedFields.getState() != null) persistedCustomer.setState(updatedFields.getState());
        if (updatedFields.getZip() != null) persistedCustomer.setZip(updatedFields.getZip());

        Customer updatedCustomer = customerRepository.save(persistedCustomer);

        return projectionFactory.createProjection(CustomerDetails.class, updatedCustomer);
    }
}
