package com.example.semesterproject;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddSupplierFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddSupplierFragment extends Fragment {

    EditText sup_name, sup_phone;

    public Button addsup;
    public AddSupplierFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_supplier, container, false);
        sup_name = view.findViewById(R.id.supplier_name);
        sup_phone = view.findViewById(R.id.supplier_phone);
        addsup = view.findViewById(R.id.addSupplierButton);
        addsup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String supplierName = sup_name.getText().toString();
                String supplierPhone = sup_phone.getText().toString();
                insertSupplier(supplierName, supplierPhone);
                //Toast.makeText(getActivity(), "Customer added successfully", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    private void insertSupplier(String suppliername, String supplierphone) {
        SQLiteOpenHelper dbHelper = new posdatabasehelper(this.getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS supplier (" +
                "    supplier_id      INTEGER PRIMARY KEY AUTOINCREMENT," +
                "    supplier_name    TEXT," +
                "    supplier_contact TEXT" +
                ");");
        String sql = "INSERT INTO supplier(supplier_name,supplier_contact) VALUES (?, ?)";
        Object[] bindArgs = {suppliername, supplierphone};
        try {
            db.execSQL(sql, bindArgs);
            Toast.makeText(getActivity(), "Supplier added successfully", Toast.LENGTH_SHORT).show();

        } catch (SQLException e) {
            Toast.makeText(getActivity(), "Error inserting Supplier: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {


            db.close();
        }

    }

}