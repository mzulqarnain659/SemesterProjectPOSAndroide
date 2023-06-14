package com.example.semesterproject;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


public class listviewhome extends Fragment {

ListView homelistview;

    public listviewhome() {


    }
    String[] productName ={"Customer","Invoice","Supplier","Stock", "Report"};
    int[] productImages = { R.drawable.customer ,R.drawable.invoice ,R.drawable.supplier ,R.drawable.stock,R.drawable.report };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listviewhome, container, false);
        homelistview = view.findViewById(R.id.home_listview);
        ListViewInflator listViewInflator = new ListViewInflator(this.getContext(),productImages, productName, R.layout.homelayoutsamplelistview);


        homelistview.setAdapter(listViewInflator);
        homelistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(productName[i] == "Customer"){
                    Intent switchNow = new Intent(getActivity(),addcustomer.class);
                    startActivity(switchNow);
                } else if (productName[i] == "Invoice") {
                    Intent switchNow = new Intent(getActivity(),addinvoice.class);
                    startActivity(switchNow);
                }else if(productName[i] == "Supplier"){
                    Intent switchNow = new Intent(getActivity(),addsupplier.class);
                    startActivity(switchNow);
                }else if(productName[i]=="Stock"){
                    Intent switchNow = new Intent(getActivity(),stock.class);
                    startActivity(switchNow);
                }else if (productName[i]=="Report"){
                    Intent switchNow = new Intent(getActivity(),report.class);
                    startActivity(switchNow);
                }

            }
        });

        return view;
    }
}