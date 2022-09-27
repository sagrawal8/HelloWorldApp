package com.example.numad22fa_shashankagrawal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void aboutMe(View view) {
        CharSequence text = "Name: Shashank Agrawal\nEmail: agrawal.sha@northeastern.edu";
        Intent intent = new Intent(this, AboutMeActivity.class);
        intent.putExtra("ABOUT_ME", text);
        startActivity(intent);
    }

    public void clickyClacky(View view){
        Intent intent = new Intent(this, ClickyClackyActivity.class);
        startActivity(intent);
    }

    public void linkCollector(View view){
        Intent intent = new Intent(this, LinkCollectorActivity.class);
        startActivity(intent);
    }

}