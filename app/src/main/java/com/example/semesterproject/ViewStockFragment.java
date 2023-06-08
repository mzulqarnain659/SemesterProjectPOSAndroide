package com.example.semesterproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.semesterproject.Adapters.stockGridViewAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewStockFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewStockFragment extends Fragment {

    GridView StockGrid;
    posdatabasehelper dbhelper;
    SQLiteDatabase db;
    View selectedRow;
    ArrayList<String> ProductName, ProductMake,DateofAddition;
    ArrayList<String> Product_id,Supplier_Id,Quantity , costprice, salesprice;

    public ViewStockFragment() {
        // Required empty public constructor
    }
    public void fetchCustomerData()
    {
        //declaring arraylists
        ProductName = new ArrayList<>();
        ProductMake = new ArrayList<>();
        Product_id = new ArrayList<>();
        DateofAddition = new ArrayList<>();
        Supplier_Id = new ArrayList<>();
        Quantity = new ArrayList<>();
        costprice = new ArrayList<>();
        salesprice = new ArrayList<>();
        //adding null values to iterate one more time before actually inflating the grid for
        //header row
        Product_id.add(null);
        Supplier_Id.add(null);
        Quantity.add(null);
        costprice.add(null);
        salesprice.add(null);
        ProductName.add("null");
        ProductMake.add("null");
        DateofAddition.add("null");


        //running query and stroing result in cursor object
        Cursor all_rows = db.rawQuery("select * from inventory_item;", null);

        //going overall through the rows one by one if the first row exists
        if( all_rows.moveToFirst() )
        {
            do{
                Product_id.add(all_rows.getString(0));
                Supplier_Id.add(all_rows.getString(1));
                ProductName.add(all_rows.getString(2));
                DateofAddition.add(all_rows.getString(3));
                ProductMake.add(all_rows.getString(4));
                Quantity.add(all_rows.getString(5));
                costprice.add(all_rows.getString(6));
                salesprice.add(all_rows.getString(7));


            }while( all_rows.moveToNext() );
        }
    }
    public void populateGridView()
    {
        //will be stored to the arraylists
        fetchCustomerData();

        //fetching custom customer adapter references by providing all fetched data
        stockGridViewAdapter adapter = new stockGridViewAdapter( getContext(), R.layout.itemstock, DateofAddition, ProductName, ProductMake, Product_id,Supplier_Id,Quantity,costprice,salesprice);
        StockGrid.setAdapter(adapter);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_stock, container, false);
StockGrid = view.findViewById(R.id.StockViewContainer);

//        tableLayout = view.findViewById(R.id.tableLayoutstock);
      dbhelper = new posdatabasehelper(this.getActivity());
        db = dbhelper.getReadableDatabase();

        populateGridView();
//        fetchSupplierData();
       return view;

    }
//    private void fetchSupplierData() {
//        Cursor cursor = db.rawQuery("SELECT * FROM inventory_item", null);
//        TableRow headerRow = new TableRow(this.getActivity());
//
//        TextView headerId = createHeaderTextView("ID");
//        TextView headerName = createHeaderTextView("Supplier");
//        TextView headerContact = createHeaderTextView("Date");
//        TextView ProdName = createHeaderTextView("Name");
//        TextView Make = createHeaderTextView("Make");
//        TextView Quantity = createHeaderTextView("Quantity");
//        TextView cp = createHeaderTextView("Cost");
//        TextView sp = createHeaderTextView("Sale");
//
//
//        headerRow.addView(headerId);
//        headerRow.addView(headerName);
//        headerRow.addView(headerContact);
//        headerRow.addView(ProdName);
//        headerRow.addView(Make);
//        headerRow.addView(Quantity);
//        headerRow.addView(cp);
//        headerRow.addView(sp);
//
//        tableLayout.addView(headerRow);
//        if (cursor.moveToFirst()) {
//            do {
//                String product_id = cursor.getString(cursor.getColumnIndex("product_id"));
//                String suplierid = cursor.getString(1);
//                String date = cursor.getString(2);
//                String name = cursor.getString(3);
//                String make = cursor.getString(4);
//                String quan = cursor.getString(5);
//                String costp = cursor.getString(6);
//                String salep = cursor.getString(7);
//                TableRow tableRow = new TableRow(this.getActivity());
//                TextView textheaderId = createHeaderTextView(product_id);
//                TextView textheaderName = createHeaderTextView(suplierid);
//                TextView textheaderContact = createHeaderTextView(date);
//                TextView textProdName = createHeaderTextView(name);
//                TextView textMake = createHeaderTextView(make);
//                TextView textQuantity = createHeaderTextView(quan);
//                TextView textcp = createHeaderTextView(costp);
//                TextView textsp = createHeaderTextView(salep);
//
//
//                tableRow.addView(textheaderId);
//                tableRow.addView(textheaderName);
//                tableRow.addView(textheaderContact);
//
//                tableRow.addView(textProdName);
//                tableRow.addView(textMake);
//                tableRow.addView(textQuantity);
//
//                tableRow.addView(textcp);
//                tableRow.addView(textsp);
//
//                tableLayout.addView(tableRow);
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//    }
//
//    private TextView createTextView(String text) {
//        TextView textView = new TextView(this.getActivity());
//        textView.setText(text);
//          return textView;
//    }
//    private TextView createHeaderTextView(String text) {
//        TextView textView = createTextView(text);
//        textView.setAllCaps(true);
//        textView.setGravity(Gravity.CENTER);
//        return textView;
//    }
}