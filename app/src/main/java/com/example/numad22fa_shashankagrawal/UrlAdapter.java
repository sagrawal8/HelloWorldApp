package com.example.numad22fa_shashankagrawal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class UrlAdapter extends RecyclerView.Adapter<UrlViewHolder>{
    private final List<Url> urls;
    private final Context context;

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
            Toast.makeText(context, urls.get(position).getName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        // Returns the size of the recyclerview that is the list of the arraylist.
        return urls.size();
    }
}
