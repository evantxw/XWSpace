package com.evan.xwspace;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.evan.tools.view.TXListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TWListViewActivity extends AppCompatActivity {
    private TXListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twlist_view);
        listView = (TXListView) findViewById(R.id.txlistview);
        // 不添加也有默认的头和底
        View topView = View.inflate(this, R.layout.top, null);
        listView.addHeaderView(topView);
//        View bottomView=new View(getApplicationContext());
//        listView.addFooterView(bottomView);

        // 顶部和底部也可以固定最终的高度 不固定就使用布局本身的高度
//        listView.setFinalBottomHeight(100);
        listView.setFinalTopHeight(100);

        List<Map<String, String>> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("title", "content" + i);
            data.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, data, android.R.layout.simple_list_item_1, new String[]{"title"}, new int[]{android.R.id.text1});

        listView.setAdapter(adapter);

        //YLListView默认有头和底  处理点击事件位置注意减去
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                position = position - listView.getHeaderViewsCount();
            }
        });
    }
}
