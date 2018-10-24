package com.example.contacts.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private String city;
    @XmlElement(name="contacts")
    private XMLcontacts xmLcontacts = null;
    private List<Contact> contacts;


    public Customer() {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.age = age;
        this.city = city;
        this.contacts = contacts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public XMLcontacts getXmLcontacts() {
        return xmLcontacts;
    }

    public void setXmLcontacts(XMLcontacts xmLcontacts) {
        this.xmLcontacts = xmLcontacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", xmLcontacts=" + xmLcontacts +
                ", contacts=" + contacts +
                '}';
    }
}
