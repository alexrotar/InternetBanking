/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.softserveinc.internetbanking.dao.jdbctemplate;

import com.softserveinc.internetbanking.dao.AccountDAO;
import com.softserveinc.internetbanking.model.Account;
import com.softserveinc.internetbanking.model.mapper.AccountRowMapper;
import java.math.BigDecimal;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author orotar
 */
@Repository
public class AccountJDBCTemplate implements AccountDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    
    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void createAccount(String status, String ownerName, BigDecimal accountAmount) {
        String SQL = "INSERT INTO account(status, owner_name, account_amount) VALUES (?, ?, ?)";
        jdbcTemplateObject.update( SQL, status, ownerName, accountAmount);
        System.out.println("Created Record = " + status + " " + ownerName + " " + accountAmount);
    }

    @Override
    public Account getAccount(int id) {
        String SQL = "SELECT * FROM account WHERE account_id = ?";
        Account account = jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new AccountRowMapper());
        return account;
    }

    @Override
    public Account getAccountByUsername(String username) {
        String SQL = "SELECT * FROM account WHERE username = ?";
        Account account = jdbcTemplateObject.queryForObject(SQL, new Object[]{username}, new AccountRowMapper());
        return account;
    }
    
    @Override
    public List<Account> listAccounts() {
        String SQL = "SELECT * FROM account";
        List <Account> accounts = jdbcTemplateObject.query(SQL, new AccountRowMapper());
        return accounts;
    }

    @Override
    public void delete(int id) {
        String SQL = "DELETE FROM account WHERE account_id = ?";
        jdbcTemplateObject.update(SQL, id);
        System.out.println("Deleted record with ID = " + id );
    }

    @Override
    public void updateStatus(int account_id, String status) {
        String SQL = "UPDATE account SET status = ? where account_id = ?";
        jdbcTemplateObject.update(SQL, status, account_id);
        System.out.println("Updated record with ID = " + account_id );
    }
    
}
