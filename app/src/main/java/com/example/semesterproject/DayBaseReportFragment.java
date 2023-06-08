package com.example.semesterproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DayBaseReportFragment extends Fragment {
TableLayout tb;
posdatabasehelper dbhelper;
SQLiteDatabase db;
EditText date;

Button report,clear;

    public DayBaseReportFragment() {
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
        View view=  inflater.inflate(R.layout.fragment_day_base_report, container, false);
      date = view.findViewById(R.id.daydate);
      report = view.findViewById(R.id.searchdayreport);
      clear = view.findViewById(R.id.clearday);
        tb = view.findViewById(R.id.tableLayoutofdayreport);
        dbhelper = new posdatabasehelper(this.getActivity());
        db = dbhelper.getReadableDatabase();
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fetchSupplierData();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               tb.removeAllViews();
            }
        });

   return view;
    }
    private void fetchSupplierData() {
        Cursor cursor = db.rawQuery("SELECT prod_id, inv_id, cust_id, itm_name, itm_quantity, itm_total_price FROM invoice_item WHERE date_of_addition = '"+ date.getText() +"'", null);
        TableRow headerRow = new TableRow(this.getActivity());

        TextView headerId = createHeaderTextView("Product");
        TextView headerInvoice = createHeaderTextView("invoice");
        TextView headerCustomer = createHeaderTextView("Customer");
        TextView headerNae = createHeaderTextView("Name");
        TextView headerQuantity = createHeaderTextView("Quantity");
        TextView headerPrice = createHeaderTextView("Price");

        headerRow.addView(headerId);
        headerRow.addView(headerInvoice);
        headerRow.addView(headerCustomer);

        headerRow.addView(headerNae);
        headerRow.addView(headerQuantity);
        headerRow.addView(headerPrice);

        tb.addView(headerRow);
        if (cursor.moveToFirst()) {
            do {
                String productId = cursor.getString(0);
                String invoice = cursor.getString(1);
                String customer = cursor.getString(2);
                String Name = cursor.getString(3);
                String quantity = cursor.getString(4);
                String price = cursor.getString(5);
                TableRow tableRow = new TableRow(this.getActivity());



                TextView textViewpId = createTextView(productId);
                TextView textViewinvid = createTextView(invoice);
                TextView textViewcid = createTextView(customer);
                TextView textViewname = createTextView(Name);
                TextView textViewquan = createTextView(quantity);
                TextView textViewPrice = createTextView(price);
                tableRow.addView(textViewpId);
                tableRow.addView(textViewinvid);
                tableRow.addView(textViewcid);
                tableRow.addView(textViewname);
                tableRow.addView(textViewquan);
                tableRow.addView(textViewPrice);
                tb.addView(tableRow);
            } while (cursor.moveToNext());
        }

        cursor.close();
    }

    private TextView createTextView(String text) {
        TextView textView = new TextView(this.getActivity());
        textView.setText(text);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(2, 10, 2, 10);
        return textView;
    }
    private TextView createHeaderTextView(String text) {
        TextView textView = createTextView(text);
        textView.setAllCaps(true);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}