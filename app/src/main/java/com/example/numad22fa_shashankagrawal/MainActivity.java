package com.example.numad22fa_shashankagrawal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void aboutMe(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Name: Shashank Agrawal\nEmail: agrawal.sha@northeastern.edu";
        int duration = Toast.LENGTH_LONG;
        Toast.makeText(context, text, duration).show();
    }

    public void clickyClacky(View view){
        Intent intent = new Intent(this, ClickyClackyActivity.class);
        startActivity(intent);
    }

}