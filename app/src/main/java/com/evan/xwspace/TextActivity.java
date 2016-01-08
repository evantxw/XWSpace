package com.evan.xwspace;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

public class TextActivity extends AppCompatActivity {

    private static final int MAX = 1;
    private static final int TIME = 20;

    private int maxLines;
    private TextView textView;
    private boolean hasMeasure = false;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        initView();
    }

    private void initView() {

        textView = (TextView) findViewById(R.id.textview);
        ViewTreeObserver treeObserver = textView.getViewTreeObserver();
        treeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (!hasMeasure) {
                    maxLines = textView.getLineCount();
                    textView.setMaxLines(MAX);
                    hasMeasure = true;
                }
                return true;
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });
    }

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int lines = msg.what;
            textView.setMaxLines(lines);
            textView.postInvalidate();
        }
    };

    private void toggle() {
        if (thread != null) {
            handler.removeCallbacks(thread);
        }

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = MAX;
                while (count++ <= maxLines) {
                    Message message = handler.obtainMessage();
                    message.what = count;
                    handler.sendMessage(message);
                    try {
                        Thread.sleep(TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();
    }

}
