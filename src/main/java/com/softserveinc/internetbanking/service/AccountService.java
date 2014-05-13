/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.softserveinc.internetbanking.service;

import com.softserveinc.internetbanking.dao.jdbctemplate.AccountJDBCTemplate;
import com.softserveinc.internetbanking.model.Account;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author orotar
 */
@Transactional
@Service
@Component
@Path("/account")
public class AccountService {
    
    private ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    private AccountJDBCTemplate accountJDBCTemplate = (AccountJDBCTemplate)context.getBean("accountJDBCTemplate");
    
     // Return the list of Accounts with GET method
     @GET
     @Produces ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
     public List<Account> getAllAccounts() {
            return accountJDBCTemplate.listAccounts();
     }
     
     // Return selected Account with GET method (id)
     @GET
     @Path("/{param}")
     @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
     public Account getAccountById(@PathParam("param") int accountId){
            return accountJDBCTemplate.getAccount(accountId);
     }
     
     // Return selected Account with GET (username)
     @GET
     @Path("/username/{param}")
     @Produces ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
     public Account getAccountByusername(@PathParam("param") String username){
            return accountJDBCTemplate.getAccountByUsername(username);
     }
     
     @PUT
     @Path("/{param}")
     @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
     @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
     public Account editAccountStatus(@PathParam("param") int accountId, Account account){
         accountJDBCTemplate.updateStatus(accountId, account.getStatus());
         return accountJDBCTemplate.getAccount(accountId);
     }
     
} 