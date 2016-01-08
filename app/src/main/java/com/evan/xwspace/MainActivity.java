package com.evan.xwspace;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.evan.tools.view.BadgeView;
import com.evan.tools.view.ImageCycleView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BadgeView badgeView;

    ImageCycleView imageCycleView;

    private ArrayList<String> mImageUrl = null;

    private String imageUrl1 = "http://imgs.xiuna.com/xiezhen/2014-9-25/2/5562900520140919100645087.jpg";

    private String imageUrl2 = "http://imgs.xiuna.com/xiezhen/2013-3-20/1/12.jpg";

    private String imageUrl3 = "http://srimg1.meimei22.com/pic/suren/2014-9-24/1/8740_11329820378.jpg";

    private String imageUrl4 = "http://imgs.xiuna.com/xiezhen/2013-3-20/1/12.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageCycleView = (ImageCycleView) findViewById(R.id.ad_view);

        mImageUrl = new ArrayList<String>();
        mImageUrl.add(imageUrl1);
        mImageUrl.add(imageUrl2);
        mImageUrl.add(imageUrl3);
        mImageUrl.add(imageUrl4);

        imageCycleView = (ImageCycleView) findViewById(R.id.ad_view);
        imageCycleView.setImageResources(mImageUrl, cycleViewListener);

        TextView textView = (TextView) findViewById(R.id.helloworld);

        badgeView = new BadgeView(this, textView);
        badgeView.show();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG);
                snackbar.setAction("Action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "snackbar is test click showing", Toast.LENGTH_SHORT).show();
                    }
                });
                snackbar.show();
                startActivity(new Intent(MainActivity.this, Text.class));
            }
        });
    }

    ImageCycleView.ImageCycleViewListener cycleViewListener = new ImageCycleView.ImageCycleViewListener() {
        @Override
        public void displayImage(String imageURL, ImageView imageView) {
            imageView.setImageResource(R.drawable.banner_dian_focus);
//              Toast.makeText(MainActivity.this, imageURL, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onImageClick(int position, View imageView) {
            Snackbar.make(imageView, "position-->" + position, Snackbar.LENGTH_SHORT).show();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
