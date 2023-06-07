package com.example.semesterproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

public class UpdateSupplierFragment extends Fragment {

    Button search, update;
    String id;

    private Spinner spinner;
    private posdatabasehelper dbhelper;
    private EditText editTextName, editTextContact;

    private SQLiteDatabase db;
    ArrayList<String> supplierNames, supplierPhoneNumbers;
    ArrayList<Integer> supplierIDs;

    public UpdateSupplierFragment() {
        // Required empty public constructor
    }

    private void searchSupplier() {
        //declaring arraylists
        supplierIDs = new ArrayList<>();
        supplierNames = new ArrayList<>();
        supplierPhoneNumbers = new ArrayList<>();

        //adding null values to iterate one more time before actually inflating the grid for
        //header row
        supplierIDs.add(null);
        supplierNames.add("Supplier Name");
        supplierPhoneNumbers.add("Contact Number");
        String selectedsupplierId = (String) spinner.getSelectedItem();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor customer_detail = db.rawQuery("SELECT supplier_id, supplier_name, supplier_contact FROM supplier WHERE supplier_id = ?", new String[]{selectedsupplierId});
        if (customer_detail.moveToFirst()) {

            supplierIDs.add(customer_detail.getInt(0));
            supplierNames.add(customer_detail.getString(1));
            supplierPhoneNumbers.add(customer_detail.getString(2));
        }

    }
    private void updatesupplier() {
        String updated_name =editTextName.getText().toString();
        String updated_contact = editTextContact.getText().toString();
        String selectedCustomerId = (String) spinner.getSelectedItem();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        db.execSQL("UPDATE supplier SET supplier_name = '" + updated_name + "', supplier_contact = '" + updated_contact + "' WHERE supplier_id = " + id);
        Toast.makeText(this.getActivity(), "Updated Successfully", Toast.LENGTH_SHORT).show();
        editTextName.setText("");
        editTextContact.setText("");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_update_supplier, container, false);
        dbhelper = new posdatabasehelper(this.getActivity());
        spinner = view.findViewById(R.id.SupplierIdInUpdateFragmment);
        search = view.findViewById(R.id.SearchSupplierButtonInUpdateSupplierFragment);
        editTextName  = view.findViewById(R.id.SupplierNameInUpdateFragment);
        editTextContact = view.findViewById(R.id.ContactNumberInUpdateFragment);
        update = view.findViewById(R.id.UPDATESPPLIER);
        List<String> supplierIds = fetchDataFromDatabase();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, supplierIds);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        id = (String) spinner.getSelectedItem();
        Toast.makeText(this.getActivity(), id, Toast.LENGTH_SHORT).show();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchSupplier();
                //   edtTextIDsearched.setText(customerIDs.get(1));
                editTextName.setText(supplierNames.get(1));
                editTextContact.setText(supplierPhoneNumbers.get(1));

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatesupplier();
            }
        });

        return view;
    }
    private List<String> fetchDataFromDatabase() {
        List<String> supplierIds = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        // Query the database and retrieve customer IDs
        Cursor cursor = db.rawQuery("SELECT supplier_id FROM supplier", null);
        if (cursor.moveToFirst()) {
            do {
                String customerId = cursor.getString(cursor.getColumnIndex("supplier_id"));
                supplierIds.add(customerId);
            } while (cursor.moveToNext());
        }

        // Close the cursor and database
        cursor.close();


        return supplierIds;
    }


}
