package com.yrgo.dataaccess;

import com.yrgo.domain.Call;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CallRowMapper implements RowMapper<Call> {
    @Override
    public Call mapRow(ResultSet rs, int rowNum) throws SQLException {
        int callId = rs.getInt("CALL_ID");
        Date timeAndDate = rs.getDate("TIME_AND_DATE");
        String notes = rs.getString("NOTES");
        String customerId = rs.getString("CUSTOMER_ID");
        Call call = new Call(notes, timeAndDate, customerId);
        call.setId(callId);
        call.setCustomerId(customerId);
        return call;
    }
}