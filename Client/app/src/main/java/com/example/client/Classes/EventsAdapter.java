package com.example.client.Classes;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client.Activities.EventDetails;
import com.example.client.Entities.Event;
import com.example.client.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EventsAdapter extends GenericAdapter<Event> {

    public EventsAdapter(List<Event> eventsList, Context context) {
        super(eventsList, context);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.event_item;
    }

    @Override
    protected GenericViewHolder<Event> createViewHolder(View view, List<Event> itemList, Context context) {
        return new EventViewHolder(view, itemList, context);
    }

    static class EventViewHolder extends GenericViewHolder<Event> {
        TextView title_txt_;
        TextView description_txt_;
        ImageView image_img_;

        public EventViewHolder(@NonNull View itemView, List<Event> eventsList, Context context) {
            super(itemView, eventsList, context);
            title_txt_ = itemView.findViewById(R.id.title_txt_);
            description_txt_ = itemView.findViewById(R.id.description_txt);
            image_img_ = itemView.findViewById(R.id.image_img_);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Event clickedEvent = itemList.get(position);
                        Intent intent = new Intent(context, EventDetails.class);
                        intent.putExtra("id", clickedEvent.id.toString());
                        intent.putExtra("title", clickedEvent.title);
                        intent.putExtra("description", clickedEvent.description);
                        intent.putExtra("image", clickedEvent.image);
                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public void bind(Event event) {
            title_txt_.setText(event.title);
            description_txt_.setText(event.description);
            Picasso.get()
                    .load(event.image)
                    .into(image_img_);
        }
    }
}
