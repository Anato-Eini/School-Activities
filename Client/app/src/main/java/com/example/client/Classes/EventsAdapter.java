package com.example.client.Classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.Entities.Event;
import com.example.client.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {
    private List<Event> eventsList;

    public EventsAdapter(List<Event> eventsList) {
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventsList.get(position);
        // Bind event data to views in the ViewHolder
        holder.bind(event);
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView title_txt_;
        TextView description_txt_;
        ImageView image_img_;

        public EventViewHolder(@NonNull View viewItem) {
            super(viewItem);
            title_txt_ = viewItem.findViewById(R.id.title_txt_);
            description_txt_ = viewItem.findViewById(R.id.description_txt);
            image_img_ = viewItem.findViewById(R.id.image_img_);
        }

        public void bind(Event event) {
            title_txt_.setText(event.title);
            description_txt_.setText(event.description);
            Picasso.get()
                    .load(event.image)
                    .into(image_img_);
        }
    }
}