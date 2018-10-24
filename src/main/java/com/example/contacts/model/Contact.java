package com.example.contacts.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="contacts")
@XmlAccessorType(XmlAccessType.FIELD)
public class Contact {
    private Integer id;
    private Integer customerId;
    private Integer type;
    private String contactName;


    public Contact() {}

    public Contact(Integer type, String contactName) {
        this.type = type;
        this.contactName = contactName;


    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", contactName='" + contactName + '\'' +
                ", type=" + type +
                ", customerId=" + customerId +
                '}';
    }
}
