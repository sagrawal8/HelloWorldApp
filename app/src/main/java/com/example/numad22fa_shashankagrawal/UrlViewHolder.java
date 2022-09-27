package com.example.numad22fa_shashankagrawal;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UrlViewHolder extends RecyclerView.ViewHolder{
    public TextView name;
    public TextView link;

    public UrlViewHolder(@NonNull View itemView) {
        super(itemView);
        this.name = itemView.findViewById(R.id.name);
        this.link = itemView.findViewById(R.id.link);
    }
}
