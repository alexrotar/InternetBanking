/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.softserveinc.internetbanking.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Account class
 * @author orotar
 */
@Entity
@Table (name="account")
@XmlRootElement(name="account")
public class Account implements Serializable {
    
    @Id 
    @GeneratedValue (strategy=GenerationType.AUTO)
    @Column (name="account_id")
    private int account_id;

    @Column (name="status")
    private String status;

    @Column (name="owner_name")
    private String ownerName;
    
    @Column (name="username")
    private String username;
    
    @Column (name="account_amount")
    private BigDecimal accountAmount;
    
    public Account() {}

    public Account(int account_id, String status, String ownerName, BigDecimal accountAmount) {
        this.account_id = account_id;
        this.status = status;
        this.ownerName = ownerName;
        this.accountAmount = accountAmount;
    }
    
    public String getUsername() {
        return username;
    }

    @XmlElement
    public void setUsername(String username) {
        this.username = username;
    }
    
    public int getAccount_id() {
        return account_id;
    }

    @XmlElement
    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getStatus() {
        return status;
    }

    @XmlElement
    public void setStatus(String status) {
        this.status = status;
    }

    public String getOwnerName() {
        return ownerName;
    }

    @XmlElement
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public BigDecimal getAccountAmount() {
        return accountAmount;
    }

    @XmlElement
    public void setAccountAmount(BigDecimal accountAmount) {
        this.accountAmount = accountAmount;
    }
    
    @Override
    public boolean equals(Object other){  
        if (this == other) return true;  
        if (!(other instanceof Account)) return false;  
        final Account that = (Account) other;  
        return this.getAccountAmount().equals(that.getAccountAmount());  
    }  
  
    @Override
    public int hashCode(){  
        return this.getAccountAmount().hashCode();  
    }  
    
}
