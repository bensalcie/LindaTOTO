package com.example.lindatoto.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lindatoto.R;
import com.example.lindatoto.ui.home.MyVideos;
import com.squareup.picasso.Picasso;

import java.util.List;


public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder>  {

    private Context mCtx;
    private List<MyNotification> routesList;

    public NotificationsAdapter(Context mCtx, List<MyNotification> routesList) {
        this.mCtx = mCtx;
        this.routesList = routesList;
    }

    @Override
    public NotificationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.not_item, null);

        return new NotificationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NotificationsViewHolder holder, final int position) {

        final MyNotification video = routesList.get(position);

        holder.tvNotDate.setText(video.getDate());
        holder.tvMessage.setText(video.getMessage());
        holder.tvFooter.setText("On :"+video.getPostedon()+" | Category :"+video.getCategory());
    }

    @Override
    public int getItemCount() {

        return routesList.size();
    }
    class NotificationsViewHolder extends RecyclerView.ViewHolder {
        TextView tvNotDate,tvMessage,tvFooter;
        public NotificationsViewHolder(View itemView) {
            super(itemView);
            tvNotDate=itemView.findViewById(R.id.tvNotDate);
            tvMessage=itemView.findViewById(R.id.tvMessage);
            tvFooter=itemView.findViewById(R.id.tvFooter);


        }
    }
}
