package com.example.lindatoto.ui.home;

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
import com.squareup.picasso.Picasso;

import java.util.List;


public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideosViewHolder>  {

    private Context mCtx;
    private List<MyVideos> routesList;

    public VideosAdapter(Context mCtx, List<MyVideos> routesList) {
        this.mCtx = mCtx;
        this.routesList = routesList;
    }

    @Override
    public VideosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.video_item_new, null);

        return new VideosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VideosViewHolder holder, final int position) {

        final MyVideos video = routesList.get(position);

        holder.tvTitle.setText(video.getTitle());

        holder.tvDescription.setText(String.format("  %s  ", video.getDescription()));


        final String videoid=video.getVideoid();


        if (!TextUtils.isEmpty(video.getThumb())){
           // holder.prodilePic.setImageURI(Uri.parse(route.getImage_url()));
            Picasso.get().load(video.getThumb()).placeholder(R.drawable.ic_baseline_ondemand_video_24).into(holder.prodilePic);
        }else {
            holder.prodilePic.setImageResource(R.drawable.ic_baseline_ondemand_video_24);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent videoIntent = new Intent(holder.itemView.getContext(),DynamicActivity.class);
                videoIntent.putExtra("category","videos");
                videoIntent.putExtra("videoid",videoid);
                holder.itemView.getContext().startActivity(videoIntent);
            }
        });





    }

    @Override
    public int getItemCount() {

        return routesList.size();
    }
    class VideosViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle,tvDescription;
       ImageView prodilePic;


        public VideosViewHolder(View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            prodilePic=itemView.findViewById(R.id.prfilePic);
            tvDescription=itemView.findViewById(R.id.tvDescription);


        }
    }
}
