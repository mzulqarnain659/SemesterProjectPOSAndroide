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

public class addsupplier  extends AppCompatActivity {

    @Override
    public  void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addsupplier);

        Button add, update,view;
add = findViewById(R.id.AddSupplierButton);
update = findViewById(R.id.UpdateSupplierButton);
view = findViewById(R.id.ViewSupplierButton);
        FrameLayout CustomerFrame = findViewById(R.id.SupplierFrame);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction= manager.beginTransaction();
        transaction.replace(R.id.SupplierFrame, new AddSupplierFragment());
        transaction.commit();
    add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction= manager.beginTransaction();
            transaction.replace(R.id.SupplierFrame, new AddSupplierFragment());
            transaction.commit();
        }
    });
    update.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction= manager.beginTransaction();
            transaction.replace(R.id.SupplierFrame, new UpdateSupplierFragment());
            transaction.commit();
        }
    });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction= manager.beginTransaction();
                transaction.replace(R.id.SupplierFrame, new ViewSupplierFragment());
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
            Intent it= new Intent(addsupplier.this, addcustomer.class);
            startActivity(it);
        } else if (id == R.id.invmenu)
        {
            Intent it= new Intent(addsupplier.this, addinvoice.class);
            startActivity(it);
        }
        else if (id == R.id.suppliermenu)
        {
            Intent it= new Intent(addsupplier.this, addsupplier.class);
            startActivity(it);
        }
        else if (id == R.id.stockmenu)
        {
            Intent it= new Intent(addsupplier.this, stock.class);
            startActivity(it);
        }
        else if (id == R.id.reportmenu)
        {
            Intent it= new Intent(addsupplier.this, report.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }

}
