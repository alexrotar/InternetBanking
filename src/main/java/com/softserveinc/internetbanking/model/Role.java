/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softserveinc.internetbanking.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Role class represents role of the user
 * @author orotar
 */
@Entity
@Table (name="role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String roleName;

    //Constructor
    public Role(){}

    //Overloaded constructor for the Role class
    public Role(int id, String roleName, List<User> users) {
        this.id = id;
        this.roleName = roleName;
    }

    //Getter for role id
    public int getId() {
        return id;
    }

    //Setter for role id
    public void setId(int id) {
        this.id = id;
    }

    //Getter for roleName
    public String getRoleName() {
        return roleName;
    }

    //Setter for roleName
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
}
