package com.example.semesterproject;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewInflator extends ArrayAdapter<String> {

    private final String[] productName;
    private final int[] imageResource;


    private int viewID;
    ListViewInflator(Context context, int[] imageResource, String[] productName, int viewID)
    {
        super(context, viewID, productName);

        this.viewID = viewID;
        this.imageResource = imageResource;
        this.productName = productName;
    }


    @Override
    public View getView(int position, View convertItem, ViewGroup parent) {

        View gridView = convertItem;


        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        gridView = inflater.inflate( viewID, parent,false );


        ImageView productImage = gridView.findViewById(R.id.list_imageview);
        TextView productName = gridView.findViewById(R.id.list_textview);

        productImage.setImageResource(this.imageResource[position]);
        productName.setText(this.productName[position]);



        return gridView;
    }
}

