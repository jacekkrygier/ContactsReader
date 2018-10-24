package com.example.contacts.mapper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContactCheckerTest {

    @Test
    public void testCheckType() {
        //Given
        ContactChecker contactChecker = new ContactChecker();
        String jacek = "Jacek";
        String phone = "783939699";
        String jabber = "jabber";
        String mail = "jacek@java.com";

        //When
        int test1 = contactChecker.checkType(jacek);
        int test2 = contactChecker.checkType(phone);
        int test3 = contactChecker.checkType(jabber);
        int test4 = contactChecker.checkType(mail);

        //Then
        assertEquals(0, test1);
        assertEquals(2, test2);
        assertEquals(3, test3);
        assertEquals(1, test4);
    }

}