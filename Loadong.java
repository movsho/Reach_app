package com.reach.reach_app_v10;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;


public class Loadong extends Activity {

    private static final int PROGRESS = 0x1;

    private ProgressBar mProgress;
    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();

    protected void onCreate(Bundle icicle) {

        super.onCreate(icicle);

        setContentView(R.layout.activity_loadong);

        mProgress = (ProgressBar) findViewById(R.id.progressBarloading);

        // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while (mProgressStatus < 100) {
                    i++;
                    mProgressStatus = i;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
                            System.out.print("InHandler");
                            mProgress.setProgress(mProgressStatus);
                            if (mProgressStatus == 100) {

                                Intent intent = new Intent(getApplicationContext(), Feed.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        }).start();

    }
}
