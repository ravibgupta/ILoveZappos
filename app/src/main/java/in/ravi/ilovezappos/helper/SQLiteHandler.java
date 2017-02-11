package in.ravi.ilovezappos.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ilovezappos";

    // Scan table name
    private static final String TABLE_CART = "cart";

    // Scan Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_BRAND = "brand";
    private static final String KEY_PRODUCT = "product";
    private static final String KEY_PRICE = "price";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_IMAGE_TABLE = "CREATE TABLE " + TABLE_CART + "("
                + KEY_ID + " TEXT," + KEY_IMAGE + " TEXT," + KEY_BRAND + " TEXT," + KEY_PRODUCT + " TEXT," + KEY_PRICE + " TEXT" + ")";
        db.execSQL(CREATE_IMAGE_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing cart details in database
     */
    public void addToCart(String id, String image, String brand, String product, String price) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_IMAGE, image);
        values.put(KEY_BRAND, brand);
        values.put(KEY_PRODUCT, product);
        values.put(KEY_PRICE, price);

        // Inserting Row
        long idd = db.insert(TABLE_CART, null, values);
        db.close(); // Closing database connection

    }

    /**
     * Getting cart data from database
     */
    public HashMap<String, String> getCartDetails(int position) {
        HashMap<String, String> image = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_CART;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.move(position + 1);
        if (cursor.getCount() > 0) {
            image.put("id", cursor.getString(0));
            image.put("image", cursor.getString(1));
            image.put("brand", cursor.getString(2));
            image.put("product", cursor.getString(3));
            image.put("price", cursor.getString(4));
        }
        cursor.close();
        db.close();

        return image;
    }

    public int getTableSize() {
        String selectQuery = "SELECT  * FROM " + TABLE_CART;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor.getCount();
    }

    public void deleteFromCart(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TABLE_CART, "id = ?", new String[]{id});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    /**
     * Re crate database Delete all tables and create them again
     */
    public void clearCart() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_CART, null, null);
        db.close();

        Log.d(TAG, "Deleted all img info from sqlite");
    }

}