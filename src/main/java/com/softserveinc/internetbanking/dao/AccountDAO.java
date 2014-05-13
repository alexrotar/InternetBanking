/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.softserveinc.internetbanking.dao;

import com.softserveinc.internetbanking.model.Account;
import java.math.BigDecimal;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author orotar
 */
public interface AccountDAO {
    /** 
    * This is the method to be used to initialize
    * database resources ie. connection.
    * @param ds
    */
   public void setDataSource(DataSource ds);
   /** 
    * This is the method to be used to create
    * a record in the account table.
     * @param account_id
     * @param status
     * @param ownerName
     * @param accountAmount
    */
   public void createAccount(String status, String ownerName, BigDecimal accountAmount);
   /** 
    * This is the method to be used to list down
    * a record from the Account table corresponding
    * to a passed account id.
     * @param id
     * @return 
    */
   public Account getAccount(int id);
   /** 
    * This is the method to be used to list down
    * all the records from the Account table.
     * @return 
    */
   public List<Account> listAccounts();
   /** 
    * This is the method to be used to delete
    * a record from the Account table corresponding
    * to a passed account id.
     * @param id
    */
   public void delete(int id);
   /** 
    * This is the method to be used to update
    * a record into the Account table.
     * @param account_id
     * @param status
    */
   public void updateStatus(int account_id, String status);
    /** 
    * This is the method to be used to update
    * a record into the Account table.
     * @param username
     * @return 
    */
   public Account getAccountByUsername(String username);
}
