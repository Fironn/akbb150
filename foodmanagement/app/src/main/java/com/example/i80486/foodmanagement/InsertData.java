package com.example.i80486.foodmanagement;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class InsertData extends AppCompatActivity {
    TextView textView;
    protected Button deleteButton;
    protected Button loadButton;
    private SQLiteHelper helper;
    private SQLiteDatabase db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment1);
        textView = (TextView) findViewById(R.id.text_view);


        textView = findViewById(R.id.text_view);
        loadButton = findViewById(R.id.Load_button);
        deleteButton = findViewById(R.id.Delete_button);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int month = Integer.parseInt(intent.getStringExtra("month"));
        int day = Integer.parseInt(intent.getStringExtra("day"));



        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readData();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                db.delete("data1",null,null);
            }
        });


        if(helper == null){
            helper = new SQLiteHelper(getApplicationContext());
        }

        if(db == null){
            db = helper.getWritableDatabase();
        }

        insertData(db,name,month,day);

        /*backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(this,);
                startActivity(intent);
            }
        });*/
    }
    private void readData() {
        if (helper == null) {
            helper = new SQLiteHelper(getApplicationContext());
        }

        if (db == null) {
            db = helper.getReadableDatabase();
        }
        Log.d("debug", "**********Cursor");

        String order_by = "deadLine2 ASC";
        Cursor cursor = db.query(
                "data1",
                new String[]{"name","deadLine1","deadLine2"},
                null,
                null,
                null,
                null,
                order_by

        );


        cursor.moveToFirst();

        StringBuilder sbuilder = new StringBuilder();

        for (int i = 0; i < cursor.getCount(); i++) {
            sbuilder.append(cursor.getString(0));
            sbuilder.append(": ");
            sbuilder.append(cursor.getInt(1));
            sbuilder.append("/");
            sbuilder.append(cursor.getInt(2));
            sbuilder.append("\n");
            cursor.moveToNext();
        }

        // 忘れずに！
        cursor.close();

        textView.setText(sbuilder.toString());
    }
    private void insertData(SQLiteDatabase db, String name, int deadLine1,int deadLine2){

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("deadLine1", deadLine1);
        values.put("deadLine2", deadLine2);

        db.insert("data1", null, values);
    }

}
