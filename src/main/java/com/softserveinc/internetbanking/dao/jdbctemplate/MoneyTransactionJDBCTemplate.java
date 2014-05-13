/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.softserveinc.internetbanking.dao.jdbctemplate;

import com.softserveinc.internetbanking.dao.MoneyTransactionDAO;
import com.softserveinc.internetbanking.model.MoneyTransaction;
import com.softserveinc.internetbanking.model.mapper.MoneyTransactionRowMapper;
import java.math.BigDecimal;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author orotar
 */
public class MoneyTransactionJDBCTemplate implements MoneyTransactionDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    
    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    @Override
    public void createTransaction(int account_id, int destination_account_id, BigDecimal transactionAmount) {
        String SQL = "INSERT INTO money_transaction(account_id, destination_account_id, amount) VALUES (?, ?, ?)";
        jdbcTemplateObject.update( SQL, account_id, destination_account_id, transactionAmount);
        System.out.println("Created Record = " + account_id + " " + destination_account_id + " " + transactionAmount);
    }

    @Override
    public List<MoneyTransaction> getTransaction(int id) {
        String SQL = "SELECT * FROM money_transaction WHERE account_id = ?";
        List<MoneyTransaction> moneyTransaction = jdbcTemplateObject.query(SQL, new Object[]{id}, new MoneyTransactionRowMapper());
        return moneyTransaction;
    }

    @Override
    public List<MoneyTransaction> listTransactions() {
        String SQL = "SELECT * FROM money_transaction";
        List <MoneyTransaction> moneyTransaction = jdbcTemplateObject.query(SQL, new MoneyTransactionRowMapper());
        return moneyTransaction;
    }
    
}