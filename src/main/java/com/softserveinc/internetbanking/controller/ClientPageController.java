/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.softserveinc.internetbanking.controller;

/**
 *
 * @author orotar
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
public class ClientPageController {
 
    @RequestMapping("/client")
     public String page() {
     return "client";
     }
 
}
