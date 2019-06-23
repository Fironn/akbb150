
package com.example.i80486.foodmanagement;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;

    private static final String DB_NAME = "sample";



    private static final String CREATE_TABLE_CHARACTER =
            "CREATE TABLE data1 ("+
                    "name Text, " +
                    "deadLine1 Integer NOT NULL, "+
                    "deadLine2 Integer NOT NULL)";




    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        //context.deleteDatabase("sample");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CHARACTER);

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
