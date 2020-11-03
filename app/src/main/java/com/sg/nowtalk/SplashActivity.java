package com.sg.nowtalk;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class SplashActivity extends AppCompatActivity {

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState );
        setContentView(R.layout.activity_splash);

        linearLayout = (LinearLayout)findViewById(R.id.splashactivity_linearlayout);
    }
}