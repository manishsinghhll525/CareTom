package com.caretom;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.caretom.beforeLoginModule.MainMenuScreen;
import com.caretom.beforeLoginModule.MenuScreen;


public class SplashScreen extends AppCompatActivity {
    private final int SPLASH_SCREEN_TIMEOUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setupRedirection();
    }

    private void setupRedirection() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainMenuScreen.class);
                startActivity(intent);
                finish();

            }
        }, SPLASH_SCREEN_TIMEOUT);
    }
}
