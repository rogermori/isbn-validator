package com.training.isbntools;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ISBNValidatorTest {

    @Test
    public void checkValidISBN() {
        ISBNValidator isbnValidator = new ISBNValidator();
        String isbn = "0140449116";
        boolean valid = isbnValidator.validateISBN(isbn);
        assertTrue(valid,isbn);
        isbn = "1449509827";
        valid = isbnValidator.validateISBN(isbn);
        assertTrue(valid, isbn);
    }

    @Test
    public void checkValid13DigitsISBN() {
        ISBNValidator isbnValidator = new ISBNValidator();
        String isbn = "9781449509828";
        boolean valid = isbnValidator.validateISBN(isbn);
        assertTrue(valid,isbn);
    }

    @Test
    public void checkInvalidISBN() {
        ISBNValidator isbnValidator = new ISBNValidator();
        boolean valid = isbnValidator.validateISBN("0140449117");
        assertFalse(valid);
    }

    @Test()
    public void nineDigitsISBNNotAllowed() {
        ISBNValidator isbnValidator = new ISBNValidator();
        assertThrows(NumberFormatException.class,() -> {
            isbnValidator.validateISBN("123456789");
        });
    }

    @Test
    public void nonNumericsISBNNotAllowed() {
        ISBNValidator isbnValidator = new ISBNValidator();
        assertThrows(NumberFormatException.class,() -> {
            isbnValidator.validateISBN("helloworld");
        });
    }

    @Test
    public void ISBNNumbersEndingInXAreValid() {
        ISBNValidator isbnValidator = new ISBNValidator();
        boolean valid = isbnValidator.validateISBN("012000030X");
        assertFalse(valid);
    }


}
