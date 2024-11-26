package com.yrgo.integrationtests;

import com.yrgo.domain.Customer;
import com.yrgo.services.customers.CustomerManagementService;
import com.yrgo.services.customers.CustomerNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@Transactional
@ContextConfiguration( { "/other-tiers.xml", "/datasource-test.xml" } )
public class CustomerMangementServiceTest {
    @Autowired
    private CustomerManagementService service;

    @Test
    public void testCreateCustomer(){
        Customer customer = new Customer("69","Daves Company AB","Good lad");
        service.newCustomer(customer);

        int customers = service.getAllCustomers().size();
        assertEquals(1, customers, "There should be 1 customer in the database!");
    }
    @Test
    public void testFindExistingCustomer() {
        Customer customer = new Customer("69","Daves Company AB","Good lad");
        service.newCustomer(customer);

        try{
           Customer foundCustomer = service.findCustomerById("69");
            assertEquals(customer, foundCustomer );
        }catch (CustomerNotFoundException e){
            fail("Did not find the customer!");
        }
    }
}
