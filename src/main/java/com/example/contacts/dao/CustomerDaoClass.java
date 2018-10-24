package com.example.contacts.dao;

import com.example.contacts.mapper.ContactChecker;
import com.example.contacts.model.Contact;
import com.example.contacts.model.Customer;
import com.example.contacts.service.DbManager;
import com.example.contacts.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerDaoClass implements CustomerDao {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void process(List<String> filesPath) throws JAXBException{
        List<Customer> list = new ArrayList<Customer>();

        //read data
        for(String filePath : filesPath) {
            if(CommonUtils.getFileExtension(filePath).equals("csv") || CommonUtils.getFileExtension(filePath).equals("txt")) {
                //read csv file or txt file
                list.addAll(CommonUtils.readCsv(filePath));
            } else if(CommonUtils.getFileExtension(filePath).equals("xml")) {
                //read xml file
                list.addAll(CommonUtils.readXml(filePath));

                for (Customer customer:
                        list) {List<Contact> contactList= new ArrayList<Contact>();
                    ContactChecker contactChecker = new ContactChecker();
                    try {for (String string:customer.getXmLcontacts().getEmail()
                         ) {Contact contact = new Contact(contactChecker.checkType(string), string);
                        contactList.add(contact);
                    }} catch(NullPointerException e) {}
                    try {for (String string:customer.getXmLcontacts().getIcq()
                            ) {Contact contact = new Contact(contactChecker.checkType(string), string);
                        contactList.add(contact);
                    }} catch(NullPointerException e) {}
                    try {for (String string:customer.getXmLcontacts().getJabber()
                            ) {Contact contact = new Contact(contactChecker.checkType(string), string);
                        contactList.add(contact);
                    }} catch(NullPointerException e) {}
                    try {for (String string:customer.getXmLcontacts().getPhone()
                            ) {Contact contact = new Contact(contactChecker.checkType(string), string);
                        contactList.add(contact);
                    }} catch(NullPointerException e) {}
                    customer.setContacts(contactList);
                }
            }
        }
        //import data;
        try {
            importData(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void importData(List<Customer> list) throws SQLException {
        String sql = "INSERT INTO CUSTOMERS(id, name, surname, age) VALUES (:id, :name, :surname, :age)";
        String sql2 = "INSERT INTO CONTACTS(id, customerId, type, contactName) VALUES (:id, :customerId, :type, :contactName) ";



        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(list.toArray());
        namedParameterJdbcTemplate.batchUpdate(sql, batch);
        DbManager dbManager = DbManager.getInstance();
        for (Customer customer:
             list) {
            String sql3 = "SELECT ID FROM CUSTOMERS WHERE (NAME = '" + customer.getName() + "' and SURNAME = '" + customer.getSurname() +"')";
            Statement statement = dbManager.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql3);
            rs.next();
            Integer id = rs.getInt("ID");
            rs.close();
            statement.close();
            for (Contact contact :
                    customer.getContacts()) {contact.setCustomerId(id);
            };
            List <Contact> list2 = customer.getContacts();
            SqlParameterSource[] batch2 = SqlParameterSourceUtils.createBatch(list2.toArray());
            namedParameterJdbcTemplate.batchUpdate(sql2, batch2);
        }
    }
}
