package com.evan.xwspace;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.File;
import java.net.URISyntaxException;

public class MapActivity extends AppCompatActivity {

    LinearLayout map_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        map_layout = (LinearLayout) findViewById(R.id.map_layout);

        String baiduPackageName = "com.baidu.BaiduMap";
        boolean isBaidu = isInstall(baiduPackageName);
        Log.i("MapActivity", "Baidu:" + String.valueOf(isBaidu));
        if (isBaidu) {
            Button button = new Button(this);
            button.setText("百度地图");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = Intent.getIntent("intent://map/marker?location=40.047669,116.313082&title=我的位置&content=百度奎科大厦&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                        startActivity(intent); //启动调用
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }

                }
            });
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            addContentView(button,lp);
            map_layout.addView(button, lp);
        } else {
            Log.i("MapActivity", "Google:" + String.valueOf(isBaidu));
        }

        String gaodePackageNAame = "com.autonavi.minimap";
        boolean isGaoDe = isInstall(gaodePackageNAame);
        if (isGaoDe) {
            Button button = new Button(this);
            button.setText("高德地图");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = Intent.getIntent("androidamap://viewMap?sourceApplication=厦门通&poiname=百度奎科大厦&lat=40.047669&lon=116.313082&dev=0");
                        startActivity(intent); //启动调用
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }

                }
            });
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            addContentView(button,lp);
            map_layout.addView(button, lp);
        } else {
            Log.i("MapActivity", "Google:" + String.valueOf(isGaoDe));
        }

        String googlePackageName = "com.google.android.apps.maps";
        boolean isGoogle = isInstall(googlePackageName);
        if (isGoogle) {
            Button button = new Button(this);
            button.setText("谷歌地图");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ditu.google.cn/maps?hl=zh&mrt=loc&q=39.940409,116.355257(西直门)"));
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK & Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                    i.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                    startActivity(i);
                }
            });
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            map_layout.addView(button, lp);
//            addContentView(button,lp);
        } else {
            Log.i("MapActivity", "Google:" + String.valueOf(isGoogle));
        }

        String tencentPackageName = "com.tencent.map";
        boolean isTencent = isInstall(tencentPackageName);
        if (isTencent) {
            Button button = new Button(this);
            button.setText("腾讯地图");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://apis.map.qq.com/uri/v1/marker?marker=coord:39.892326,116.342763;title:超好吃冰激凌;addr:手帕口桥北铁路道口&referer=myapp"));
                    startActivity(i);
                }
            });
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            map_layout.addView(button, lp);
//            addContentView(button,lp);
        } else {
            Log.i("MapActivity", "Tencent:" + String.valueOf(isTencent));
        }

    }

    /**
     * 判断是否安装了应用
     *
     * @param packageName
     * @return
     */
    private boolean isInstall(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }


}
