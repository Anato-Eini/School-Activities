package com.example.client.Classes;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public abstract class GenericAdapter<T> extends RecyclerView.Adapter<GenericAdapter.GenericViewHolder<T>> {
    protected List<T> itemList;
    protected Context context;

    public GenericAdapter(List<T> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public GenericViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getItemLayoutId(), parent, false);
        return createViewHolder(view, itemList, context);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder<T> holder, int position) {
        T item = itemList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    protected abstract int getItemLayoutId();

    protected abstract GenericViewHolder<T> createViewHolder(View view, List<T> itemList, Context context);

    public static abstract class GenericViewHolder<T> extends RecyclerView.ViewHolder {
        protected List<T> itemList;
        protected Context context;

        public GenericViewHolder(@NonNull View itemView, List<T> itemList, Context context) {
            super(itemView);
            this.itemList = itemList;
            this.context = context;
        }

        public abstract void bind(T item);
    }
}
