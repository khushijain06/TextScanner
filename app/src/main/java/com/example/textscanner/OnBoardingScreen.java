package com.example.textscanner;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class OnBoardingScreen extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(new Step.Builder().setTitle("Capture Text with Ease")
                .setContent("Effortlessly capture text using your device’s camera!")
                .setBackgroundColor(Color.parseColor("#6C63FF")) // int background color
                .setDrawable(R.drawable.camera) // int top drawable
                .setSummary("Use the Camera button to quickly capture text from any document, book, or sign. Simply point and click to scan the text you need")
                .build());
        addFragment(new Step.Builder().setTitle("Edit Your Scanned Text")
                .setContent("Easily edit your scanned text to perfection")
                .setBackgroundColor(Color.parseColor("#6c63ff")) // int background color
                .setDrawable(R.drawable.file) // int top drawable
                .setSummary("The Edit button allows you to erase or modify any part of the scanned text. Make corrections or remove unwanted sections with a simple tap\")")
                .build());
        addFragment(new Step.Builder().setTitle("Copy and Share Content")
                .setContent("Quickly copy and share your text")
                .setBackgroundColor(Color.parseColor("#6c63ff")) // int background color
                .setDrawable(R.drawable.copy) // int top drawable
                .setSummary(" Use the Copy button to copy the edited text to your clipboard. Share it with others or paste it into any application you need")
                .build());
    }
        @Override
        public void finishTutorial() {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        @Override
        public void currentFragmentPosition ( int position){
        }

        @Override
        public void onPointerCaptureChanged ( boolean hasCapture){
            super.onPointerCaptureChanged(hasCapture);
        }
    }