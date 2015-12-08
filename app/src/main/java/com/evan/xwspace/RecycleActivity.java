package com.evan.xwspace;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evan on 2015/12/7.
 */
public class RecycleActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PersonAdapter adapter;

    List<Person> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle);

        recyclerView = (RecyclerView) findViewById(R.id.recycleView);

        recyclerView.setHasFixedSize(true);

//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(layoutManager);

        list = new ArrayList<>();
        initData();

        adapter = new PersonAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnRecycleListener(new PersonAdapter.OnRecycleListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(RecycleActivity.this, "position:" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(int position) {
                Toast.makeText(RecycleActivity.this, "Long position:" + position, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    private void initData() {
        for (int i = 0; i < 30; i++) {
            Person person = new Person();
            person.setName("evan" + i);
            person.setAge(10 + i);
            list.add(person);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
