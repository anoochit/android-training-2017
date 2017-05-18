package net.redlinesoft.a12_file_asset;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xavier on 5/16/2017 AD.
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceHolder> {

    private List<Place> mData ;
    private static String TAG = "RecyclerView";
    View view;

    public PlaceAdapter(ArrayList place) {
        mData=place;
    }


    @Override
    public PlaceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        PlaceHolder placeHolder = new PlaceHolder(view);
        return placeHolder;
    }

    @Override
    public void onBindViewHolder(PlaceHolder holder, int position) {
        holder.txtName.setText(mData.get(position).getName());
        Picasso.with(view.getContext())
                .load(mData.get(position).getImages().get(0))
                .into(holder.imgThumbnail);

        Log.d(TAG,">"+mData.get(position).getImages().get(0));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class PlaceHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imgThumbnail;
        public TextView txtName;

        public PlaceHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtTitle);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.imgThumbnail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            removeAt(getAdapterPosition());
        }

        public void removeAt(int position) {
            mData.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, mData.size());
        }
    }

}


