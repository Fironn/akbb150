
package com.example.i80486.foodmanagement;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class Searching extends AppCompatActivity {

    protected TextView inputText;
    protected TextView resultText;
    protected TextView sameText;
    protected Button searchButton;
    private SQLiteHelper helper;
    private SQLiteDatabase db;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment1);

        inputText  = findViewById(R.id.input);
        resultText = findViewById(R.id.result);
        sameText = findViewById(R.id.same);
        searchButton = findViewById(R.id.search);

        if(helper == null){
            helper = new SQLiteHelper(getApplicationContext());
        }

        if(db == null) {
            db = helper.getWritableDatabase();


            insertData(db, "太郎","ニンジン",10,4);
            insertData(db, "花子", "大根",4,7);
            insertData(db, "雄二", "玉ねぎ", 4,4);
            insertData(db, "一郎", "ねぎ", 8,16);


        }
        readData();
    }
    private void readData() {
        if (helper == null) {
            helper = new SQLiteHelper(getApplicationContext());
        }

        if (db == null) {
            db = helper.getReadableDatabase();
        }
        Log.d("debug", "**********Cursor");



        Cursor cursor = db.query(
                "data2",
                new String[]{"person_name", "name", "deadLine1","deadLine2"},
                null,
                null,
                null,
                null,
                null
        );
        Log.d("debug", "Cursor");


        cursor.moveToFirst();

        StringBuilder sbuilder = new StringBuilder();

        for (int i = 0; i < cursor.getCount(); i++) {
            sbuilder.append(cursor.getString(0));
            sbuilder.append(": ");
            sbuilder.append(cursor.getString(1));
            sbuilder.append(":");
            sbuilder.append(cursor.getInt(2));
            sbuilder.append("/");
            sbuilder.append(cursor.getInt(3));
            sbuilder.append("\n");
            cursor.moveToNext();
        }
        String res = cursor.getString(1);
        if(res.equals(inputText.getText().toString())){
            sameText.setText(res);
        }
        else{
            sameText.setText("");
        }

        // 忘れずに！
        cursor.close();

        resultText.setText(sbuilder.toString());
    }

    private void insertData(SQLiteDatabase db, String name, String person_name,int deadLine1,int deadLine2){

        ContentValues values = new ContentValues();
        values.put("person_name", person_name);
        values.put("name", name);
        values.put("deadLine1", deadLine1);
        values.put("deadLine1", deadLine2);

        db.insert("data2", null, values);
    }


}
