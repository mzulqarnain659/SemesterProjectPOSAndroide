package com.example.semesterproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class addcustomer extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.addcustomer);
int a;
        Button add,update,view;
        add = findViewById(R.id.AddCustomerButton);
        update = findViewById(R.id.UpdateCustomerButton);
        view  = findViewById(R.id.ViewCustomerButton);
        FrameLayout CustomerFrame = findViewById(R.id.Customer_Frame);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction  transaction= manager.beginTransaction();
        transaction.replace(R.id.Customer_Frame, new addcutomerfragment());
        transaction.commit();
add.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction  transaction= manager.beginTransaction();
        transaction.replace(R.id.Customer_Frame, new addcutomerfragment());
        transaction.commit();
    }
});
update.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction  transaction= manager.beginTransaction();
        transaction.replace(R.id.Customer_Frame, new updatecustomerFragment());
        transaction.commit();
    }
});
view.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction  transaction= manager.beginTransaction();
        transaction.replace(R.id.Customer_Frame, new viewcustomerFragment());
        transaction.commit();
    }
});
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.custmenu)
        {
            Intent it= new Intent(addcustomer.this, addcustomer.class);
            startActivity(it);
        } else if (id == R.id.invmenu)
        {
            Intent it= new Intent(addcustomer.this, addinvoice.class);
            startActivity(it);
        }
        else if (id == R.id.suppliermenu)
        {
            Intent it= new Intent(addcustomer.this, addsupplier.class);
            startActivity(it);
        }
        else if (id == R.id.stockmenu)
        {
            Intent it= new Intent(addcustomer.this, stock.class);
            startActivity(it);
        }
        else if (id == R.id.reportmenu)
        {
            Intent it= new Intent(addcustomer.this, report.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }

}
