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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Spinner spinner;
    private posdatabasehelper dbhelper;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public String id;
    Button search;


    private Spinner spinnersearch;
    private EditText editTextName, editTextContact;
    private Button searchButton;

    private SQLiteDatabase db;
    public updatecustomerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment updatecustomerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static updatecustomerFragment newInstance(String param1, String param2) {
        updatecustomerFragment fragment = new updatecustomerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
        List<String> customerIds = fetchDataFromDatabase();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, customerIds);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    id = (String) spinner.getSelectedItem();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchCustomer();

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

    private void searchCustomer() {
        String selectedCustomerId = (String) spinner.getSelectedItem();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursorr = db.rawQuery("SELECT cust_name, cust_phone FROM customer WHERE cust_id = ?", new String[]{selectedCustomerId});
        if (cursorr.moveToFirst()) {
            String customerName = cursorr.getString(cursorr.getColumnIndexOrThrow("cust_name"));
            String customerContact = cursorr.getString(cursorr.getColumnIndexOrThrow("cust_phone"));
            // Populate the EditText fields with the retrieved customer data
            editTextName.setText(customerName);
            editTextContact.setText(customerContact);
            Toast.makeText(this.getActivity(), customerName.toString(), Toast.LENGTH_SHORT).show();
        }
        cursorr.close();
        db.close();
    }
 }