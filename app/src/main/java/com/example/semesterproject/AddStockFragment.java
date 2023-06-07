package com.example.semesterproject;

import static java.sql.Types.INTEGER;
import static java.text.Collator.PRIMARY;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddStockFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddStockFragment extends Fragment {

    public posdatabasehelper dbhelper;
   private SQLiteDatabase db;
    public AddStockFragment() {
        // Required empty public constructor
    }

EditText product_id,product_name,date_of_addition_in_stock,prod_make,quantity,cost_price, sales_price;
Spinner supplierid;
Button AddStock;
    String productid,productname,dateofadditioninstock,prodmake,Quantity,costprice;
       String   salesprice;
    String Supplierid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private List<String> fetchDataFromDatabase() {
        List<String> supplierIds = new ArrayList<>();
      //  SQLiteDatabase db = dbhelper.getReadableDatabase();
        // Query the database and retrieve customer IDs
        Cursor cursor = db.rawQuery("SELECT supplier_id FROM supplier", null);
        if (cursor.moveToFirst()) {
            do {
                String customerId = cursor.getString(cursor.getColumnIndex("supplier_id"));
                supplierIds.add(customerId);
            } while (cursor.moveToNext());
        }

        // Close the cursor and database
        cursor.close();


        return supplierIds;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_add_stock, container, false);
        dbhelper = new posdatabasehelper(this.getActivity());
        db = dbhelper.getWritableDatabase();
        product_id= view.findViewById(R.id.PRODUCTIDINADDSTOCKFRAGMENT);
        product_name = view.findViewById(R.id.PRODUCTNAMEINADDSTOCKFRAGMENT);
        date_of_addition_in_stock=view.findViewById(R.id.ADDITIONDATEOFPRODUCTINADDSTOCKFRAGMENT);
        prod_make = view.findViewById(R.id.MAKEOFPRODUCTINADDSTOCKFRAGMENT);
        quantity = view.findViewById(R.id.ProductQuantityINADDSTOCKFRAGMENT);
        cost_price = view.findViewById(R.id.UnitPriceINADDSTOCKFRAGMENT);
        sales_price = view.findViewById(R.id.SALEPRICEINADDSTOCKFRAGMENT);
        supplierid = view.findViewById(R.id.SUPPLIERIDINADDSTOCKFRAGMENT);
        productid = product_id.getText().toString();
        productname= product_name.getText().toString();
        dateofadditioninstock = date_of_addition_in_stock.getText().toString();
        prodmake = prod_make.getText().toString();
        Quantity = quantity.getText().toString();
        costprice = cost_price.getText().toString();
        salesprice =sales_price.getText().toString();
        List<String> supplierIds = fetchDataFromDatabase();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, supplierIds);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        supplierid.setAdapter(adapter);
        Supplierid =(String) supplierid.getSelectedItem();

        AddStock = view.findViewById(R.id.AddProductStock);



        AddStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productid = product_id.getText().toString();
                productname= product_name.getText().toString();
                dateofadditioninstock = date_of_addition_in_stock.getText().toString();
                prodmake = prod_make.getText().toString();
                Quantity = quantity.getText().toString();
                costprice = cost_price.getText().toString();
                salesprice =sales_price.getText().toString();
                Supplierid =(String) supplierid.getSelectedItem();
                insertstock(productid,productname,dateofadditioninstock,prodmake,Quantity,costprice,salesprice,Supplierid);



            }
        });
        return view;
    }
    private void insertstock(String productid,String productname, String dateofadditioninstock, String prodmake,
                             String Quantity,String costprice, String salesprice,String Supplierid) {
        SQLiteOpenHelper dbHelper = new posdatabasehelper(this.getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS inventory_item (product_id INTEGER PRIMARY KEY," +
                "        sup_id   INTEGER," +
                "        date_of_addition TEXT," +
                "        prod_name        TEXT," +
                "        prod_make        TEXT," +
                "        prod_quantity    INTEGER," +
                "        prod_cost_price  INTEGER," +
                "        prod_sale_price  INTEGER);");
        String addstk = "INSERT INTO inventory_item(product_id,sup_id,date_of_addition,prod_name,prod_make, prod_quantity,prod_cost_price,prod_sale_price) VALUES(?,?,?,?,?,?,?,?)";
        Object[] bindArgs = {productid,Supplierid,dateofadditioninstock,productname,
                prodmake,Quantity,costprice,salesprice
        };

        try {
            db.execSQL(addstk, bindArgs);
            Toast.makeText(getActivity(), "Product added to Stock successfully", Toast.LENGTH_SHORT).show();

        } catch (SQLException e) {
            Toast.makeText(getActivity(), "Error inserting Product " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            db.close();
        }

    }

}