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
 * MoneyTransaction class
 * @author orotar
 */

@Entity
@Table (name="money_transaction")
@XmlRootElement(name="moneyTransaction")
public class MoneyTransaction implements Serializable {
    
    @Id 
    @GeneratedValue (strategy=GenerationType.AUTO)
    @Column (name="transaction_id")
    private int transactionId;
    
    @Column (name="account_id")
    private int account_id;

    @Column (name="destination_account_id")
    private int destination_account_id;
    
    @Column (name="date_time")
    private String dateTime;
    
    @Column (name="amount")
    private BigDecimal transactionAmount;
    
    public MoneyTransaction(){}
    
    public MoneyTransaction(int transactionId, int account_id, int destination_account_id, String dateTime, BigDecimal transactionAmount){
        this.transactionId = transactionId;
        this.account_id = account_id;
        this.destination_account_id = destination_account_id;
        this.dateTime = dateTime;
        this.transactionAmount = transactionAmount;
    }
    
    public int getAccount_id() {
        return account_id;
    }

    @XmlElement
    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }
    

    public int getTransactionId() {
        return transactionId;
    }

    @XmlElement
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getDestination_account_id() {
        return destination_account_id;
    }

    @XmlElement
    public void setDestination_account_id(int destination_account_id) {
        this.destination_account_id = destination_account_id;
    }

    public String getDateTime() {
        return dateTime;
    }

    @XmlElement
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    @XmlElement
    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
    
    @Override
    public boolean equals(Object other){  
        if (this == other) return true;  
        if (!(other instanceof MoneyTransaction)) return false;  
        final MoneyTransaction that = (MoneyTransaction) other;  
        return this.getTransactionAmount().equals(that.getTransactionAmount());  
}  
  
    @Override
    public int hashCode(){  
        return this.getTransactionAmount().hashCode();  
    }  
    
}
