package com.example.contacts.service;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;


public class DbManagerTest {
    @Test
    public void testConnect() throws SQLException {
        //Given
        //When
        DbManager dbManager = DbManager.getInstance();
        //Then
        Assert.assertNotNull(dbManager.getConnection());
    }
}