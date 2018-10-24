package com.example.contacts.service;

import com.example.contacts.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.List;

@Service
public class CustomerServiceClass implements CustomerService {

    @Autowired
    CustomerDao customerDao;
    @Override
    public void process(List<String> filePath) throws JAXBException {
        customerDao.process(filePath);

    }
}
