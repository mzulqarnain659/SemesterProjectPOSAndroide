package com.example.semesterproject;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;


import com.example.semesterproject.databinding.ActivitySplashscreenBinding;

public class splashscreen extends AppCompatActivity {


 public ImageView splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_splashscreen);
        ImageView imageView = findViewById(R.id.splash_image);

        ScaleAnimation zoomInAnimation = new ScaleAnimation(
                1.0f, 1.5f, // Start and end scale X
                1.0f, 1.5f, // Start and end scale Y
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot X coordinate (center of the image)
                Animation.RELATIVE_TO_SELF, 0.5f // Pivot Y coordinate (center of the image)
        );
        zoomInAnimation.setDuration(1000); // Animation duration: 1 second

        ScaleAnimation zoomOutAnimation = new ScaleAnimation(
                1.5f, 1.0f, // Start and end scale X
                1.5f, 1.0f, // Start and end scale Y
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot X coordinate (center of the image)
                Animation.RELATIVE_TO_SELF, 0.5f // Pivot Y coordinate (center of the image)
        );
        zoomOutAnimation.setDuration(1000); // Animation duration: 1 second
        zoomOutAnimation.setStartOffset(1000); // Start animation after the zoom-in animation

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(zoomInAnimation);
        animationSet.addAnimation(zoomOutAnimation);

        imageView.startAnimation(animationSet);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
        Intent it  = new Intent(splashscreen.this, MainActivity.class);
        startActivity(it);
        finish();
            }
        },2000);

    }

}