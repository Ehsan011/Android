package com.example.doctorlagbe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public Database(Context context , @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1="create table users(username text, email text, password text)";
        sqLiteDatabase.execSQL(qry1);

        String qry2="create table cart(username text, product text, price float, or_type text)";
        sqLiteDatabase.execSQL(qry2);

        String qry3="create table orderplace(username text, fullname text, address text, contactno text, pincode int, date text, time text,  amount float, or_type text)";
        sqLiteDatabase.execSQL(qry3);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void register(String username, String email, String password){
        ContentValues cv=new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);

        SQLiteDatabase db=getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }

    public int login(String username, String password){
        int result=0;
        String str[]=new String[2];
        str[0] = username;
        str[1] = password;

        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from users where username=? and password=?", str);

//        Cursor c =db.rawQuery( "select * from users where username=? and password=?", str);
        if(cursor.moveToFirst()){
            result=1;
        }
        return result;
    }
//ADD CART
    public void addCart(String username, String product, float price, String or_type){
        ContentValues cv=new ContentValues();
        cv.put("username", username);
        cv.put("product", product);
        cv.put("price", price);
        cv.put("or_type", or_type);

        SQLiteDatabase db=getWritableDatabase();
        db.insert("cart", null, cv);
        db.close();
    }

    public int checkCart(String username, String product){
        int result=0;
        String str[]=new String[2];
        str[0] = username;
        str[1] = product;

        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from cart where username=? and product=?", str);

//        Cursor c =db.rawQuery( "select * from users where username=? and password=?", str);
        if(cursor.moveToFirst()){
            result=1;
        }
        db.close();
        return result;
    }
//Remove lab/mad
    public void removeCart(String username, String or_type){
        String str[] = new String[2];
        str[0] = username;
        str[1] = or_type;
        SQLiteDatabase db= getWritableDatabase();

        db.delete("cart", "username=? and or_type=?", str);
        db.close();
    }

    public ArrayList getCartData(String username, String or_type){
        ArrayList<String> arr=new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[2];
        str[0] = username;
        str[1] = or_type;

        Cursor c = db.rawQuery("select * from cart where username = ? and or_type = ?", str);
            if(c.moveToFirst()){
                do {
                    String product= c.getString(1);
                    String price= c.getString(2);
                    arr.add(product+"$"+price);

                }while (c.moveToNext());
            }
        db.close();
        return arr;

    }

    public void addOrder(String username, String fullname, String address, String contact, int pincode, String date, String time, float amount, String or_type){
        ContentValues cv =new ContentValues();
        cv.put("username", username);
        cv.put("fullname", fullname);
        cv.put("address", address);

        cv.put("contactno", contact);
        cv.put("pincode", pincode);
        cv.put("amount", amount);

        cv.put("date", date);
        cv.put("time", time);
        cv.put("or_type", or_type);

        SQLiteDatabase db=getWritableDatabase();
        db.insert("orderplace", null, cv);
        db.close();
    }

    public ArrayList getOrderData(String username){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0]=username;
        Cursor q = db.rawQuery("select * from orderplace where username =?", str);
        if(q.moveToFirst()){
            do{

                arr.add(q.getString(1)+"$"+q.getString(2)+"$"+q.getString(3)+"$"+q.getString(4)+"$"+q.getString(5)+"$"+q.getString(6)+"$"+q.getString(7)+"$"+q.getString(8));

            }while (q.moveToNext());
        }
        db.close();
        return arr;

    }
}
