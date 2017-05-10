package net.redlinesoft.a08_cardview;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by xavier on 5/1/2017 AD.
 */

public class MyGalleryCardViewAdapter extends RecyclerView.Adapter<MyGalleryCardViewAdapter.DataObjectHolder> {

    private ArrayList<DataObject> mDataset;
    private  static String TAG_LOG = "RecyclerView";

    public MyGalleryCardViewAdapter(ArrayList<DataObject> dataSet) {
        mDataset=dataSet;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.galleryview_item, parent, false);
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.person_name.setText(mDataset.get(position).getName());
        holder.person_age.setText(mDataset.get(position).getAge());
        holder.person_photo.setImageResource(mDataset.get(position).photoId);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cardview;
        TextView person_name;
        TextView person_age;
        ImageView person_photo;

        public DataObjectHolder(View itemView) {
            super(itemView);
            // binding widget
            cardview = (CardView) itemView.findViewById(R.id.card_view);
            person_name = (TextView) itemView.findViewById(R.id.person_name);
            person_age = (TextView) itemView.findViewById(R.id.person_age);
            person_photo = (ImageView) itemView.findViewById(R.id.person_photo);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG_LOG, String.valueOf(getAdapterPosition()));
        }
    }


}
