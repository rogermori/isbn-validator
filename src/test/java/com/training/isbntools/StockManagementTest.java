package com.training.isbntools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class StockManagementTest {

    @Test
    public void canGetACorrectLocatorCode() {
        ExternalISBNDataService testWebService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return new Book(isbn,"Of Mice And Men", "J. Steinbeck");
            }
        };

        ExternalISBNDataService testDBService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return null;
            }
        };


        String isbn = "0140177396";
        StockManager stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDBService);
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);
    }

    @Test
    public void databaseIsUsedIfDataIsPresent() {
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);
        String isbn = "0140177396";
        when(databaseService.lookup(isbn)).thenReturn(new Book(isbn,"abc", "abc"));
        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);
        String locatorCode = stockManager.getLocatorCode(isbn);
        verify(databaseService).lookup(isbn);
        verify(webService,never()).lookup(anyString());
    }

    @Test
    public void webserviceIsUsedIfDataIsNotPresentInDatabase(){
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);
        String isbn = "0140177396";
        when(databaseService.lookup(isbn)).thenReturn(null);
        when(webService.lookup(isbn)).thenReturn(new Book(isbn,"abc", "abc"));
        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);
        String locatorCode = stockManager.getLocatorCode(isbn);
        verify(databaseService,times(1)).lookup(isbn);
        verify(webService,times(1)).lookup(isbn);
    }
}
