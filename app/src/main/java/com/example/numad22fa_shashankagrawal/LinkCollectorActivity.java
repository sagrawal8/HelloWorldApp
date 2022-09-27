package com.example.numad22fa_shashankagrawal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Objects;

public class LinkCollectorActivity extends AppCompatActivity {

    RecyclerView urlRecyclerView;
    ArrayList<Url> urlList;
    boolean flag_button_click = false;
    boolean flag_dataset_changed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        urlList = new ArrayList<>();
        urlList.add(new Url("Google", "https://google.com"));
        urlList.add(new Url("Yahoo", "https://yahoo.com"));
        urlRecyclerView = findViewById(R.id.url_recycler_view);
        urlRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        urlRecyclerView.setAdapter(new UrlAdapter(urlList, this));

        FloatingActionButton fab = findViewById(R.id.add_url_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrameLayout f = findViewById(R.id.add_url_frame_layout);
                if(!flag_button_click){
                    fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_circle_24));
                    flag_button_click = true;
                    f.setVisibility(View.VISIBLE);
                    String name = ((EditText)findViewById(R.id.plain_text_input_name)).getText().toString();
                    String link = ((EditText)findViewById(R.id.plain_text_input_link)).getText().toString();
                    if(!name.matches("") && !link.matches("")) {
                        urlList.add(new Url("Amazon", link));
                        flag_dataset_changed = true;
                    }
                }else if(flag_button_click){
                    fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_add_circle_24));
                    flag_button_click = false;
                    f.setVisibility(View.INVISIBLE);
                    String message;
                    if(flag_dataset_changed) {
                        Objects.requireNonNull(urlRecyclerView.getAdapter()).notifyItemInserted(urlList.size() - 1);
                        message = "URL added.";
                    } else {
                        message = "1 or more fields were empty or null.";
                    }
                    Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
            }
        });
    }
}