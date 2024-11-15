package com.yrgo.dataaccess;

import com.yrgo.domain.Customer;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {
     @Override
     public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

         String customerId = rs.getString("CUSTOMER_ID");
         String companyName = rs.getString("COMPANY_NAME");
         String notes = rs.getString("NOTES");

         return new Customer(customerId, companyName, notes);
     }
 }