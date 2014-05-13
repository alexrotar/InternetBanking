/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.softserveinc.internetbanking.model.mapper;

import com.softserveinc.internetbanking.model.User;
import com.softserveinc.internetbanking.model.UserStatus;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author orotar
 */

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setStatus(UserStatus.valueOf(rs.getString("status")));
        user.setUserFullName(rs.getString("full_name"));
        user.setId(rs.getInt("id"));
        return user;
    }
    
}
