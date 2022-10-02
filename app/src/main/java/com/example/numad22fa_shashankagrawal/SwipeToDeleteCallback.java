package com.example.numad22fa_shashankagrawal;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;


public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {
    private UrlAdapter mAdapter;

    public SwipeToDeleteCallback(UrlAdapter adapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        mAdapter = adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        mAdapter.deleteItem(position);

//        Snackbar.make(findViewById(R.id.url_recycler_view, "Successfully Deleted", Snackbar.LENGTH_SHORT)
//                .setAction("Action", null).show();
    }
}
