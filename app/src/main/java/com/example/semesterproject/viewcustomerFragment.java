package com.example.semesterproject;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.Toast;


public class viewcustomerFragment extends Fragment {


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
TableLayout viewcustomer;
    public viewcustomerFragment() {
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
       View view= inflater.inflate(R.layout.fragment_viewcustomer, container, false);
       viewcustomer = view.findViewById(R.id.customer_table);
        SQLiteOpenHelper dbHelper = new posdatabasehelper(this.getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS customer (" +
                "    cust_id    INTEGER PRIMARY KEY AUTOINCREMENT," +
                "    cust_name  TEXT," +
                "    cust_phone TEXT" +
                ");");
        String sql = "SELECT * FROM customer;";
        try {
            db.execSQL(sql);
            Toast.makeText(getActivity(), "Customer added successfully", Toast.LENGTH_SHORT).show();

        } catch (SQLException e) {
            Toast.makeText(getActivity(), "Error inserting customer: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {


            db.close();
        }
       return view;
    }
}