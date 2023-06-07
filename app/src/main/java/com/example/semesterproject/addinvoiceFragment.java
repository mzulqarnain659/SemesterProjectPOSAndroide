package com.example.semesterproject;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class addinvoiceFragment extends Fragment {
    EditText invoicedate, payment_status;
    Spinner customer_id;
    public Button addinvoice;
    public String id;
    private posdatabasehelper dbhelper;
String invoice_date, paymentstatus;


       private SQLiteDatabase db;
    public addinvoiceFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view =inflater.inflate(R.layout.fragment_addinvoice, container, false);
      dbhelper = new posdatabasehelper(this.getActivity());
      db = dbhelper.getReadableDatabase();
      invoicedate = view.findViewById(R.id.invoice_date);
      payment_status = view.findViewById(R.id.payment_status);
      customer_id = view.findViewById(R.id.cust_id_in_invoice);
      addinvoice = view.findViewById(R.id.ADDINVOICE);
        List<String> customerIds = fetchDataFromDatabase();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, customerIds);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        customer_id.setAdapter(adapter);
        id = (String) customer_id.getSelectedItem();
        invoice_date =invoicedate.getText().toString();
        paymentstatus = payment_status.getText().toString();


db.close();
addinvoice.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

db = dbhelper.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS invoice (" +
                "    inv_id         INTEGER PRIMARY KEY AUTOINCREMENT," +
                "    cust_id        INTEGER," +
                "    inv_date       TEXT," +
                "    payment_status TEXT" +
                ");");
        String sql = "INSERT INTO invoice (cust_id,inv_date, payment_status) VALUES (?, ?,?)";
        Object[] bindArgs = {id, invoice_date, paymentstatus};
        try {

            db.execSQL(sql, bindArgs);
            Toast.makeText(getActivity(), "invoice added successfully", Toast.LENGTH_SHORT).show();

        } catch (SQLException e) {
            Toast.makeText(getActivity(), "Error inserting customer: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {


            db.close();
        }

    }
});

      return view;
    }
    private List<String> fetchDataFromDatabase() {
        List<String> customerIds = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        // Query the database and retrieve customer IDs
        Cursor cursor = db.rawQuery("SELECT cust_id FROM customer", null);
        if (cursor.moveToFirst()) {
            do {
                String customerId = cursor.getString(cursor.getColumnIndex("cust_id"));
                customerIds.add(customerId);
            } while (cursor.moveToNext());
        }

        // Close the cursor and database
        cursor.close();


        return customerIds;
    }

}