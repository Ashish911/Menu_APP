package com.example.menu_app.model;

public class Users {

    private String FullName;
    private String UserName;
    private String Email;
    private String PhoneNo;
    private String Password;
    private boolean isWaiter;
    private boolean isChief;

    public Users(String fullName, String userName, String email, String phoneNo) {
        FullName = fullName;
        UserName = userName;
        Email = email;
        PhoneNo = phoneNo;
    }

    public Users(String fullName, String userName, String email, String phoneNo, String password) {
        FullName = fullName;
        UserName = userName;
        Email = email;
        PhoneNo = phoneNo;
        Password = password;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean isWaiter() {
        return isWaiter;
    }

    public void setWaiter(boolean waiter) {
        isWaiter = waiter;
    }

    public boolean isChief() {
        return isChief;
    }

    public void setChief(boolean chief) {
        isChief = chief;
    }
}
