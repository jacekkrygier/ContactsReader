package com.example.contacts.utils;

import com.example.contacts.mapper.ContactChecker;
import com.example.contacts.model.Contact;
import com.example.contacts.model.Customer;
import com.example.contacts.model.Customers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CommonUtils {


    public static String getFileExtension(String name) {
        if(name.lastIndexOf(".") != -1 && name.lastIndexOf(".") != 0) {
            return name.substring(name.lastIndexOf(".") + 1);
        } else {
            return ("");
        }
    }

    /**
     * read xml file
     * read csv file
     * @param filePath
     * @return
     */

    public static List<Customer> readCsv(String filePath) {

        List<Customer> list = new ArrayList<Customer>();
        BufferedReader buff = null;
        String line = "";
        String splitBy = ",";

        try {
            buff = new BufferedReader(new FileReader(filePath));
            while((line = buff.readLine()) !=null) {
                String[] data = line.split(splitBy);
                Customer customer = new Customer();
                customer.setName(data[0]);
                customer.setSurname(data[1]);
                try {customer.setAge(Integer.valueOf(data[2]));
                } catch (NumberFormatException e) {
                    System.out.println("Customer " + customer.getName() +" " + customer.getSurname() +" has an unknown age");
                };
                customer.setCity(data[3]);
                List<Contact> contactsList= new ArrayList<Contact>();
                ContactChecker contactChecker = new ContactChecker();
                for(int i = 4; i < data.length; i++) {
                    Contact contact = new Contact(contactChecker.checkType(data[i]), data[i]);
                    contactsList.add(contact);
                }
                customer.setContacts(contactsList);
                list.add(customer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(buff !=null) {
                try {
                    buff.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static List<Customer> readXml(String filePath) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Customers.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        Customers customers = (Customers) unmarshaller.unmarshal(new File(filePath));

        return customers.getCustomers();
    }
}
