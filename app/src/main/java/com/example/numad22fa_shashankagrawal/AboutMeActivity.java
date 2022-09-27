package com.example.numad22fa_shashankagrawal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutMeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        Intent intent = getIntent();
        CharSequence aboutMeMsg = intent.getStringExtra("ABOUT_ME");
        TextView textView = findViewById(R.id.about_me);
        textView.setText(aboutMeMsg);
    }
}
