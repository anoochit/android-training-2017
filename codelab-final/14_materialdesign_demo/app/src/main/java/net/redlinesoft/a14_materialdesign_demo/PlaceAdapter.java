package net.redlinesoft.a14_materialdesign_demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceHolder> {

    private List<Place> mData;
    private static String LOG_TAG = "RecyclerView";
    private View view;


    Context context;

    OnItemClickListener clickListener;

    public PlaceAdapter(Context context,List<Place> place) {
        this.context = context;
        this.mData = place;
    }

    @Override
    public PlaceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // TODO 7 : set row item layout for placeholder
        PlaceHolder placeHolder = new PlaceHolder(view);
        return placeHolder;

    }

    @Override
    public void onBindViewHolder(PlaceHolder holder, int position) {
        // TODO 9 : set value to each items, use picasso to load image from url.

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class PlaceHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imgThumbnail;
        public TextView txtName;
        public TextView txtDescription;

        public PlaceHolder(View itemView) {
            super(itemView);
            // TODO 8 : view binding with textview, imageview

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
}
