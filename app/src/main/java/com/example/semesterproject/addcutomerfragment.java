package com.example.semesterproject;

import android.database.SQLException;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class addcutomerfragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText cust_name, cust_phone;

    public Button addcust;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public int rowsAffected;
    private posdatabasehelper dphelper;

    public addcutomerfragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dphelper = new posdatabasehelper(this.getActivity());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_addcutomerfragment, container, false);
        cust_name = view.findViewById(R.id.EditTextcust_name);
        cust_phone = view.findViewById(R.id.EditTextcust_phone);
        addcust = view.findViewById(R.id.AddCustomer);
        addcust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customerName = cust_name.getText().toString();
                Editable customerPhone = cust_phone.getText();
                insertCustomer(customerName, customerPhone);
                //Toast.makeText(getActivity(), "Customer added successfully", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void insertCustomer(String customerName, Editable customerPhone) {
        SQLiteOpenHelper dbHelper = new posdatabasehelper(this.getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS customer (" +
                "    cust_id    INTEGER PRIMARY KEY AUTOINCREMENT," +
                "    cust_name  TEXT," +
                "    cust_phone TEXT" +
                ");");
        String sql = "INSERT INTO customer(cust_name,cust_phone) VALUES (?, ?)";
        Object[] bindArgs = {customerName, customerPhone};
        try {
            db.execSQL(sql, bindArgs);
            Toast.makeText(getActivity(), "Customer added successfully", Toast.LENGTH_SHORT).show();

        } catch (SQLException e) {
            Toast.makeText(getActivity(), "Error inserting customer: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {

            db.close();
        }

    }

}