/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.softserveinc.internetbanking.model.mapper;

import com.softserveinc.internetbanking.model.Account;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author orotar
 */
public class AccountRowMapper implements RowMapper<Account> {
    
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setAccount_id(rs.getInt("account_id"));
        account.setOwnerName(rs.getString("owner_name"));
        account.setAccountAmount(rs.getBigDecimal("account_amount"));
        account.setStatus(rs.getString("status"));
        account.setUsername(rs.getString("username"));
        return account;
    }
    
}
