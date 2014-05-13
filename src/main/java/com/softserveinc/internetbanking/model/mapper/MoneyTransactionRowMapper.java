/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.softserveinc.internetbanking.model.mapper;

import com.softserveinc.internetbanking.model.MoneyTransaction;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author orotar
 */
public class MoneyTransactionRowMapper implements RowMapper<MoneyTransaction> {

    @Override
    public MoneyTransaction mapRow(ResultSet rs, int i) throws SQLException {
        MoneyTransaction moneyTransaction = new MoneyTransaction();
        moneyTransaction.setAccount_id(rs.getInt("account_id"));
        moneyTransaction.setDateTime(rs.getString("date_time"));
        moneyTransaction.setDestination_account_id(rs.getInt("destination_account_id"));
        moneyTransaction.setTransactionAmount(rs.getBigDecimal("amount"));
        moneyTransaction.setTransactionId(rs.getInt("transaction_id"));
        return moneyTransaction;
    }
    
}
