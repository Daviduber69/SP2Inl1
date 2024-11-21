package com.yrgo.dataaccess;

import com.yrgo.domain.Action;
import com.yrgo.domain.Call;
import com.yrgo.domain.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomerDaoJPAImpl implements CustomerDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Customer customer) {
        em.persist(customer);
    }

    @Override
    public Customer getById(String customerId) throws RecordNotFoundException {
        try {
            return em.createQuery("SELECT customer FROM Customer as customer WHERE customer.customerId =: customerId"
                    , Customer.class).setParameter("customerId", customerId).getSingleResult();

        } catch (javax.persistence.NoResultException e) {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public List<Customer> getByName(String name) {
        return em.createQuery("SELECT customer FROM Customer as customer WHERE customer.companyName =: name"
                , Customer.class).setParameter("name", name).getResultList();
    }

    @Override
    public void update(Customer customerToUpdate) throws RecordNotFoundException {
        try {
            em.find(Action.class, customerToUpdate.getCustomerId());
            em.merge(customerToUpdate);

        } catch (javax.persistence.NoResultException e) {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void delete(Customer oldCustomer) throws RecordNotFoundException {
        try {
            em.remove(oldCustomer);
        } catch (javax.persistence.NoResultException e) {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return em.createQuery("SELECT customer FROM Customer AS customer", Customer.class).getResultList();
    }

    @Override
    public Customer getFullCustomerDetail(String customerId) throws RecordNotFoundException {
        try {
            return em.createQuery("SELECT customer FROM Customer customer " +
                            "LEFT JOIN FETCH customer.calls WHERE customer.customerId = :customerId",
                    Customer.class).setParameter("customerId", customerId).getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void addCall(Call newCall, String customerId) throws RecordNotFoundException {
        try {
            Customer customer = em.find(Customer.class, customerId);
            customer.addCall(newCall);
            newCall.setCustomerId(customerId);
            em.persist(newCall);
            em.merge(customer);

        } catch (javax.persistence.NoResultException e) {
            throw new RecordNotFoundException();
        }
    }
}
