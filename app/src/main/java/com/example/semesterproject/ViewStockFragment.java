package com.example.semesterproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewStockFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewStockFragment extends Fragment {

TableLayout tableLayout;
posdatabasehelper dbhelper;
SQLiteDatabase db;

    public ViewStockFragment() {
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
        View view = inflater.inflate(R.layout.fragment_view_stock, container, false);
        tableLayout = view.findViewById(R.id.tableLayoutstock);
        dbhelper = new posdatabasehelper(this.getActivity());
        db = dbhelper.getReadableDatabase();
        fetchSupplierData();
        return view;

    }
    private void fetchSupplierData() {
        Cursor cursor = db.rawQuery("SELECT supplier_id, supplier_name, supplier_contact FROM supplier", null);
        TableRow headerRow = new TableRow(this.getActivity());

        TextView headerId = createHeaderTextView("ID");
        TextView headerName = createHeaderTextView("Name");
        TextView headerContact = createHeaderTextView("Contact");

        headerRow.addView(headerId);
        headerRow.addView(headerName);
        headerRow.addView(headerContact);

        tableLayout.addView(headerRow);
        if (cursor.moveToFirst()) {
            do {
                String supplierId = cursor.getString(cursor.getColumnIndex("supplier_id"));
                String supplierName = cursor.getString(1);
                String supplierPhone = cursor.getString(2);

                TableRow tableRow = new TableRow(this.getActivity());



                TextView textViewId = createTextView(supplierId);
                TextView textViewName = createTextView(supplierName);
                TextView textViewPhone = createTextView(supplierPhone);

                tableRow.addView(textViewId);
                tableRow.addView(textViewName);
                tableRow.addView(textViewPhone);

                tableLayout.addView(tableRow);
            } while (cursor.moveToNext());
        }

        cursor.close();
    }

    private TextView createTextView(String text) {
        TextView textView = new TextView(this.getActivity());
        textView.setText(text);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(10, 10, 10, 10);
        return textView;
    }
    private TextView createHeaderTextView(String text) {
        TextView textView = createTextView(text);
        textView.setAllCaps(true);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}