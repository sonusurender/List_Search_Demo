package com.listsearch_demo.helper;

/**
 * Created by sonu on 14/02/17.
 */

/*  POJO Class of UserModel  */
public class UserModel {
    private String name, number, emailID;

    public UserModel(String name, String number, String emailID) {
        this.name = name;
        this.number = number;
        this.emailID = emailID;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getEmailID() {
        return emailID;
    }
}
