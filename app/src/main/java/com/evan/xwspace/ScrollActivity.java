package com.evan.xwspace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ViewUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.evan.xwspace.view.ExpandListView;

import org.xutils.HttpManager;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScrollActivity extends AppCompatActivity {

    ScrollView scrollView;
    ExpandListView expandListView;
    TextView scroll_tv;
    Button scroll_btn;
    List<Map<String, String>> data;
    SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        x.view().inject(this);

        scrollView = (ScrollView) findViewById(R.id.scroll_scrollView);
        expandListView = (ExpandListView) findViewById(R.id.scroll_expand_lv);
        scroll_btn = (Button) findViewById(R.id.scroll_btn);
        scroll_tv = (TextView) findViewById(R.id.scroll_tv);

        data = new ArrayList<>();

        adapter = new SimpleAdapter(this, data, android.R.layout.simple_list_item_1, new String[]{"title"}, new int[]{android.R.id.text1});
        expandListView.setAdapter(adapter);

        loadData();

        scroll_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.smoothScrollBy(0, 500);
            }
        });

        scroll_tv.post(new Runnable() {
            @Override
            public void run() {
                scrollView.smoothScrollBy(0, 10);
            }
        });

    }

    private void loadData() {
        for (int i = 0; i < 5; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("title", "content" + i);
            data.add(map);
        }

        adapter.notifyDataSetChanged();

        RequestParams params = new RequestParams("http://www.baidu.com");

        Callback.Cancelable cancelable = x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                scroll_tv.setText(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

}
