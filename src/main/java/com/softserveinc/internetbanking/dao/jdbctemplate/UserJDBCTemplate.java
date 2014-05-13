/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.softserveinc.internetbanking.dao.jdbctemplate;

import com.softserveinc.internetbanking.dao.UserDAO;
import com.softserveinc.internetbanking.model.Role;
import com.softserveinc.internetbanking.model.User;
import com.softserveinc.internetbanking.model.mapper.UserRowMapper;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author orotar
 */
@Repository
public class UserJDBCTemplate implements UserDAO {
    
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    
    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public User findUserByName(String username) {
        String SQL = "SELECT * FROM users WHERE username = ?";
        User user = jdbcTemplateObject.queryForObject(SQL, new Object[]{username}, new UserRowMapper());
        return user;
    }

    public String findUserRole(int userId) {
        String SQL = "SELECT role_name FROM user_role_xref WHERE user_id = ?";
        String role = (String)jdbcTemplateObject.queryForObject(SQL, new Object[] { userId }, String.class);
        return role;
    }
    
}
