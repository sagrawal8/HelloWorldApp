package com.example.numad22fa_shashankagrawal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Objects;

public class LinkCollectorActivity extends AppCompatActivity {

    RecyclerView urlRecyclerView;
    ArrayList<Url> urlList;
    //check if button has been clicked
    boolean flag_button_click = false;
    //check if dataset changed
    boolean flag_dataset_changed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);
        if(savedInstanceState == null) {
            urlList = new ArrayList<>();
        } else {
            urlList = savedInstanceState.getParcelableArrayList("urlList");
        }

        //View
        urlRecyclerView = findViewById(R.id.url_recycler_view);
        //Layout manager
        urlRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Adapter
        urlRecyclerView.setAdapter(new UrlAdapter(urlList, this));
        //Item Touch Helper
        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new SwipeToDeleteCallback((UrlAdapter) urlRecyclerView.getAdapter()));
        itemTouchHelper.attachToRecyclerView(urlRecyclerView);
        //FAB
        FloatingActionButton fab = findViewById(R.id.add_url_fab);
        //Listener
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Linear layout
                LinearLayout linearLayout = findViewById(R.id.add_url_linear_layout);
                //If button has been clicked ie. URL is being added
                if(!flag_button_click){
                    //Set visibility of recycler view to GONE
                    findViewById(R.id.url_recycler_view).setVisibility(View.GONE);
                    //Change FAB icon
                    fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_circle_24));
                    //Set button clicked to true
                    flag_button_click = true;
                    //Show adding URL page
                    linearLayout.setVisibility(View.VISIBLE);
                //After URL is added
                }else if(flag_button_click){
                    //Display recycler list
                    findViewById(R.id.url_recycler_view).setVisibility(View.VISIBLE);
                    //Change FAB icon
                    fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_add_circle_24));
                    //Set button clicked to false
                    flag_button_click = false;
                    //Set visibility of add page to invisible
                    linearLayout.setVisibility(View.GONE);
                    //Get name
                    String name = ((EditText)findViewById(R.id.plain_text_input_name)).getText().toString();
                    //Get link
                    String link = ((EditText)findViewById(R.id.plain_text_input_link)).getText().toString();
                    //Check if not empty and add
                    String message;
                    if(!name.matches("") && !link.matches("")) {
                        urlList.add(new Url(name, link));
                        flag_dataset_changed = true;
                        Objects.requireNonNull(urlRecyclerView.getAdapter()).notifyItemInserted(urlList.size() - 1);
                        message = "URL added.";
                        //Clear edit text fields
                        ((EditText)findViewById(R.id.plain_text_input_name)).getText().clear();
                        ((EditText)findViewById(R.id.plain_text_input_link)).setText("http://");
                        flag_dataset_changed = false;
                    } else {
                        message = "1 or more fields were empty.";
                    }
                    //Show snackbar
                    Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        outState.putParcelableArrayList("urlList", urlList);
        super.onSaveInstanceState (outState);
    }
}