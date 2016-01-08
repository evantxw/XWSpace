package com.evan.xwspace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TMainListActivity extends AppCompatActivity {

    ListView listView;

    SimpleAdapter adapter;

    List<Map<String, String>> data;


    String[] titles = {"ScrollView自动上滚测试", "带弹簧效果的ListView"};

    String[] classNames = {ScrollActivity.class.getName(), TWListViewActivity.class.getName()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmain_list);

        data = new ArrayList<>();

        listView = (ListView) findViewById(R.id.listView);
        adapter = new SimpleAdapter(this, data, android.R.layout.simple_list_item_1, new String[]{"title"}, new int[]{android.R.id.text1});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, String> map = (Map<String, String>) parent.getItemAtPosition(position);
                Intent intent = new Intent();
                intent.setClassName(TMainListActivity.this, map.get("clazz"));
                startActivity(intent);
            }
        });

        loadData();

    }

    private void loadData() {

        for (int i = 0; i < titles.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("title", titles[i]);
            map.put("clazz", classNames[i]);
            data.add(map);
        }

        adapter.notifyDataSetChanged();
    }
}
