/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.softserveinc.internetbanking.testit;

import com.softserveinc.internetbanking.dao.jdbctemplate.MoneyTransactionJDBCTemplate;
import java.math.BigDecimal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author orotar
 */
public class JDBCtemplateMoneyTransaction {
    public static void main(String[] args){
      ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

      MoneyTransactionJDBCTemplate moneyTransactionJDBCTemplate = (MoneyTransactionJDBCTemplate)context.getBean("moneyTransactionJDBCTemplate");
      
      System.out.println("------Records Creation--------" );
      BigDecimal number = new BigDecimal("200.00");
      moneyTransactionJDBCTemplate.createTransaction(1, 3, number);
      moneyTransactionJDBCTemplate.createTransaction(1, 3, number);
      
    }
}
