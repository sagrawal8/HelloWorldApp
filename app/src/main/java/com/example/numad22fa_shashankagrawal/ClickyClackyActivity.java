package com.example.numad22fa_shashankagrawal;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClickyClackyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky_clacky);
    }

    public void pressedButton(View view) {
        Button button = findViewById(view.getId());
        TextView textView = findViewById(R.id.textPressed);
        textView.setText(getString(R.string.pressed_other, button.getText()));
    }
}