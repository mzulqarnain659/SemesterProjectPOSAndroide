package com.example.semesterproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class AddItemToInvoiceFragment extends Fragment {

Spinner customer_id, invoiceid,productName;
EditText date, make,quantity,unitprice,total_price;
Button SearchInvoice,SearchProduct,CalculateTotal,AddItem,Update;
posdatabasehelper dbhelper;
SQLiteDatabase db;
public String productIdAssociatedWithName;
 public String DATE,MAKE,QUANTITY,UNITPRICE,TOTALPRICE;
 Integer enteredquantity;
    public AddItemToInvoiceFragment() {
        // Required empty public constructor


    }
    private List<String> fetchCustomerID() {
        List<String> customerIds = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        // Query the database and retrieve customer IDs
        Cursor cursor = db.rawQuery("SELECT cust_id FROM customer", null);
        if (cursor.moveToFirst()) {
            do {
                String customerId = cursor.getString(cursor.getColumnIndex("cust_id"));
                customerIds.add(customerId);
            } while (cursor.moveToNext());
        }

        // Close the cursor and database
        cursor.close();


        return customerIds;
    }
    private List<String> fetchInvoiceID() {
        List<String> invoice_id = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        // Query the database and retrieve customer IDs
        Cursor invoiceid = db.rawQuery("SELECT inv_id FROM invoice WHERE cust_id ='"+ customer_id.getSelectedItem() +"'", null);
        if (invoiceid.moveToFirst()) {
            do {
                String invoice = invoiceid.getString(invoiceid.getColumnIndex("inv_id"));
                invoice_id.add(invoice);
            } while (invoiceid.moveToNext());
        }
         invoiceid.close();
        return invoice_id;
    }
    private List<String> fetchProductName() {
        List<String> productName = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        // Query the database and retrieve customer IDs
        Cursor prodName = db.rawQuery("SELECT prod_name FROM inventory_item ", null);
        if (prodName.moveToFirst()) {
            do {
                String invoice = prodName.getString(0);
                productName.add(invoice);
            } while (prodName.moveToNext());
        }
        prodName.close();
        return productName;
    }

    private void fetchprodifalreadyadded(){

            db= dbhelper.getReadableDatabase();
            Cursor alreadyexistingproduct = db.rawQuery("SELECT date_of_addition,itm_make, itm_quantity, itm_price, itm_total_price FROM invoice_item",null);
            if (alreadyexistingproduct.moveToFirst()){
                DATE = alreadyexistingproduct.getString(0);
                MAKE = alreadyexistingproduct.getString(1);
                QUANTITY = alreadyexistingproduct.getString(2);
                UNITPRICE= alreadyexistingproduct.getString(3);
                TOTALPRICE = alreadyexistingproduct.getString(4);
            }
    }
    private  void addproducttoinvoice(){
        db = dbhelper.getWritableDatabase();
        Cursor checkQuantity = db.rawQuery("SELECT prod_quantity FROM inventory_item WHERE product_id ='"+ productIdAssociatedWithName +"'",null);
    if(checkQuantity.moveToFirst()){
        Integer quantityp = checkQuantity.getInt(0);
        if (quantityp< Integer.parseInt(quantity.getText().toString())){
            Toast.makeText(this.getActivity(), "Out of Stock", Toast.LENGTH_SHORT).show();
        }
        else {
                String sql = "UPDATE inventory_item SET prod_quantity = prod_quantity -'"+ Integer.parseInt(quantity.getText().toString()) +"' WHERE product_id = '"+ productIdAssociatedWithName +"'";
                db.execSQL(sql);
                String sql0 = "INSERT INTO invoice_item(prod_id,cust_id,inv_id,date_of_addition,itm_name,itm_make,itm_quantity,itm_price,itm_total_price) VALUES('"+productIdAssociatedWithName+"','"+ customer_id.getSelectedItem()+"','"+ invoiceid.getSelectedItem() +"','"+ date.getText()+ "','"+ productName.getSelectedItem() +"','"+ make.getText()+"','"+ quantity.getText() +"','"+ unitprice.getText() +"','"+total_price.getText() +"')";
                db.execSQL(sql0);
                String sql1 = "UPDATE invoice_item SET sub_total = itm_total_price + '" +Integer.parseInt(total_price.getText().toString()) + "'  where inv_id='"+ invoiceid.getSelectedItem() +"' AND cust_id ='"+ customer_id.getSelectedItem()+"' ";
                db.execSQL(sql1);
        }
    }
    }

    private  void updateinvoiceitem(){
        db = dbhelper.getWritableDatabase();

      //  db.execSQL("UPDATE invoice_item SET sub_total = sub_total - '"  "'");
        db.execSQL("UPDATE invoice_item SET itm_quantity = '"+quantity.getText()+"', itm_price = '" + unitprice.getText()+"',itm_total_price = '"+ total_price.getText() +"', sub_total  = sub_total + '"+ Integer.parseInt(total_price.getText().toString())  +"' WHERE cust_id ='"+customer_id.getSelectedItem()+"'  AND inv_id = '"+ invoiceid.getSelectedItem()  +"'");

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_additemtoinvoice, container, false);
        dbhelper = new posdatabasehelper(this.getActivity());
        customer_id = view.findViewById(R.id.CustomerIdInAddItemToInvoice);
        invoiceid = view.findViewById(R.id.InvoiceIDInAddItemToInvoice) ;
        productName = view.findViewById(R.id.NameOfProductInAddItemToInvoice);
        date = view.findViewById(R.id.AdditionOfItemDateInAddItemToInvoice);
        make = view.findViewById(R.id.ProductMakeInAddItemToInvoice);
        quantity = view.findViewById(R.id.ProductQuantityInAddItemToInvoice);

        unitprice = view.findViewById(R.id.UnitPriceInAddItemToInvoice);
        total_price = view.findViewById(R.id.TotalPriceInAddItemToInvoice);
        SearchInvoice = view.findViewById(R.id.SearchInvoiceIDInAddItemToInvoice);
        SearchProduct  = view.findViewById(R.id.SearchIfProductIsAlreadyAddedInAddItemToInvoice);
        AddItem = view.findViewById(R.id.AdditemToInvoice);
        CalculateTotal = view.findViewById(R.id.CalculateBillOfItemTotalPriceInAddItemToInvoice);
        Update = view.findViewById(R.id.UpdateItemInInvoice);

        List<String> customerIds = fetchCustomerID();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, customerIds);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        customer_id.setAdapter(adapter);
        List<String> productNames = fetchProductName();
        ArrayAdapter<String> adapterproductName = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, productNames);
        adapterproductName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productName.setAdapter(adapterproductName);
        db = dbhelper.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS invoice_item (" +
                "    prod_id          INTEGER," +
                "    cust_id          INTEGER," +
                "    inv_id           INTEGER," +
                "    date_of_addition TEXT," +
                "    itm_name         TEXT," +
                "    itm_make         TEXT," +
                "    itm_quantity     INTEGER," +
                "    itm_price        INTEGER," +
                "    itm_total_price  INTEGER," +
                "    sub_total        INTEGER" +
                ");");
        productName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                db = dbhelper.getReadableDatabase();
                Cursor getproductid = db.rawQuery("SELECT product_id FROM inventory_item WHERE prod_name='"+productName.getSelectedItem()+"'",null);
                    if (getproductid.moveToFirst()){
                        String proid = getproductid.getString(0);
                       productIdAssociatedWithName = proid;
                        Toast.makeText(getActivity(), productIdAssociatedWithName, Toast.LENGTH_SHORT).show();
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        SearchInvoice.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        List<String> invoice_id = fetchInvoiceID();
        ArrayAdapter<String> adapterInvoice = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, invoice_id);
        adapterInvoice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        invoiceid.setAdapter(adapterInvoice);
    }
});
        SearchProduct.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        fetchprodifalreadyadded();
        date.setText(DATE);
        make.setText(MAKE);
        quantity.setText(QUANTITY);
        unitprice.setText(UNITPRICE);
        total_price.setText(TOTALPRICE);
    }
});
        CalculateTotal.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String tp = String.valueOf(Integer.parseInt(quantity.getText().toString())*Integer.parseInt(unitprice.getText().toString()));
        total_price.setText(tp);
    }
});
        AddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addproducttoinvoice();
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateinvoiceitem();
            }
        });

return view;
    }
}