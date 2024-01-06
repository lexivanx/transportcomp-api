package com.transportcompany.transportcompapi.controller;

import com.transportcompany.transportcompapi.model.Customer;
import com.transportcompany.transportcompapi.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        return customerService.getCustomerById(id)
                .map(existingCustomer -> {
                    customer.setCustomerID(id);
                    return ResponseEntity.ok(customerService.saveCustomer(customer));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        if (customerService.getCustomerById(id).isPresent()) {
            customerService.deleteCustomer(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to compare BillToPay and AmountPaid fields and update the isAllPaid field
    @PutMapping("/{id}/confirm-payments")
    public ResponseEntity<String> confirmPayment(@PathVariable int id) {
        boolean paymentConfirmed = customerService.checkPaymentStatusAndUpdate(id);

        if (paymentConfirmed) {
            return ResponseEntity.ok("Bills paid in full.");
        } else {
            return ResponseEntity.ok("Bills not paid yet.");
        }
    }

    // Endpoint to pay a bill regarding shipment
    // Example request body: { "amount": 55.23 }
    @PostMapping("/{id}/pay")
    public ResponseEntity<String> makePayment(@PathVariable int id, @RequestBody BigDecimal amount) {
        boolean paymentSuccess = customerService.pay(id, amount);

        if (paymentSuccess) {
            return ResponseEntity.ok("Payment made successfully.");
        } else {
            return ResponseEntity.badRequest().body("Payment failed. Customer not found, bills already paid or payment is too high.");
        }
    }
}
