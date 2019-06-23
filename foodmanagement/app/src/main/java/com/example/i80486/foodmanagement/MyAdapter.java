package com.example.i80486.foodmanagement;



import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MyAdapter extends Activity {

    protected ListView listView;
    protected TextView titleText;
    private String[] names = {"taro", "jiro", "saburo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment1);
        if (savedInstanceState == null) {
            titleText = (TextView) findViewById(R.id.title);
        }
        listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, names));
        listView.setOnItemClickListener(new SampleListItemClickListener(titleText));
    }

    static class SampleListItemClickListener implements ListView.OnItemClickListener {

        private final TextView textView;

        SampleListItemClickListener(TextView titleView) {
            this.textView = titleView;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ListView listView = (ListView) parent;
            String item = (String) listView.getItemAtPosition(position);
            textView.setText(item);
        }
    }
}
