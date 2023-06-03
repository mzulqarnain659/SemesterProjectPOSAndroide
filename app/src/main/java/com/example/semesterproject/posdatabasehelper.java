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
        db.execSQL("CREATE TABLE IF NOT EXISTS inventory_item ( product_id       INTEGER PRIMARY KEY, sup_id           INTEGER, date_of_addition TEXT, prod_name        TEXT, prod_make        TEXT,prod_quantity    INTEGER,   prod_cost_price  INTEGER, prod_sale_price  INTEGER,);\n");

        // Create tables or perform any other initialization tasks here
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrade tasks here
    }


}
