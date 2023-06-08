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


public class UpdateStockFragment extends Fragment {


    public  posdatabasehelper dbhelper;
    public SQLiteDatabase db;
   public String SelectedProductId ;
   public String SelectedSupplierId ;
    Spinner pid,sid;
    String proid, supid;
    EditText product_name,date_of_addition_in_stock,prod_make,quantity,cost_price, sales_price;
Button Update;
Button search;
String DateOfAddition,ProdName,ProdMake;
String ProdQuantity,ProdCP,ProdSP,Prod_id,Sup_id;

    public UpdateStockFragment() {
        // Required empty public constructor
    }
    private void fetchDataFromDatabase(){



        db = dbhelper.getReadableDatabase();

        String Sql = " SELECT * FROM inventory_item where product_id =? AND sup_id = ? ";
        Cursor stockDetail=  db.rawQuery(Sql, new String[]{SelectedProductId,SelectedSupplierId});
        if (stockDetail.moveToNext()){

            DateOfAddition = stockDetail.getString(2);
            ProdName = stockDetail.getString(3);
            ProdMake = stockDetail.getString(4);
            ProdQuantity = stockDetail.getString(5);
            ProdCP = stockDetail.getString(6);
            ProdSP = stockDetail.getString(7);
         }


    }
 public void updateStock(){
db = dbhelper.getWritableDatabase();
     db.execSQL("UPDATE inventory_item SET prod_name = '" + product_name.getText() + "', prod_make = '" + prod_make.getText() + "', prod_quantity = '"+ quantity.getText() +"' , prod_cost_price = '"+ cost_price.getText() +"', prod_sale_price = '"+ sales_price.getText() + "', date_of_addition='"+date_of_addition_in_stock.getText()+"' WHERE product_id = '"+ SelectedProductId +"' AND sup_id = '"+ SelectedSupplierId +"' " );
     Toast.makeText(this.getActivity(), "Updated Successfully", Toast.LENGTH_SHORT).show();

 }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
  public  List<String>  fetchSupplierIDFromDatabase(){
      List<String> supplierIds = new ArrayList<>();
       SQLiteDatabase db = dbhelper.getReadableDatabase();
      // Query the database and retrieve customer IDs
      Cursor cursor = db.rawQuery("SELECT sup_id FROM inventory_item", null);
      if (cursor.moveToFirst()) {
          do {
              String customerId = cursor.getString(cursor.getColumnIndex("sup_id"));
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
        View view= inflater.inflate(R.layout.fragment_update_stock, container, false);
        dbhelper = new posdatabasehelper(this.getActivity());
    pid = view.findViewById(R.id.ProdutIdInUpdateStockFragment);
    sid= view.findViewById(R.id.SupplierIDInUpdateStockFragment);
    product_name =view.findViewById(R.id.ProductNameInUpdateStock);
    date_of_addition_in_stock = view.findViewById(R.id.DateOfAdditioninStockInUpdateStock);
    prod_make = view.findViewById(R.id.ProductMakeinUpdateStockFragment);
    quantity = view.findViewById(R.id.ProductQuantityInUpdateStockFragment);
    cost_price = view.findViewById(R.id.UnitPriceInUpdateStockFragment);
    sales_price = view.findViewById(R.id.SalesPriceInUpdateStockFragment);
Update = view.findViewById(R.id.UpdateStock);
search = view.findViewById(R.id.SearchProductButtonInUpdateCustFragment);
        List<String> ProductIds = fetchproductIdFromDatabase();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, ProductIds);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
pid.setAdapter(adapter);
        List<String> supplierIds = fetchSupplierIDFromDatabase();
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, supplierIds);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sid.setAdapter(adapter1);
        pid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
SelectedProductId = (String) pid.getSelectedItem();
                Toast.makeText(getActivity(), SelectedProductId, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               SelectedSupplierId = (String) sid.getSelectedItem();
                Toast.makeText(getActivity(), SelectedSupplierId, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sid.setSelected(false);
        //proid = (String) pid.getSelectedItem();
       // supid =(String) sid.getSelectedItem()
        Update.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        updateStock();
    }
});
        search.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        fetchDataFromDatabase();
        date_of_addition_in_stock.setText(DateOfAddition);
        product_name.setText(ProdName);
        prod_make.setText(ProdMake);
        quantity.setText(ProdQuantity);
        cost_price.setText(ProdCP);
        sales_price.setText(ProdSP);

    }
});
 return view;
    }
    private List<String> fetchproductIdFromDatabase() {
        List<String> ProductIds = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        // Query the database and retrieve customer IDs
        Cursor cursor = db.rawQuery("SELECT product_id FROM inventory_item", null);
        if (cursor.moveToFirst()) {
            do {
                String customerId = cursor.getString(cursor.getColumnIndex("product_id"));
                ProductIds.add(customerId);
            } while (cursor.moveToNext());
        }

        // Close the cursor and database
        cursor.close();


        return ProductIds;
    }

}