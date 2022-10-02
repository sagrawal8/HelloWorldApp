package com.example.numad22fa_shashankagrawal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class UrlAdapter extends RecyclerView.Adapter<UrlViewHolder>{
    private final List<Url> urls;
    private final Context context;
    private Url recentlyDeletedUrl;
    private int recentlyDeletedUrlPosition;

    /**
     * Creates a PersonAdapter with the provided arraylist of Person objects.
     *
     * @param urls   arraylist of url object.
     * @param context   context of the activity used for inflating layout of the viewholder.
     */
    public UrlAdapter(List<Url> urls, Context context) {
        this.urls = urls;
        this.context = context;
    }

    @NonNull
    @Override
    public UrlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create an instance of the viewholder by passing it the layout inflated as view and no root.
        return new UrlViewHolder(LayoutInflater.from(context).inflate(R.layout.item_url, null));
    }

    @Override
    public void onBindViewHolder(@NonNull UrlViewHolder holder, int position) {
        // sets the name of the person to the name textview of the viewholder.
        holder.name.setText(urls.get(position).getName());
        // sets the age of the person to the age textview of the viewholder.
        holder.link.setText(String.valueOf(urls.get(position).getLink()));

        // set a click event on the whole itemView (every element of the recyclerview).
        holder.itemView.setOnClickListener(view -> {
            String url = urls.get(position).getLink();
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url;
            }
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(browserIntent);
        });
    }

    @Override
    public int getItemCount() {
        // Returns the size of the recyclerview that is the list of the arraylist.
        return urls.size();
    }

    public void deleteItem(int position){
        recentlyDeletedUrl = urls.get(position);
        recentlyDeletedUrlPosition = position;
        urls.remove(position);
        notifyItemRemoved(position);
        showUndoSnackbar();
    }

    private void showUndoSnackbar() {
        RecyclerView recyclerView = (RecyclerView) ((Activity) context).findViewById(R.id.url_recycler_view);
        Snackbar snackbar = Snackbar.make(recyclerView, "Swipe to undo delete",
                Snackbar.LENGTH_LONG);
        snackbar.setAction("Undo", v -> undoDelete());
        snackbar.show();
    }

    private void undoDelete() {
        urls.add(recentlyDeletedUrlPosition,
                recentlyDeletedUrl);
        notifyItemInserted(recentlyDeletedUrlPosition);
    }
}
