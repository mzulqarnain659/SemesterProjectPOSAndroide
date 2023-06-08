package com.example.semesterproject;
import android.content.Intent;
import android.os.Bundle;
//        import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
        import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class home extends AppCompatActivity {

    GridView androidGridView;
TextView user;
    Button b1,b2;
    String username;
ToggleButton b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
username = getIntent().getStringExtra("user");

        b3= findViewById(R.id.change_home_view);
user = findViewById(R.id.textViewusername);
user.setText(username);
        Toast.makeText(this, username, Toast.LENGTH_SHORT).show();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.homeFragment, new homegridview());
        fragmentTransaction.commit();

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (b3.isChecked()){
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = manager.beginTransaction();
                    fragmentTransaction.replace(R.id.homeFragment, new listviewhome());
                    fragmentTransaction.commit();

                }
                else {FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = manager.beginTransaction();
                    fragmentTransaction.replace(R.id.homeFragment, new homegridview());
                    fragmentTransaction.commit();


                }
            }
        });


}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.custmenu)
        {
            Intent it= new Intent(home.this, addcustomer.class);
            startActivity(it);
        } else if (id == R.id.invmenu)
        {
            Intent it= new Intent(home.this, addinvoice.class);
            startActivity(it);
        }
        else if (id == R.id.suppliermenu)
        {
            Intent it= new Intent(home.this, addsupplier.class);
            startActivity(it);
        }
        else if (id == R.id.stockmenu)
        {
            Intent it= new Intent(home.this, stock.class);
            startActivity(it);
        }
        else if (id == R.id.reportmenu)
        {
            Intent it= new Intent(home.this, report.class);
            startActivity(it);
        }else if (id == R.id.action_logout){
            Intent it= new Intent(home.this, MainActivity.class);
            startActivity(it);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



}