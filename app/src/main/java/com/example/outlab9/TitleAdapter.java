package com.example.outlab9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TitleAdapter extends RecyclerView.Adapter<Title> {
    List<String> list;
    public TitleAdapter(List<String>l){
        list=l;
    }
    @NonNull
    @Override
    public Title onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the layout
        View photoView = inflater.inflate(R.layout.title, parent, false);//not photoView
        return new Title(photoView);
    }

    @Override
    public void onBindViewHolder(@NonNull Title holder, int position) {
        holder.newsTitle.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(
            @NotNull RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
