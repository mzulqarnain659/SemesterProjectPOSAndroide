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

import com.example.semesterproject.R;

import java.util.ArrayList;

public class stockGridViewAdapter extends ArrayAdapter {

    Context context;
    ArrayList<String> ProductName, ProductMake,DateofAddition;
    ArrayList<String> Product_id,Supplier_Id,Quantity , costprice, salesprice;

    public stockGridViewAdapter(@NonNull Context context, int layoutResource, ArrayList<String> DateofAddition, ArrayList<String> ProductName, ArrayList<String> ProductMake, ArrayList<String> Product_id,ArrayList<String> Supplier_Id,ArrayList<String> Quantity ,ArrayList<String> costprice,ArrayList<String> salesprice) {
        super(context, layoutResource, ProductName.toArray());
        this.context = context;
        this.ProductName = ProductName;
        this.ProductMake = ProductMake;
        this.DateofAddition = DateofAddition;
        this.Product_id = Product_id;
        this.Supplier_Id = Supplier_Id;
        this.Quantity = Quantity;
        this.costprice = costprice;
        this.salesprice = salesprice;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View itemView = convertView;

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        itemView = inflater.inflate(R.layout.itemstock, parent, false);

        //fetching references from the Layout Item to inflate them
        TextView Product = itemView.findViewById(R.id.textViewProductId);
        TextView supplierid = itemView.findViewById(R.id.textViewSupplerID);
        TextView Name = itemView.findViewById(R.id.textViewProductName);
        TextView date = itemView.findViewById(R.id.textViewDate);
        TextView make = itemView.findViewById(R.id.textViewMake);
        TextView quantity = itemView.findViewById(R.id.textViewQuantity);
        TextView costp = itemView.findViewById(R.id.textViewCostPrice);
        TextView salep = itemView.findViewById(R.id.textViewSalePrice);
         //===inflate in the widgets with data

        //if on the starting null values means HEADER ADDING POINT
        if( position == 0 )
        {
            Product.setTypeface(null, Typeface.BOLD);
            supplierid.setTypeface(null, Typeface.BOLD);
            Name.setTypeface(null, Typeface.BOLD);
            date.setTypeface(null, Typeface.BOLD);
            make.setTypeface(null, Typeface.BOLD);
            quantity.setTypeface(null, Typeface.BOLD);
            costp.setTypeface(null, Typeface.BOLD);
            salep.setTypeface(null, Typeface.BOLD);

            Product.setText("ID");
            supplierid.setText("Supplier");
            Name.setText("Date");
            date.setText("Name");
            make.setText("Make");
            quantity.setText("Quantity");
            costp.setText("Cost");
            salep.setText("Sale");
         }
        else{

            Product.setText(Product_id.get(position)+"");
            supplierid.setText(Supplier_Id.get(position));
            date.setText(DateofAddition.get(position)+"");
            Name.setText(ProductName.get(position));
            make.setText(ProductMake.get(position));
            quantity.setText(Quantity.get(position));
            costp.setText(costprice.get(position)+"");
            salep.setText(salesprice.get(position));

        }

        return itemView;
    }
}
