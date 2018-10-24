package com.example.contacts.dao;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface CustomerDao {
    public void process(List<String> filesPath) throws JAXBException;
}
