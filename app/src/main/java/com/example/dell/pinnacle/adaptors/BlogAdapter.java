package com.example.dell.pinnacle.adaptors;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dell.pinnacle.R;
import com.example.dell.pinnacle.activities.BlogText;
import com.example.dell.pinnacle.models.BlogDetails;

import java.util.List;

/**
 * Created by dell on 3/13/2018.
 */

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.BlogAdapterView> {
    Context mctx;
    List<BlogDetails> blogDetailsList;

    public BlogAdapter(Context mctx, List<BlogDetails> blogDetailsList) {
        this.mctx = mctx;
        this.blogDetailsList = blogDetailsList;

    }

    @Override
    public BlogAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_card,parent,false);
        BlogAdapterView blogAdapterView = new BlogAdapterView(view,mctx,blogDetailsList);
        return blogAdapterView;
    }

    @Override
    public void onBindViewHolder(BlogAdapterView holder, int position) {
        BlogDetails blogDetails = blogDetailsList.get(position);
        holder.blog.setText(blogDetails.getBlog());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mctx, BlogText.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("blog",blogDetails.getBlog());
                mctx.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return blogDetailsList.size();
    }

    public class BlogAdapterView extends RecyclerView.ViewHolder {
        TextView blog;
        RelativeLayout relativeLayout ;
        public BlogAdapterView(View itemView,Context mctx,List<BlogDetails>blogDetailsList) {
            super(itemView);
            blog = itemView.findViewById(R.id.textViewBlog);
            relativeLayout = itemView.findViewById(R.id.blog_id);
        }
    }
}
