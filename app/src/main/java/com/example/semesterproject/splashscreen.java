package com.example.semesterproject;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


import com.example.semesterproject.databinding.ActivitySplashscreenBinding;

public class splashscreen extends AppCompatActivity {


 public ImageView splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_splashscreen);
       splash= findViewById(R.id.splash_image);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash);
        splash.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
        Intent it  = new Intent(splashscreen.this, MainActivity.class);
        startActivity(it);
            }
        },3000);

    }

}