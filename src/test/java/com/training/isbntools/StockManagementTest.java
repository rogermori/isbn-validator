package com.training.isbntools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StockManagementTest {

    private ExternalISBNDataService webService;
    private ExternalISBNDataService databaseService;
    private StockManager stockManager;

    @BeforeEach
    public void beforeEachTest() {
        webService = mock(ExternalISBNDataService.class);
        databaseService = mock(ExternalISBNDataService.class);
        stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);
    }

    @Test
    public void canGetACorrectLocatorCode() {
        String isbn = "0140177396";
        when(webService.lookup(anyString())).thenReturn(new Book(isbn, "Of Mice And Men", "J. Steinbeck"));
        when(databaseService.lookup(anyString())).thenReturn(null);
        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);
    }

    @Test
    public void databaseIsUsedIfDataIsPresent() {
        String isbn = "0140177396";
        when(databaseService.lookup(isbn)).thenReturn(new Book(isbn, "abc", "abc"));
        String locatorCode = stockManager.getLocatorCode(isbn);
        verify(databaseService).lookup(isbn);
        verify(webService, never()).lookup(anyString());
    }

    @Test
    public void webserviceIsUsedIfDataIsNotPresentInDatabase() {
        String isbn = "0140177396";
        when(databaseService.lookup(isbn)).thenReturn(null);
        when(webService.lookup(isbn)).thenReturn(new Book(isbn, "abc", "abc"));
        String locatorCode = stockManager.getLocatorCode(isbn);
        verify(databaseService, times(1)).lookup(isbn);
        verify(webService, times(1)).lookup(isbn);
    }
}
