package com.training.isbntools;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ISBNValidatorTest {

    @Test
    public void checkValidISBN() {
        ISBNValidator isbnValidator = new ISBNValidator();
        boolean valid = isbnValidator.validateISBN(140449116);
        assertTrue(valid);
    }

    @Test
    public void checkInvalidISBN() {
        ISBNValidator isbnValidator = new ISBNValidator();
        boolean valid = isbnValidator.validateISBN(140449117);
        assertFalse(valid);
    }


}
