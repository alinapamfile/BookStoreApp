package com.example.android.bookstoreapp.data;

import android.provider.BaseColumns;

/**
 * API Contract for the BookStoreApp.
 */

public class BookContract {

    /**
     * Empty constructor
     */
    private BookContract() {
    }


    /**
     * Inner class that defines constant values for the books database table.
     * Each entry in the table represents a single book.
     */
    public static abstract class BookEntry implements BaseColumns {

        /**
         * Name of database table for books
         */
        public static final String TABLE_NAME = "books";


        /**
         * Unique ID number for the book (only for use in the database table).
         * Type: INTEGER
         */
        public static final String _ID = BaseColumns._ID;

        /**
         * Name of the pet.
         * Type: TEXT
         */
        public static final String COLUMN_BOOK_NAME = "name";

        /**
         * Price of the book.
         * Type: REAL
         */
        public static final String COLUMN_BOOK_PRICE = "price";

        /**
         * Quantity of the book.
         * Type: INTEGER
         */
        public static final String COLUMN_BOOK_QUANTITY = "quantity";

        /**
         * Supplier of the book.
         * Type: TEXT
         */
        public static final String COLUMN_BOOK_SUPPLIER = "supplier";

        /**
         * Supplier's phone number.
         * Type: TEXT
         */
        public static final String COLUMN_BOOK_SUPPLIER_PHONE_NUMBER = "phone_number";

    }
}
