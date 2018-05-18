package com.example.dell.pinnacle.adaptors;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.pinnacle.R;
import com.example.dell.pinnacle.activities.Video;
import com.example.dell.pinnacle.models.SubjectDetails;

import java.util.List;

/**
 * Created by dell on 3/10/2018.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoAdapterView> {
    Context mctx;
    List<SubjectDetails> subjectDetailsList;

    public VideoAdapter(Context mctx, List<SubjectDetails> subjectDetailsList) {
        this.mctx = mctx;
        this.subjectDetailsList = subjectDetailsList;
    }

    @Override
    public VideoAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        VideoAdapterView videoAdapterView = new VideoAdapterView(view,mctx,subjectDetailsList);
        return videoAdapterView;
    }

    @Override
    public void onBindViewHolder(VideoAdapterView holder, int position) {
        SubjectDetails subjectDetails = subjectDetailsList.get(position);
        holder.title.setText(subjectDetails.getTitle());
        holder.description.setText(subjectDetails.getDececription());
        holder.url = subjectDetails.getUrl();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoUrl=holder.url;
                //Toast.makeText(mctx, videoUrl, Toast.LENGTH_SHORT).show();
                Bundle bn = new Bundle();
                bn.putString("videourl",videoUrl);
                Intent intent = new Intent(mctx, Video.class);
                intent.putExtras(bn);
                mctx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return subjectDetailsList.size();
    }

    public class VideoAdapterView extends RecyclerView.ViewHolder {
        TextView title, description;
        String url;

        public VideoAdapterView(View itemView,Context mctx, List<SubjectDetails>subjectDetailsList) {
            super(itemView);
            title = itemView.findViewById(R.id.textViewTitle);
            description = itemView.findViewById(R.id.textViewDescription);
        }
    }
}
