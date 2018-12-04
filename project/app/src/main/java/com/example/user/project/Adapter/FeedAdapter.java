package com.example.user.project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.project.Interface.ItemClickListerner2;
import com.example.user.project.Model.RSSObject;
import com.example.user.project.R;

class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{


    public TextView textTitle, textPublishDate, textContent;
    public ItemClickListerner2 itemClickListener2;

    public FeedViewHolder(View itemView) {
        super(itemView);

        textTitle = itemView.findViewById(R.id.textTitle);
        textPublishDate = itemView.findViewById(R.id.textPublishDate);
        textContent = itemView.findViewById(R.id.textContent);

        itemView.setOnClickListener(this);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener2(ItemClickListerner2 itemClickListener2) {
        this.itemClickListener2 = itemClickListener2;
    }

    @Override
    public void onClick(View v) {

        itemClickListener2.onClick(v, getAdapterPosition(), false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener2.onClick(v,getAdapterPosition(), true);
        return false;
    }
}

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {

    private RSSObject rssObject;
    private Context context;
    private LayoutInflater inflater;

    public FeedAdapter(RSSObject rssObject, Context context) {
        this.rssObject = rssObject;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(R.layout.list_news_feed, parent, false);
        return new FeedViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {

        holder.textTitle.setText(rssObject.getItems().get(position).getTitle());
        holder.textPublishDate.setText(rssObject.getItems().get(position).getPubDate());
        holder.textContent.setText(rssObject.getItems().get(position).getContent());

        holder.setItemClickListener2(new ItemClickListerner2() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(!isLongClick){

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(rssObject.getItems().get(position).getLink()));
                    context.startActivity(browserIntent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {

        return rssObject.items.size();
    }
}
