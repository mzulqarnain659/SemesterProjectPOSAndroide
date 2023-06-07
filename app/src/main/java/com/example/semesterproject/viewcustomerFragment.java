package com.example.semesterproject;

import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupMenu;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.semesterproject.Adapters.customerGridViewAdapter;

import java.util.ArrayList;






public class viewcustomerFragment extends Fragment {

    private TableLayout tableLayout;
    GridView customerGrid;
    posdatabasehelper databaseHelper;
    SQLiteDatabase db;
    View selectedRow;
    ArrayList<String> customerNames, customerPhoneNumbers;
    ArrayList<Integer> customerIDs;


    //data fetcher and storage managers
    public void fetchCustomerData()
    {
        //declaring arraylists
        customerIDs = new ArrayList<>();
        customerNames = new ArrayList<>();
        customerPhoneNumbers = new ArrayList<>();

        //adding null values to iterate one more time before actually inflating the grid for
        //header row
        customerIDs.add(null);
        customerNames.add("Customer Name");
        customerPhoneNumbers.add("Contact Number");

        //running query and stroing result in cursor object
        Cursor all_rows = db.rawQuery("select * from customer;", null);

        //going overall through the rows one by one if the first row exists
        if( all_rows.moveToFirst() )
        {
            do{
                customerIDs.add(all_rows.getInt(0));
                customerNames.add(all_rows.getString(1));
                customerPhoneNumbers.add(all_rows.getString(2));

            }while( all_rows.moveToNext() );
        }
    }

    //grid view data updater
    public void populateGridView()
    {
        //will be stored to the arraylists
        fetchCustomerData();

        //fetching custom customer adapter references by providing all fetched data
        customerGridViewAdapter adapter = new customerGridViewAdapter(getContext(), R.layout.item_customer, customerNames, customerPhoneNumbers, customerIDs);
        customerGrid.setAdapter(adapter);
    }

    //popup menu selection listener
    PopupMenu.OnMenuItemClickListener onMenuItemClickListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item)
        {
            //on real rows
            switch (item.getItemId())
            {
                case R.id.deleteCustomerView:
                    TextView customerID = selectedRow.findViewById(R.id.textViewCustomerId);

                    int rows_affected = db.delete("customer", "cust_id=?", new String[]{customerID.getText().toString().trim()});

                    if( rows_affected > 0 )
                    {
                        Toast.makeText(getActivity().getApplicationContext(), "Customer Deleted Successfully", Toast.LENGTH_SHORT);
                    }
                    else{
                        Toast.makeText(getActivity().getApplicationContext(), "Customer Deletion Unsuccessful", Toast.LENGTH_SHORT);
                    }

                    //refresh the adapter
                    populateGridView();

                    return true;
                case R.id.closeCustomerContextView:
                    return true;
            }

            return false;
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewcustomer, container, false);

        customerGrid = view.findViewById(R.id.customerViewContainer);

        //database fetching works
        //fetching reference of database
        databaseHelper = new posdatabasehelper(this.getActivity());
        db = databaseHelper.getWritableDatabase();

        //calling populater function
        populateGridView();


        //setting listeners to open the context menu
        customerGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                selectedRow = view;
                
                //if it is a header dont open the menu
                TextView headerCheck = selectedRow.findViewById(R.id.textViewCustomerId);
                if( headerCheck.getText().equals("ID") )
                {
                    return;
                }

                PopupMenu popupMenu = new PopupMenu(getContext().getApplicationContext(), view, Gravity.CENTER_HORIZONTAL);

                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.delete_customer, popupMenu.getMenu());

                //show the popup menu
                popupMenu.show();

                //also attach listener to it listen to the customer selection ont he selected view
                popupMenu.setOnMenuItemClickListener(onMenuItemClickListener);
            }
        });

        return view;
    }
}
