/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.softserveinc.internetbanking.testit;

import com.softserveinc.internetbanking.dao.jdbctemplate.AccountJDBCTemplate;
import com.softserveinc.internetbanking.model.Account;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author orotar
 */
public class JDBCtemplateAccount {
    public static void main(String[] args){
      ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

      AccountJDBCTemplate accountJDBCTemplate = (AccountJDBCTemplate)context.getBean("accountJDBCTemplate");
//      
//      System.out.println("------Records Creation--------" );
//      BigDecimal number = new BigDecimal("200.00");
//      accountJDBCTemplate.createAccount("NEW", "Valex Obama", number);
//      accountJDBCTemplate.createAccount("NEW", "Ralex Obama", number);

//      System.out.println("------Listing Multiple Records--------" );
//      List<Account> accounts = accountJDBCTemplate.listAccounts();
//      for (Account record : accounts) {
//         System.out.print("ID : " + record.getAccount_id() );
//         System.out.print(", Status : " + record.getStatus());
//         System.out.println(", Owner Name : " + record.getOwnerName());
//         System.out.println(", Account Amount : " + record.getAccountAmount());
//      }

//      System.out.println("----Updating Record with ID = 4 -----" );
//      accountJDBCTemplate.updateStatus(4, "ACTIVE");
//
      System.out.println("----Listing Record with ID = 1 -----" );
      Account account = accountJDBCTemplate.getAccount(1);
      System.out.print("ID : " + account.getAccount_id() );
      System.out.print(", Name : " + account.getOwnerName() );
      System.out.println("username " + accountJDBCTemplate.getAccountByUsername("lex").getOwnerName());
	  
    }
}
