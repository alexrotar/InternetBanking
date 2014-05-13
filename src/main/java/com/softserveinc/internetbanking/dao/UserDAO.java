/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.softserveinc.internetbanking.dao;

import com.softserveinc.internetbanking.model.User;
import javax.sql.DataSource;
import org.springframework.stereotype.Component;

/**
 * User DAO interface
 * @author orotar
 */
@Component
public interface UserDAO {
    
    /** 
    * This is the method to be used to initialize
    * database resources ie. connection.
    * @param ds
    */
   public void setDataSource(DataSource ds);
    
    /** 
    * This is the method to be used to list down
    * a record from the User table corresponding
    * to a passed user id.
     * @param username
     * @return 
    */
   public User findUserByName(String username);
   
}
