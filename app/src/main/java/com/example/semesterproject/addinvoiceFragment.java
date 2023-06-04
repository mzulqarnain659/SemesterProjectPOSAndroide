package com.example.semesterproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class addinvoiceFragment extends Fragment {
    EditText invoicedate, payment_status;
    Spinner customer_id;
    public Button addinvoice;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;


    public addinvoiceFragment() {
        // Required empty public constructor
    }

    public static addinvoiceFragment newInstance(String param1, String param2) {
        addinvoiceFragment fragment = new addinvoiceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
      invoicedate = view.findViewById(R.id.invoice_date);
      payment_status = view.findViewById(R.id.payment_status);
      customer_id = view.findViewById(R.id.cust_id_in_invoice);
      addinvoice = view.findViewById(R.id.ADDINVOICE);


      return view;
    }
}