package com.example.waaaaaaaa123.thetowerdefence;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.waaaaaaaa123.thetowerdefence.button.ExitButton;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        ExitButton.setActivity(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
