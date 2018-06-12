package com.example.android.bookstoreapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bookstoreapp.data.BookContract.BookEntry;
import com.example.android.bookstoreapp.data.BookDbHelper;

import org.w3c.dom.Text;

/**
 * Allows user to create a new book.
 */
public class EditorActivity extends AppCompatActivity {

    /**
     * EditText field to enter the book's name
     */
    private EditText mNameEditText;

    /**
     * EditText field to enter the book's price
     */
    private EditText mPriceEditText;

    /**
     * Quantity of the book, which is initially 0
     */
    private int quantity = 0;
    /**
     * TextView that shows the user what quantity was chosen
     */
    private TextView mQuantityTextView;

    /**
     * EditText field to enter the book's supplier name
     */
    private EditText mSupplierEditText;

    /**
     * EditText field to enter the book's supplier phone number
     */
    private EditText mPhoneNumberEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all relevant views that we will need to read user input from
        mNameEditText = (EditText) findViewById(R.id.edit_book_name);
        mPriceEditText = (EditText) findViewById(R.id.edit_book_price);
        mQuantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        mSupplierEditText = (EditText) findViewById(R.id.edit_book_supplier);
        mPhoneNumberEditText = (EditText) findViewById(R.id.edit_book_phone_number);

        /** On-click listener for the increment button that adds 1 to the quantity
         * and displays it when the user clicks on it */
        final Button incrementButton = (Button) findViewById(R.id.increment_button);
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity = quantity + 1;
                mQuantityTextView.setText(String.valueOf(quantity));
            }
        });

        /** On-click listener for the decrement button that subtracts 1 from the quantity
         * and displays it when the user clicks on it */
        final Button decrementButon = (Button) findViewById(R.id.decrement_button);
        decrementButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** The quantity can't be negative */
                if (quantity > 0) {
                    quantity = quantity - 1;
                    mQuantityTextView.setText(String.valueOf(quantity));
                }
            }
        });
    }

    /**
     * Get user input from editor and save new book into database.
     */
    private void insertBook() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String nameString = mNameEditText.getText().toString().trim();
        float priceFloat = Float.parseFloat(mPriceEditText.getText().toString());
        String supplierString = mSupplierEditText.getText().toString().trim();
        String phoneNumberString = mPhoneNumberEditText.getText().toString().trim();

        // Create database helper
        BookDbHelper mDbHelper = new BookDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and book attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(BookEntry.COLUMN_BOOK_NAME, nameString);
        values.put(BookEntry.COLUMN_BOOK_PRICE, priceFloat);
        values.put(BookEntry.COLUMN_BOOK_QUANTITY, quantity);
        values.put(BookEntry.COLUMN_BOOK_SUPPLIER, supplierString);
        values.put(BookEntry.COLUMN_BOOK_SUPPLIER_PHONE_NUMBER, phoneNumberString);

        // Insert a new row for book in the database, returning the ID of that new row.
        long newRowId = db.insert(BookEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId != -1)
            Toast.makeText(this, "Book saved with id: " + newRowId, Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Error with saving book", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                //Save pet to database
                insertBook();
                //Exit activity
                finish();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
