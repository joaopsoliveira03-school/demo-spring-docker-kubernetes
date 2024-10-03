package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping()
    public Iterable<Customer> getAll(){
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customer get(@PathVariable Long id){
        return customerRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()).getBody();
    }

    @PostMapping
    public Customer create(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @RequestBody Customer customer){
        return customerRepository.findById(id).map(c -> {
            c.setFirstName(customer.getFirstName());
            c.setLastName(customer.getLastName());
            return customerRepository.save(c);
        }).orElseGet(() -> {
            customer.setId(id);
            return customerRepository.save(customer);
        });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        customerRepository.deleteById(id);
    }


}
