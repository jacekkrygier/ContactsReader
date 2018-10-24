package com.example.contacts.mapper;

public class ContactChecker {


    //checking contacts, for now I accept only mobile phones in format (123456789, 123-456-789 or 123 456 789).
    public Integer checkType(String contact) {
    if (contact.contains("jbr")||contact.contains("jabber")){
        return 3;
    } else if (contact.contains("@")) {
        return 1;
    } else if (contact.matches("\\d{9}")) {
        return 2;
    } else if (contact.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{3}")) {
        return 2;
    } else {
        return 0;
        }
    }
}
