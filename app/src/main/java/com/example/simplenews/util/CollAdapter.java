package com.example.simplenews.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.simplenews.R;
import com.example.simplenews.gson.Colltects;

import java.util.List;

/**
 * Created by Administrator on 2019/12/3.
 */
public class CollAdapter extends RecyclerView.Adapter<CollAdapter.ViewHolder> {
    private List<Colltects> mcolltectses;
    private Context context;


    public class ViewHolder extends RecyclerView.ViewHolder {
        View colltectView;
        TextView title;
        TextView author_name;
        TextView date;
        public ViewHolder(View itemView) {
            super(itemView);
            colltectView = itemView;
            title = (TextView) itemView.findViewById(R.id.coll_title);
            author_name = (TextView) itemView.findViewById(R.id.coll_author_name);
            date = (TextView) itemView.findViewById(R.id.coll_date);
        }


    }
    public CollAdapter(Context context,List<Colltects> colltectses){
        mcolltectses = colltectses;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.colltect_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.colltectView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Colltects people = mcolltectses.get(position);
//                Intent intent = new  Intent(context,SecondActivity.class);
//                intent.putExtra("name",people.getName());
//                intent.putExtra("imageId",people.getImageId()+"");
//                intent.putExtra("content",people.getContent());
//                context.startActivity(intent);
            }
        });
        return  holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Colltects coll = mcolltectses.get(position);
        holder.title.setText(coll.getTitle());
        holder.author_name.setText(coll.getAuthor_name());
        holder.date.setText(coll.getDate());
    }

    @Override
    public int getItemCount() {
        return mcolltectses.size();
    }
}
