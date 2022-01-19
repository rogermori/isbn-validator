package com.training.isbntools;


public class ISBNValidator {


    public static final int LONG_ISBN_LENGTH = 13;
    public static final int SHORT_ISBN_LENGTH = 10;

    public static final int SHORT_ISBN_MULTIPLIER = 11;
    public static final int LONG_ISBN_MULTIPLIER = 10;

    public boolean validateLongISBN(String isbn) {
        int total = 0;
        String invalidFormatMessage = "isbn must have 13 digits";
        for (int i = 0; i < LONG_ISBN_LENGTH; i++)  {
            int digit = Integer.valueOf(isbn.charAt(i));
            /*
            if (!Character.isDigit(digit)) {
                if (i == 9 &&  (digit == 'x' || digit == 'X') ) {
                    total += 10;
                    continue;
                }
                throw new NumberFormatException(invalidFormatMessage);
            }
            */
            if ( (i+1) % 2 != 0 ) {
                total += digit ;
            } else {
                total += digit * 3;
            }
        }
        return total % LONG_ISBN_MULTIPLIER == 0;
    }

    public boolean validateISBN(String isbn)  {
        if (isbn.length() == LONG_ISBN_LENGTH) {
            return validateLongISBN(isbn);
        } else if (isbn.length() == SHORT_ISBN_LENGTH) {
            return validateShortISBN(isbn);
        }
        String invalidFormatMessage = "isbn must have 10 or 13 digits";
        throw new NumberFormatException(invalidFormatMessage);
    }

    private boolean validateShortISBN(String isbn) {
        String invalidFormatMessage = "isbn must have 10 digits";
        int total = 0;
        for (int i = 0; i < SHORT_ISBN_LENGTH; i++)  {
            int digit = Integer.valueOf(isbn.charAt(i));
            if (!Character.isDigit(digit)) {
                if (i == 9 &&  (digit == 'x' || digit == 'X') ) {
                    total += SHORT_ISBN_LENGTH;
                    continue;
                }
                throw new NumberFormatException(invalidFormatMessage);
            }
            total += digit * (SHORT_ISBN_LENGTH -i);
        }
        return total % SHORT_ISBN_MULTIPLIER == 0;
    }
}
