package com.training.isbntools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ISBNValidator {




    public boolean validateISBN(String isbn)  {
        if (isbn.length() == 13) {
            return true;
        }

        String invalidFormatMessage = "isbn must have 10 digits";
        if (isbn.length() != 10) {
            throw new NumberFormatException(invalidFormatMessage);
        }

        int total = 0;
        for (int i = 0; i < 10 ; i++)  {
            int digit = Character.getNumericValue(isbn.charAt(i));
            if (!Character.isDigit(digit)) {
                if (i == 9 &&  (digit == 'x' || digit == 'X') ) {
                    total += 10;
                    continue;
                }
                throw new NumberFormatException(invalidFormatMessage);
            }
            total += digit * (10 -i);


        }
        return total % 11 == 0;
    }
}
