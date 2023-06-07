package com.example.semesterproject;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link updatecustomerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class updatecustomerFragment extends Fragment {


    private Spinner spinner;
    private posdatabasehelper dbhelper;

    public String id;
    Button search, update;

    private EditText editTextName, editTextContact;

    private SQLiteDatabase db;
    ArrayList<String> customerNames, customerPhoneNumbers;
    ArrayList<Integer> customerIDs;

    public updatecustomerFragment() {
        // Required empty public constructor
    }
    private void searchCustomer() {
        //declaring arraylists
        customerIDs = new ArrayList<>();
        customerNames = new ArrayList<>();
        customerPhoneNumbers = new ArrayList<>();


        customerIDs.add(null);
        customerNames.add("Customer Name");
        customerPhoneNumbers.add("Contact Number");
        String selectedCustomerId = (String) spinner.getSelectedItem();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor customer_detail = db.rawQuery("SELECT cust_id, cust_name, cust_phone FROM customer WHERE cust_id = ?", new String[]{selectedCustomerId});
        if (customer_detail.moveToFirst()) {

            customerIDs.add(customer_detail.getInt(0));
            customerNames.add(customer_detail.getString(1));
            customerPhoneNumbers.add(customer_detail.getString(2));
        }

    }
    private void updatecustomer() {
    String updated_name =editTextName.getText().toString();
    String updated_contact = editTextContact.getText().toString();
        String selectedCustomerId = (String) spinner.getSelectedItem();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        db.execSQL("UPDATE customer SET cust_name = '" + updated_name + "', cust_phone = '" + updated_contact + "' WHERE cust_id = " + id);
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
       View view =inflater.inflate(R.layout.fragment_updatecustomer, container, false);
        dbhelper = new posdatabasehelper(this.getActivity());
        spinner = view.findViewById(R.id.CustomerIdInUpdateFragmment);
         search = view.findViewById(R.id.SearchCustomerButtonInUpdateCustFragment);
        editTextName  = view.findViewById(R.id.CustomerNameInUpdateFragment);
        editTextContact = view.findViewById(R.id.ContactNumberInUpdateFragment);
        update = view.findViewById(R.id.UPDATECUSTOMER);
        List<String> customerIds = fetchDataFromDatabase();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, customerIds);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    id = (String) spinner.getSelectedItem();
        Toast.makeText(this.getActivity(), id, Toast.LENGTH_SHORT).show();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchCustomer();
              //   edtTextIDsearched.setText(customerIDs.get(1));
editTextName.setText(customerNames.get(1));
editTextContact.setText(customerPhoneNumbers.get(1));

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
updatecustomer();
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