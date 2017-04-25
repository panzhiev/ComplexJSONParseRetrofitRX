package com.example.panzhiev.complexjsonparseretrofitrx.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.panzhiev.complexjsonparseretrofitrx.R;
import com.example.panzhiev.complexjsonparseretrofitrx.model.NewsItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Panzhiev on 25.04.2017.
 */

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolderNews> {

    private Context mContext;
    private List<NewsItem> mNewsItems;

    public NewsRecyclerAdapter(Context context, ArrayList newsItems) {
        this.mContext = context;
        this.mNewsItems = newsItems;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public NewsRecyclerAdapter.ViewHolderNews onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        ViewHolderNews holder = new ViewHolderNews(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NewsRecyclerAdapter.ViewHolderNews holder, int position) {

        final NewsItem newsItem = (NewsItem) mNewsItems.get(position);

        holder.tvName.setText(newsItem.getHeadLine());
        Picasso.with(holder.imageView.getContext())
                .load(newsItem.getNewsImage().getPhoto())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mNewsItems.size();
    }

    public class ViewHolderNews extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView tvName;

        public ViewHolderNews(View itemView) {
            super(itemView);
            this.tvName = (TextView) itemView.findViewById(R.id.text_view_headline);
            this.imageView = (ImageView) itemView.findViewById(R.id.profile_image);
        }
    }
}
