package com.example.semesterproject;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


public class homegridview extends Fragment {

    GridView homeGridView;
    public homegridview() {
    }


    String[] productName ={"Customer","Invoice","Supplier","Stock", "Report"};
    int[] productImages = {R.drawable.customer,R.drawable.invoice ,R.drawable.supplier ,R.drawable.stock,R.drawable.report };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_homegridview, container, false);
        homeGridView = view.findViewById(R.id.home_gridview);


        GridViewInflator gridViewInflator = new GridViewInflator(this.getContext(),productImages, productName, R.layout.homelayoutsamplegridview);

        homeGridView.setAdapter(gridViewInflator);

        homeGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                // Inflate the layout for this fragment
        return view;
    }
}