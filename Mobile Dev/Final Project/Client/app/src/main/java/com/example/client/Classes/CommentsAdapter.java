package com.example.client.Classes;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.client.Entities.Comment;
import com.example.client.R;

import org.w3c.dom.Text;

import java.util.List;

public class CommentsAdapter extends GenericAdapter<Comment> {

    public CommentsAdapter(List<Comment> commentsList, Context context) {
        super(commentsList, context);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.comment_item;
    }

    @Override
    protected GenericViewHolder<Comment> createViewHolder(View view, List<Comment> itemList, Context context) {
        return new CommentViewHolder(view, itemList, context);
    }

    static class CommentViewHolder extends GenericViewHolder<Comment> {
        TextView userName_txt_;
        TextView comment_txt_;
        TextView createdAt_txt_;
        public CommentViewHolder(@NonNull View itemView, List<Comment> commentsList, Context context) {
            super(itemView, commentsList, context);
//            userName_txt_ = itemView.findViewById(R.id.userName_txt_);
            comment_txt_ = itemView.findViewById(R.id.comment_txt_);
            createdAt_txt_ = itemView.findViewById(R.id.createdAt_txt_);
        }

        @Override
        public void bind(Comment comment) {
//            userName_txt_.setText(comment.getUserName());
            comment_txt_.setText(comment.comment);
            createdAt_txt_.setText(comment.createdAt.toString());
        }
    }
}