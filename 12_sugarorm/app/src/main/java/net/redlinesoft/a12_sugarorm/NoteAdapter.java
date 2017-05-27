package net.redlinesoft.a12_sugarorm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by xavier on 5/22/2017 AD.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private static final String TAG = "NoteAdapter";
    List<Note> mData;
    Context context;

    OnItemClickListener clickListener;
    OnItemLongClickListener longClickListener;

    public NoteAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.mData = notes;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        NoteViewHolder noteHolder = new NoteViewHolder(view);
        return noteHolder;
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        holder.txtTitle.setText(mData.get(position).getTitle());
        holder.txtNote.setText(mData.get(position).getNote());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView txtTitle,txtNote;

        public NoteViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtNote = (TextView) itemView.findViewById(R.id.txtNote);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(view, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            longClickListener.onItemLongClicked(view,getAdapterPosition());
            return true;
        }

    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface OnItemLongClickListener {
        public boolean onItemLongClicked(View view, int position);
    }


    public void SetOnItemLongClickListener(final OnItemLongClickListener itemClickListener) {
        this.longClickListener = itemClickListener;
    }

}
