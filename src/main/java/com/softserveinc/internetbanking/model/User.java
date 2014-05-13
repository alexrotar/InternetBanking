/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.softserveinc.internetbanking.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Users class
 * @author orotar
 */
@Entity
@Table (name="users")
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String userFullName;
    private List<String> roles;

    //Constructor
    public User() {
    }
    
    //Overloaded constructor for the User class
    public User(int id, String password, UserStatus status, List<String> roles, String username) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.roles = roles;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    //Getter for user Id
    public int getId() {
        return id;
    }

    //Setter for user Id
    public void setId(int id) {
        this.id = id;
    }

    //Getter for username
    public String getUsername() {
        return username;
    }

    //Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    //Getter for user password
    public String getPassword() {
        return password;
    }

    //Setter for user password
    public void setPassword(String password) {
        this.password = password;
    }

    //Getter for user status
    public UserStatus getStatus() {
        return status;
    }

    //Setter for user status
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    //Getter for user's roles
    public List<String> getRoles() {
        return roles;
    }

    //Setter for user's roles
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    
    @Override
    public boolean equals(Object other){  
        if (this == other) return true;  
        if (!(other instanceof User)) return false;  
        final User that = (User) other;  
        return this.getUsername().equals(that.getUsername());  
}  
  
    @Override
    public int hashCode(){  
        return this.getUsername().hashCode();  
    }  
}