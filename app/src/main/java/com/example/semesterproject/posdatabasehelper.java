package com.example.semesterproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class posdatabasehelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pos.db";
    private static final int DATABASE_VERSION = 1;

    public posdatabasehelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS customer (" +
                "    cust_id    INTEGER PRIMARY KEY AUTOINCREMENT," +
                "    cust_name  TEXT," +
                "    cust_phone TEXT" +
                ");");

        db.execSQL("CREATE TABLE IF NOT EXISTS invoice (" +
                "    inv_id         INTEGER PRIMARY KEY AUTOINCREMENT," +
                "    cust_id        INTEGER," +
                "    inv_date       TEXT," +
                "    payment_status TEXT" +
                ");");
db.execSQL("CREATE TABLE IF NOT EXISTS supplier (" +
        "    supplier_id      INTEGER PRIMARY KEY AUTOINCREMENT," +
        "    supplier_name    TEXT," +
        "    supplier_contact TEXT" +
        ");");
db.execSQL("CREATE TABLE IF NOT EXISTS inventory_item ( product_id INTEGER PRIMARY KEY," +
        "        sup_id           INTEGER," +
        "        date_of_addition TEXT," +
        "        prod_name        TEXT," +
        "        prod_make        TEXT," +
        "        prod_quantity    INTEGER," +
        "        prod_cost_price  INTEGER," +
        "        prod_sale_price  INTEGER);");
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


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrade tasks here
    }


}
