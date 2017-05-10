package net.redlinesoft.a13_retrofit.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.redlinesoft.a13_retrofit.R;
import net.redlinesoft.a13_retrofit.model.Place;

import java.util.List;

/**
 * Created by xavier on 5/3/2017 AD.
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceHolder> {

    private List<Place> mData ;
    private static String LOG_TAG = "RecyclerView";

    public PlaceAdapter(List<Place> place) {
          mData=place;
    }

    View view;
    PlaceHolder placeHolder;

    @Override
    public PlaceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item, parent, false);
         placeHolder = new PlaceHolder(view);
        return placeHolder;
    }

    @Override
    public void onBindViewHolder(final PlaceHolder holder, int position) {
        holder.txtName.setText(mData.get(position).getName());
        holder.txtDescription.setText(mData.get(position).getDescription());
        Picasso.with(view.getContext())
                .load(mData.get(position).getImages().get(0))
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.imgThumbnail);

        Log.d(LOG_TAG,""+mData.get(position).getImages().get(0));
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
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.imgThumbnail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            try {
                Log.d(LOG_TAG, "onClick " + getAdapterPosition() + " - "
                        + mData.get(getAdapterPosition()).getImages().get(0) + " - "
                        + mData.get(getAdapterPosition()).getGeolocation().getLat() + " - "
                        + mData.get(getAdapterPosition()).getGeolocation().getLon());
            } catch (Exception e) {
                Log.e(LOG_TAG,"error fetch data");
            }

        }

    }
}
