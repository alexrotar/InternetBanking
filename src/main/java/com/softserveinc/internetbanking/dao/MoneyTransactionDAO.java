/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.softserveinc.internetbanking.dao;

import com.softserveinc.internetbanking.model.MoneyTransaction;
import java.math.BigDecimal;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author orotar
 */
public interface MoneyTransactionDAO {
    /** 
    * This is the method to be used to initialize
    * database resources ie. connection.
    * @param ds
    */
   public void setDataSource(DataSource ds);
   /** 
    * This is the method to be used to create
    * a record in the money_transaction table.
     * @param transactionId
     * @param account_id
     * @param destination_account_id
     * @param dateTime
     * @param transactionAmount
    */
   public void createTransaction(int account_id, int destination_account_id, BigDecimal transactionAmount);
   /** 
    * This is the method to be used to list down
    * a record from the money_transaction table corresponding
    * to a passed transaction id.
     * @param id
     * @return 
    */
   public List<MoneyTransaction> getTransaction(int id);
   /** 
    * This is the method to be used to list down
    * all the records from the money_transaction table.
     * @return 
    */
   public List<MoneyTransaction> listTransactions();

}
