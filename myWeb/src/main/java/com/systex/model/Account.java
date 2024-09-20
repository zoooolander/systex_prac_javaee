/*
 * Account.java
 *

 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.systex.model;

import java.util.*;

/**
 *
 * @author Administrator
 */
public class Account {
    
    /** Creates a new instance of Account */
    public Account() {
    }

    /**
     * Holds value of property account.
     */
    private String account;

    /**
     * Getter for property account.
     * @return Value of property account.
     */
    public String getAccount() {
        return this.account;
    }

    /**
     * Setter for property account.
     * @param account New value of property account.
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * Holds value of property password.
     */
    private String password;

    /**
     * Getter for property password.
     * @return Value of property password.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Setter for property password.
     * @param password New value of property password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Holds value of property role.
     */
    private HashSet<String> roles = new HashSet<String>();

    /**
     * Getter for property role.
     * @return Value of property role.
     */
    public HashSet<String> getRoles() {
        return this.roles;
    }
	
	public String getRoleString() {
		String roleString = "";
		for(String role:roles){
			roleString += role;
			roleString += " ";
		}
		return roleString;
    }

    /**
     * Setter for property role.
     * @param role New value of property role.
     */
    public void addRole(String role) {
        this.roles.add(role);
    }
	
	public void removeRole(String role) {
        this.roles.remove(role);
    }
    
}
