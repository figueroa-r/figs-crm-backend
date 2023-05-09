package com.rfigueroa.figscrm.controller;

import com.rfigueroa.figscrm.dto.CustomerDTO;
import com.rfigueroa.figscrm.dto.RestPageResponseDTO;
import com.rfigueroa.figscrm.projections.CustomerDetailsProjection;
import com.rfigueroa.figscrm.projections.CustomerTableProjection;
import com.rfigueroa.figscrm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v2/customers")
public class CustomerController {

    CustomerService customerService; // service layer for customer repository

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<RestPageResponseDTO<CustomerTableProjection>> getCustomersPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name,asc") String[] sort ) {

        return new ResponseEntity<>(customerService.getCustomersByPageRequest(page, size, sort), HttpStatus.OK);

    }

    @DeleteMapping
    public HttpStatus deleteCustomersList (@RequestBody List<Integer> customersList) {
        customerService.deleteAllCustomersById(customersList);

        return HttpStatus.OK;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDetailsProjection> getSingleCustomer(@PathVariable Integer customerId) {
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDetailsProjection> createCustomer(@Valid @RequestBody CustomerDTO inputCustomer) {
        return new ResponseEntity<>(customerService.createCustomer(inputCustomer), HttpStatus.CREATED);
    }

    @PatchMapping("/{customerId}")
    public ResponseEntity<CustomerDetailsProjection> updateCustomerFields(@RequestBody CustomerDTO updatedFields, @PathVariable Integer customerId) {
        return new ResponseEntity<>(customerService.updateCustomer(updatedFields, customerId), HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public HttpStatus deleteCustomer(@PathVariable Integer customerId) {
        customerService.deleteCustomerById(customerId);

        return HttpStatus.OK;
    }

}
