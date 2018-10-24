package com.example.contacts.service;

import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface CustomerService {
    public void process(List<String> filePath) throws JAXBException;
}
