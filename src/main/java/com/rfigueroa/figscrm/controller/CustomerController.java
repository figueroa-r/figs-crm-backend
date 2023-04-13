package com.rfigueroa.figscrm.controller;

import com.rfigueroa.figscrm.dto.CustomerPatchRequest;
import com.rfigueroa.figscrm.dto.RestResponseDTO;
import com.rfigueroa.figscrm.entity.Customer;
import com.rfigueroa.figscrm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v2")
public class CustomerController {

    CustomerService customerService; // service layer for customer repository

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public ResponseEntity<RestResponseDTO<Customer>> getCustomersPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name,asc") String[] sort ) {

        return new ResponseEntity<>(customerService.getCustomersByPageRequest(page, size, sort), HttpStatus.OK);

    }

    @DeleteMapping("/customers")
    public HttpStatus deleteCustomersList (@RequestBody List<Integer> customersList) {
        customerService.deleteAllCustomersById(customersList);

        return HttpStatus.OK;
    }

    @GetMapping("/customers/{customerId}")
    public Customer getSingleCustomer(@PathVariable Integer customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer inputCustomer) {
        return customerService.createCustomer(inputCustomer);
    }

    @PatchMapping("/customers/{customerId}")
    public Customer updateCustomerFields(@RequestBody CustomerPatchRequest updatedFields) {
        return customerService.updateCustomer(updatedFields);
    }

    @DeleteMapping("/customers/{customerId}")
    public HttpStatus deleteCustomer(@PathVariable Integer customerId) {
        customerService.deleteCustomerById(customerId);

        return HttpStatus.OK;
    }

}
