package com.example.dhaval.gecg;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dhaval.gecg.pojo.Event;

import java.util.List;

/**
 * Created by dhaval on 23/02/2018.
 */

public class DataAdapter extends  RecyclerView.Adapter<DataAdapter.MyViewHolder>{
    private Context mContext;
    private List<Event> activityList;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_card, parent, false);
        return  new MyViewHolder(itemView);
    }
    @Override
     public void onBindViewHolder(MyViewHolder holder, int position) {
        Event event = activityList.get(position);
        holder.actName.setText(event.getName());
        holder.location.setText(event.getLocation());
        holder.oraganizer.setText(event.getOrganizer());
        holder.timing.setText(event.getTime());
        holder.date.setText(event.getDate());
        //Glide.with(mContext).load(activityList.get(position).getImages().getImg2()).into(holder.thumbnail);
        Glide.with(mContext).load(activityList.get(position).getImage()).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).transform(new CircleTransform(mContext)).into(holder.thumbnail);

//        Glide
//                .with(mContext)
//                .load(activityList.get(position).getImage())// can also be a drawable
//                .error(R.mipmap.ic_launcher) // will be displayed if the image cannot be loaded
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .crossFade()
//                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView actName,location,oraganizer,timing,date;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            actName = (TextView) view.findViewById(R.id.actName);
            location = (TextView) view.findViewById(R.id.actDest);
            oraganizer = (TextView) view.findViewById(R.id.actOrg);
            timing = view.findViewById(R.id.actTiming);
            date = view.findViewById(R.id.actDate);
            thumbnail = (ImageView) view.findViewById(R.id.actImg);
        }
    }
    public DataAdapter(Context mContext, List<Event> activityList) {
        this.mContext = mContext;
        this.activityList = activityList;
    }

}