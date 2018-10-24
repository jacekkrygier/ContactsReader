package com.example.contacts.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="contacts")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLcontacts {
    private List<String> phone;
    private List<String> email;
    private List<String> icq;
    private List<String> jabber;

    public XMLcontacts(List<String> phone, List<String> email, List<String> icq, List<String> jabber) {
        this.phone = phone;
        this.email = email;
        this.icq = icq;
        this.jabber = jabber;
    }

    public XMLcontacts() {
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public List<String> getIcq() {
        return icq;
    }

    public void setIcq(List<String> icq) {
        this.icq = icq;
    }

    public List<String> getJabber() {
        return jabber;
    }

    public void setJabber(List<String> jabber) {
        this.jabber = jabber;
    }

    @Override
    public String toString() {
        return "XMLcontacts{" +
                "phone=" + phone +
                ", email=" + email +
                ", icq=" + icq +
                ", jabber=" + jabber +
                '}';
    }
}
