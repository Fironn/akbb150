package com.example.i80486.foodmanagement;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main2Activity extends AppCompatActivity
        implements View.OnClickListener,FragmentTabHost.OnTabChangeListener{  //クリックリスナーを実装

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int seconds;
    protected ListView listView;
    protected TextView titleText;
    private String[] names = {"taro", "jiro", "saburo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.floatingActionButton2).setOnClickListener(this);  //リスナーをボタンに登録



        FragmentTabHost tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.container);

        TabHost.TabSpec tabSpec1, tabSpec2;

        // TabSpec を生成する
        tabSpec1 = tabHost.newTabSpec("tab1");
        tabSpec1.setIndicator("List");
        // TabHost に追加
        tabHost.addTab(tabSpec1, Fragment1.class, null);

        // TabSpec を生成する
        tabSpec2 = tabHost.newTabSpec("tab2");
        tabSpec2.setIndicator("Search");
        // TabHost に追加
        tabHost.addTab(tabSpec2, Fragment2.class, null);

        // リスナー登録
        tabHost.setOnTabChangedListener(this);
        MyAdapter myAdapter=new MyAdapter();
        setUserName();
    }
//    public void setListView(ArrayList data) {
//
//        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
//
//        ListView listView = (ListView)findViewById(R.id.list);
//        listView.setAdapter(adapter);
//
//    }

    // ダイアログで入力した値をtextViewに入れる - ダイアログから呼び出される
    public void setTextView(String value) {
//        TextView textView = (TextView) findViewById(R.id.text_dialog);
//        textView.setText(value);
    }

    @Override
    public void onTabChanged(String tabId) {
        Log.d("onTabChanged", "tabId: " + tabId);
    }

    public void setUserName(){
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        TextView textView=(TextView) findViewById(R.id.username);

        textView.setText("Welcome "+name);
    }

    //ボタンが押された時の処理
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.button2:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.floatingActionButton2:


                // レイアウトインフレーターを取得
                LayoutInflater inflater = LayoutInflater.from(this);

// レイアウトXMLからビューを取得
                View viewDate = inflater.inflate(R.layout.change_date, null);

//                DatePicker datePicker = findViewById(R.id.dpp).getDatePicker();

// ビューを画面に反映
                // テキスト入力用Viewの作成
                final EditText editView = new EditText(Main2Activity.this,null);
                final EditText editView2 = new EditText(Main2Activity.this,null);
                final EditText editDate = new EditText(Main2Activity.this,null);
                final EditText editMonth = new EditText(Main2Activity.this,null);

                AlertDialog.Builder dialog = new AlertDialog.Builder(Main2Activity.this);

                //外枠とパーツの作成
                LinearLayout layout = new LinearLayout(getApplicationContext());

                //上から下にパーツを組み込む設定
                layout.setOrientation(LinearLayout.VERTICAL);
//, new LinearLayout.LayoutParams( 300, 70)
                //外枠にパーツを組み込む

                // 日付情報の初期設定
                Calendar calendar = Calendar.getInstance();
                this.year = calendar.get(Calendar.YEAR);
                this.month = calendar.get(Calendar.MONTH);
                this.day = calendar.get(Calendar.DAY_OF_MONTH);


                showUserSelectDateTime(editView2);

                // 日付設定ダイアログの作成
//                DatePickerDialog datePickerDialog = new DatePickerDialog(Main2Activity.this,R.style.MyAlertDialogStyle,
//                        new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int year, int month, int day) {
//
//                                String buffer = String.format("%4d/%02d/%02d",year,month+1,day);
//                                Main2Activity.this.year=year;
//                                Main2Activity.this.month=month;
//                                Main2Activity.this.day=day;
//
//                                Log.i("lightbox",buffer);
//                                showUserSelectDateTime(editView2);
//                            }
//                        }, year, month, day);
//
//                DatePicker datePicker = datePickerDialog.getDatePicker();
//
//                datePicker.init(year - 1, month  + 1, day + 5, new DatePicker.OnDateChangedListener() {
//                    @Override
//                    public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
//                        Main2Activity.this.year = year;
//                        Main2Activity.this.month = month;
//                        Main2Activity.this.day = day;
//
//                        showUserSelectDateTime(editView2);
//                    }
//                });
//
//                datePicker.setMinDate(calendar.getTimeInMillis());
//                datePicker.setCalendarViewShown(false);
//                datePicker.setSpinnersShown(true);
                // 日付設定ダイアログの表示

//                datePickerDialog.show();

                layout.addView(make_TextView("品名"));
                layout.addView(editView);
                layout.addView(make_TextView("賞味期限"));
                layout.addView(editMonth);
                layout.addView(editDate);

//                layout.addView(viewDate);
//                layout.addView(datePicker);


                //レイアウトをダイアログに設定
                dialog.setView(layout);

                // OKボタンの設定
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        String returnValue = editView.getText().toString();
                        String returnValue1 = editMonth.getText().toString();
                        String returnValue2 = editDate.getText().toString();
//                        int value=Integer.parseInt(returnValue);
//                        int value1=Integer.parseInt(returnValue1);
//                        int value2=Integer.parseInt(returnValue2);

                        if(returnValue.length()<=0){
                            returnValue="無名";
                        }
                        if(returnValue1.length()<=0) {
                            returnValue1=Integer.toString(month);
                        }
                        if (returnValue2.length()<= 0) {
                            returnValue2=Integer.toString(day);
                        }

                        setTextView(returnValue);

                        Intent intent = new Intent(Main2Activity.this, InsertData.class);
                        intent.putExtra("name", returnValue);
                        intent.putExtra("month", returnValue1);
                        intent.putExtra("day", returnValue2);

                        startActivity(intent);
                    }
                });

                // キャンセルボタンの設定
                dialog.setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // キャンセルボタンをタップした時の処理をここに記述
                    }
                });

                dialog.show();
                break;



        }
    }

    private void showUserSelectDateTime(EditText text){
        // Get TextView object which is used to show user pick date and time.
        TextView textView = text;

        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append(this.year);
        strBuffer.append("-");
        strBuffer.append(this.month+1);
        strBuffer.append("-");
        strBuffer.append(this.day);
        strBuffer.append(" ");

        textView.setText(strBuffer.toString());
        textView.setTextColor(Color.BLUE);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
    }

    private TextView make_TextView(String sMessage) {
        //テキストビューの生成
        TextView tv = new TextView(getApplicationContext());
        //メッセージの設定
        tv.setText(sMessage);
        return tv;
    }
}