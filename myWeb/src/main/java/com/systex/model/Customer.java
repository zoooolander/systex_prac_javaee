/*
 * Customer.java
 *
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.systex.model;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class Customer implements Serializable {

    /** Creates a new instance of Customer */
    public Customer() {
    }
    /**
     * Holds value of property name.
     */
    private int cid;

    /**
     * Getter for property name.
     * @return Value of property name.
     */
    public int getCid() {
        return this.cid;
    }

    /**
     * Setter for property name.
     * @param name New value of property name.
     */
    public void setCid(int cid) {
        this.cid = cid;
    }
    /**
     * Holds value of property name.
     */
    private String name;

    /**
     * Getter for property name.
     * @return Value of property name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for property name.
     * @param name New value of property name.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Holds value of property password.
     */
    private String email;

    /**
     * Getter for property password.
     * @return Value of property password.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter for property password.
     * @param password New value of property password.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Holds value of property telephone.
     */
    private String telephone;

    /**
     * Getter for property telephone.
     * @return Value of property telephone.
     */
    public String getTelephone() {
        return this.telephone;
    }

    /**
     * Setter for property telephone.
     * @param telephone New value of property telephone.
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    /**
     * Holds value of property address.
     */
    private String address;

    /**
     * Getter for property address.
     * @return Value of property address.
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Setter for property address.
     * @param address New value of property address.
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Holds value of property gender.
     */
    private String gender;

    /**
     * Getter for property gender.
     * @return Value of property gender.
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * Setter for property gender.
     * @param gender New value of property gender.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    /**
     * Holds value of property habits.
     */
    private String[] habits;

    /**
     * Getter for property habits.
     * @return Value of property habits.
     */
    public String[] getHabits() {
        return this.habits;
    }

    public String getHabitString() {
        String habitString = "";
        int len = habits.length;
        for (int i = 0; i < len; i++) {
            if (habits[i].equals("music")) {
                habitString += "聽音樂";
            }
            if (habits[i].equals("shopping")) {
                habitString += "逛大街";
            }
            if (habits[i].equals("reading")) {
                habitString += "讀好書";
            }
            if (i != len - 1) {
                habitString += ",";
            }
        }
        return habitString;
    }

    /**
     * Setter for property habits.
     * @param habits New value of property habits.
     */
    public void setHabits(String[] habits) {
        this.habits = habits;
    }
    /**
     * Holds value of property birth.
     */
    private String birth;

    /**
     * Getter for property birth.
     * @return Value of property birth.
     */
    public String getBirth() {
        return this.birth;
    }

    public String getBirthYearString() {
        //String year = this.birth.split("-")[0];
        return this.birth.split("-")[0];
    }
    
    public int getBirthYear() {
        return Integer.parseInt(this.birth.split("-")[0]);
    }
    
    public int getBirthMonth() {
        return Integer.parseInt(this.birth.split("-")[1]);
    }

    public int getBirthDay() {
        return Integer.parseInt(this.birth.split("-")[2]);
    }

    /**
     * Setter for property birth.
     * @param birth New value of property birth.
     */
    public void setBirth(String birth) {
        this.birth = birth;
    }
    /**
     * Holds value of property account.
     */
    private String account;

    /**
     * Getter for property birth.
     * @return Value of property birth.
     */
    public String getAccount() {
        return this.account;
    }

    /**
     * Setter for property birth.
     * @param birth New value of property birth.
     */
    public void setAccount(String account) {
        this.account = account;
    }
}
