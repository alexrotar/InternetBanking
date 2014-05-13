/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.softserveinc.internetbanking.testit;

import com.softserveinc.internetbanking.dao.jdbctemplate.UserJDBCTemplate;
import com.softserveinc.internetbanking.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author orotar
 */
public class JDBCtemplateUser {
    public static void main(String[] args){
      ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

      UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate)context.getBean("userJDBCTemplate");

      System.out.println("----Listing Record with ID = 1 -----" );
      User user = userJDBCTemplate.findUserByName("lex");
      String role = userJDBCTemplate.findUserRole(user.getId());
      System.out.println(role);
    }
}
