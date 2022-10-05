package com.example.numad22fa_shashankagrawal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class FindPrimeActivity extends AppCompatActivity {
    private Handler mHandler;
    private Thread x = null;
    private long currentNum ;
    private long latestPrime;
    boolean restoredFromBundle;
    boolean terminatedSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_prime);
        if(savedInstanceState != null){
            currentNum = savedInstanceState.getLong("current_num");
            latestPrime = savedInstanceState.getLong("latest_prime");
            ((TextView)findViewById(R.id.latest_prime)).setText(String.valueOf(latestPrime));
            ((TextView)findViewById(R.id.current_number)).setText(String.valueOf(currentNum));
            if(savedInstanceState.containsKey("current_num_for_search")) {
                currentNum = savedInstanceState.getLong("current_num_for_search");
            }
            restoredFromBundle = true;
            if(savedInstanceState.getBoolean("search_running"))
            {
                runThread();
            }
        } else {
            currentNum = 3;
            latestPrime = 3;
            restoredFromBundle = false;
        }
        mHandler = new Handler();
        terminatedSearch = false;
    }

    public void runThread() {
        if(x!=null){
            return;
        }
        if(!restoredFromBundle || terminatedSearch) {
            currentNum = 3;
            latestPrime = 3;
        }
        terminatedSearch = false;
        //Start new thread
        x = new Thread(new Runnable() {
            private long currentNum = FindPrimeActivity.this.currentNum;
            private final TextView latestPrimeTextView = findViewById(R.id.latest_prime);
            private final TextView currentNumberTextView = findViewById(R.id.current_number);
            long timePosted = new Date().getTime();
            @Override
            public void run() {
                int i;
                while(!Thread.currentThread().isInterrupted()) {
                    for (i = 2; i <= currentNum / 2; i++) {
                        if ((currentNum % i) == 0) {
                            break;
                        }
                    }
                    if (i > currentNum / 2) {
                        latestPrime = currentNum;
                        FindPrimeActivity.this.latestPrime = latestPrime;
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                latestPrimeTextView.setText(String.valueOf(latestPrime));
                                if(new Date().getTime() - timePosted >= 200){
                                    currentNumberTextView.setText(String.valueOf(currentNum));
                                    timePosted = new Date().getTime();
                                }
                            }
                        });

                    }
                    currentNum += 2;
                    FindPrimeActivity.this.currentNum = currentNum;
                }
            }
        });
        x.start();
    }

    public void startPrimeSearch(View view){
        runThread();
    }

    public void endPrimeSearch(View view) {
        if(x != null) {
            x.interrupt();
            x = null;
            ((TextView)findViewById(R.id.current_number)).setText(String.valueOf(currentNum));
            terminatedSearch = true;
        }

    }

    @Override
    public void onBackPressed() {
        if(x != null && x.isAlive()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do you want to exit the activity?");

            // if user presses "Exit", then he is allowed to exit from application
            builder.setPositiveButton("Terminate Search", (dialog, which) -> finish());

            // if user selects "Stay", just cancel this dialog and continue with app
            builder.setNegativeButton("Stay", (dialog, which) -> dialog.cancel());

            builder.create().show();
        }
        else {
            finish();
        }
    }

    protected void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putLong("latest_prime", latestPrime);
        bundle.putLong("current_num", currentNum);
        bundle.putBoolean("search_running", x != null);
        if(terminatedSearch){
            bundle.putLong("latest_prime_for_search", 3);
            bundle.putLong("current_num_for_search", 3);
        }
    }
}