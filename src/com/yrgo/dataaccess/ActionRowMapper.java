package com.yrgo.dataaccess;

import com.yrgo.domain.Action;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

class ActionRowMapper implements RowMapper<Action> {
    public Action mapRow(ResultSet rs, int arg1) throws SQLException {
        int actionId = rs.getInt(1);
        String details = rs.getString(2);
        boolean complete = rs.getBoolean(3);
        String owningUser = rs.getString(4);
        Date requiredBy = rs.getDate(5);

        Calendar requiredByCal = new java.util.GregorianCalendar();
        requiredByCal.setTime(requiredBy);

        return new Action("" + actionId, details, requiredByCal, owningUser, complete);
    }
}
