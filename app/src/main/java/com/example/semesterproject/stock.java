package com.example.semesterproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class stock extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        Button add, update,view;
        add = findViewById(R.id.AddStockButton);
        update = findViewById(R.id.UpdateStockButton);
        view = findViewById(R.id.ViewStockButton);
        FrameLayout CustomerFrame = findViewById(R.id.stockframe);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction= manager.beginTransaction();
        transaction.replace(R.id.stockframe, new AddStockFragment());
        transaction.commit();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction= manager.beginTransaction();
                transaction.replace(R.id.stockframe, new AddStockFragment());
                transaction.commit();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction= manager.beginTransaction();
                transaction.replace(R.id.stockframe, new UpdateStockFragment());
                transaction.commit();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction= manager.beginTransaction();
                transaction.replace(R.id.stockframe, new ViewStockFragment());
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
            Intent it= new Intent(stock.this, addcustomer.class);
            startActivity(it);
        } else if (id == R.id.invmenu)
        {
            Intent it= new Intent(stock.this, addinvoice.class);
            startActivity(it);
        }
        else if (id == R.id.suppliermenu)
        {
            Intent it= new Intent(stock.this, addsupplier.class);
            startActivity(it);
        }
        else if (id == R.id.stockmenu)
        {
            Intent it= new Intent(stock.this, stock.class);
            startActivity(it);
        }
        else if (id == R.id.reportmenu)
        {
            Intent it= new Intent(stock.this, report.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }

}