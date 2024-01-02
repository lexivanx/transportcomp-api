package com.transportcompany.transportcompapi.service;

import com.transportcompany.transportcompapi.model.Customer;
import com.transportcompany.transportcompapi.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(int id) {
        return customerRepository.findById(id);
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }

    // Method to compare BillToPay and AmountPaid fields and update the isAllPaid field
    public boolean checkPaymentStatusAndUpdate(int customerId) {
        Optional<Customer> customerCurrent = customerRepository.findById(customerId);

        if (customerCurrent.isPresent()) {
            Customer customer = customerCurrent.get();
            if (customer.getBillToPay().compareTo(customer.getAmountPaid()) == 0) {
                customer.setIsAllPaid(true);
                customerRepository.save(customer);
                return true;
            }
        }
        return false;
    }

    // Method to pay a bill regarding shipment
    public boolean pay(int customerId, BigDecimal amount) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);

        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            BigDecimal newAmountPaid = customer.getAmountPaid().add(amount);
            customer.setAmountPaid(newAmountPaid);

            // Check if the customer has paid all bills and update isAllPaid field
            if (customer.getBillToPay().compareTo(customer.getAmountPaid()) == 0) {
                customer.setIsAllPaid(true);
            }

            customerRepository.save(customer);
            return true;
        }
        return false;
    }

}

