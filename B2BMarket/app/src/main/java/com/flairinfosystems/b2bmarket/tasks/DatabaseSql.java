package com.flairinfosystems.b2bmarket.tasks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.flairinfosystems.b2bmarket.models.Contact;

/**
 * Created by user on 06-02-2017.
 */

public class DatabaseSql extends SQLiteOpenHelper {
    private static DatabaseSql mInstance = null;
    private static final int DATABASE_VERSION =4;
    private static final String DATABASE_NAME ="contact.db";
    //******************COntacts Table***************
    private static final String TABLE_CONTACTS_NAME ="contacts";
    private static final String CONTACTS_COLUMN_ID ="id";
    private static final String CONTACTS_COLUMN_NAME = "name";
    private static final String CONTACTS_COLUMN_COMPANY ="company";
    private static final String CONTACTS_COLUMN_EMAIL ="email";
    private static final String CONTACTS_COLUMN_PASS ="pass";
    private static final String CONTACTS_COLUMN_CONFIRM ="confirmpass";
    private static final String CONTACTS_COLUMN_PROFILE="profile";
    //******************COntacts Table***************
    //******************Seller Table***************
  private static final String TABLE_SELLER_NAME ="contacts";
    private static final String SELLER_COLUMN_MOBILE ="mobile";
    private static final String SELLER_COLUMN_NAME = "name";
    private static final String SELLER_COLUMN_CITY ="city";
    private static final String SELLER_COLUMN_COMPANY_NAME ="Copany_Name";
    private static final String SELLER_COLUMN_COMPANY_ADDRESS = "Company_Address";
    private static final String SELLER_COLUMN_WEBSITE ="website";
    //******************Seller Table***************
    //******************Product Table***************
    private static final String PRODUCT_COLUMN_ID ="id";
    private static final String PRODUCT_COLUMN_NAME ="name";
    private static final String PRODUCT_COLUMN_PICTURE_1 ="Picture_1";
    private static final String PRODUCT_COLUMN_PICTURE_2 ="Picture_2";
    private static final String PRODUCT_COLUMN_PICTURE_3="Picture_3";
    private static final String PRODUCT_COLUMN_PICTURE_4="Picture_4";
    private static final String PRODUCT_COLUMN_SELLER_MOBILE_ID ="Seller_Mobile_id";


    //******************Product Table***************
    private SQLiteDatabase db;
    private static final String TABLE_CREATE_CONTACTS = "create table contacts (id integer primary key not null, " +
            "name text not null, company text not null, email text not null, pass text not null, confirmpass text not null," +
            "profile blob not null);";

    private static final String TABLE_CREATE_SELLER = "create table sellers (mobile text primary key not null,name text,city text," +
            "Company_Name text not null, Company_Address text not null,website text);";

    private static final String TABLE_CREATE_PRODUCT="create table products (id integer primary key not null,name text not null," +
            "Picture_1 text ,Picture_2 text,Picture_3 text,Picture_4 text," +
            "Seller_Mobile_id text not null,foreign key(Seller_Mobile_id) references sellers(mobile))";
    private DatabaseSql(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_CONTACTS);
        //db.execSQL(TABLE_CREATE_SELLER);
        //db.execSQL(TABLE_CREATE_PRODUCT);
        this.db= db;
    }
    public void insertContact(Contact c)
    {
        db = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        String query = "select *from contacts";
        Cursor cursor= db.rawQuery(query,null);
        int count= cursor.getCount();
        cursor.close();
        values.put(CONTACTS_COLUMN_ID,count);
        values.put(CONTACTS_COLUMN_NAME,c.name);
        values.put(CONTACTS_COLUMN_COMPANY,c.company);
        values.put(CONTACTS_COLUMN_EMAIL,c.email);
        values.put(CONTACTS_COLUMN_PASS,c.pass);
        values.put(CONTACTS_COLUMN_CONFIRM,c.confirmpass);
        values.put(CONTACTS_COLUMN_PROFILE,c.imageByte);
        db.insert(TABLE_CONTACTS_NAME,null,values);
        db.close();

    }

    public String searchPass(String email)
    {
        db = this.getReadableDatabase();
        String query="select email,pass from "+TABLE_CONTACTS_NAME;
        Cursor cursor= db.rawQuery(query,null);
        String a,b;
        b="not found password";
        try{
        if(cursor.moveToFirst())
        {
            do{
                a= cursor.getString(0);
                if(a.equals(email))
                {
                    b= cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());

        }}
        finally {
            if (cursor != null && !cursor.isClosed())
            cursor.close();
            db.close();
        }
        return b;
    }
    public String searchEmail(String email) {
        db = this.getReadableDatabase();
        String query = "select email from " + TABLE_CONTACTS_NAME;
        String a,b;
        b="not found";
        Cursor cursor= db.rawQuery(query,null);
        try{
        if(cursor.moveToFirst())
        {
            do{
                a= cursor.getString(0);
                if(a.equals(email)){
                    b= a;
                    break;
                }

            }while(cursor.moveToNext());

    }}
        finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
            db.close();
        }
    return b;
    }
    public byte[] getProfileImageByte(String email) {
        db = this.getReadableDatabase();
        String query = "select email,profile from " + TABLE_CONTACTS_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a;
       byte[] b=null;
        try{
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                if (a.equals(email)) {
                  b = cursor.getBlob(1);
                    break;
                }
            } while (cursor.moveToNext());

        }}
        finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
            db.close();
        }
        return b;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query= "DROP TABLE IF EXISTS"+TABLE_CONTACTS_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public static DatabaseSql getInstance(Context ctx) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (mInstance == null) {
            mInstance = new DatabaseSql(ctx.getApplicationContext());
        }
        return mInstance;
    }


}
