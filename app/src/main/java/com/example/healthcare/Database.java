package com.example.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query1 = "create table users(username text, email text, password text)";
        sqLiteDatabase.execSQL(query1);

        String query2 = "create table carts(username text, product text, price float, order_type text)";
        sqLiteDatabase.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

//    Register function
    public void register(String username, String email, String password){
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);

        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }

//    Login function
    public int login(String username, String password){
        int result = 0;
        String str[]= new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username=? and password=?", str);
        if (c.moveToFirst()){
            return 1;
        }
        db.close();
        return result;
    }

//    Add to cart function
    public void addToCart(String username, String product, Float price, String order_type){
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("product", product);
        cv.put("price", price);
        cv.put("order_type", order_type);

        SQLiteDatabase db = getWritableDatabase();
        db.insert("carts", null ,cv);
        db.close();
    }

//    Check cart function
    public int checkCart(String username, String product){
        int result = 0;
        String str[] = new String[2];
        str[0]=username;
        str[1]=product;

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from carts where username=? and product=?", str);
        if(c.moveToFirst()){
            return  1;
        }
        db.close();
        return result;
    }

//    Remove cart
    public void removeCart(String username, String order_type){
        String str[] = new String[2];
        str[0] = username;
        str[1] = order_type;

        SQLiteDatabase db = getWritableDatabase();
        db.delete("carts", "where username=? and order_type=?", str);
        db.close();
    }

    public ArrayList getCartData(String username, String order_type){
        ArrayList <String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[2];
        str[0] = username;
        str[1] = order_type;
        Cursor c =db.rawQuery("select * from carts where username=? and order_type=?", str);
        if (c.moveToFirst()){
            do {
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product+"$"+price);
            }while (c.moveToNext());
        }
        db.close();
        return arr;
    }
}
