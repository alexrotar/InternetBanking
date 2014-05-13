/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.softserveinc.internetbanking.service;

import com.softserveinc.internetbanking.dao.jdbctemplate.MoneyTransactionJDBCTemplate;
import com.softserveinc.internetbanking.model.MoneyTransaction;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Path("/transaction")
public class MoneyTransactionService {
    
    private ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

    private MoneyTransactionJDBCTemplate moneyTransactionJDBCTemplate = (MoneyTransactionJDBCTemplate)context.getBean("moneyTransactionJDBCTemplate");
    
     // Return the list of MoneyTransactions with GET method
     @GET
     @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
     public List<MoneyTransaction> getAllMoneyTransactions() {
            return moneyTransactionJDBCTemplate.listTransactions();
     }
     
     // Return selected MoneyTransaction with GET method
     @GET
     @Path("/{param}")
     @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
     public List<MoneyTransaction> getMoneyTransactionByAccountId(@PathParam("param") int accountId){
            return moneyTransactionJDBCTemplate.getTransaction(accountId);
     }    
     
     // Create new MoneyTransaction with POST method
     @POST
     @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
     public void createTransaction(MoneyTransaction transaction){
         moneyTransactionJDBCTemplate.createTransaction(transaction.getAccount_id(), transaction.getDestination_account_id(), transaction.getTransactionAmount());
     }
     
}
