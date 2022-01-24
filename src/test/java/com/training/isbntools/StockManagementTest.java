package com.training.isbntools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StockManagementTest {

    @Test
    public void canGetACorrectLocatorCode() {
        ExternalISBNDataService testService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return new Book(isbn,"Of Mice And Men", "J. Steinbeck");
            }
        };


        String isbn = "0140177396";
        StockManager stockManager = new StockManager();
        stockManager.setService(testService);
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);
    }
}
