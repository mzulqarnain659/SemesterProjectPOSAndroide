package com.example.semesterproject.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.semesterproject.R;

import java.util.ArrayList;
import java.util.List;

public class customerGridViewAdapter extends ArrayAdapter {

    Context context;
    ArrayList<String> customerNames, customerPhoneNumbers;
    ArrayList<Integer> customerIDs;

    public customerGridViewAdapter(@NonNull Context context, int layoutResource, ArrayList<String> customerNames, ArrayList<String> customerPhoneNumbers, ArrayList<Integer> customerIDs) {
        super(context, layoutResource, customerNames.toArray());
        this.context = context;
        this.customerNames = customerNames;
        this.customerPhoneNumbers = customerPhoneNumbers;
        this.customerIDs = customerIDs;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View itemView = convertView;

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        itemView = inflater.inflate(R.layout.item_customer, parent, false);

        //fetching references from the Layout Item to inflate them
        TextView customerID = itemView.findViewById(R.id.textViewCustomerId);
        TextView customerName = itemView.findViewById(R.id.textViewCustomerName);
        TextView customerPhoneNumber = itemView.findViewById(R.id.textViewCustomerPhoneNumber);

        //===inflate in the widgets with data

        //if on the starting null values means HEADER ADDING POINT
        if( position == 0 )
        {
            customerID.setTypeface(null, Typeface.BOLD);
            customerName.setTypeface(null, Typeface.BOLD);
            customerPhoneNumber.setTypeface(null, Typeface.BOLD);

            customerID.setText("ID");
            customerName.setText("Name");
            customerPhoneNumber.setText("Contact");
        }
        else{
            customerID.setText(customerIDs.get(position)+"");
            customerName.setText(customerNames.get(position));
            customerPhoneNumber.setText(customerPhoneNumbers.get(position));
        }

        return itemView;
    }
}
