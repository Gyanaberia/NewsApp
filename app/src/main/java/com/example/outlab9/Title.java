package com.example.outlab9;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Title extends RecyclerView.ViewHolder {
    TextView newsTitle;
    public Title(@NonNull View itemView) {
        super(itemView);
        newsTitle=(TextView)itemView.findViewById(R.id.title);
    }
}
