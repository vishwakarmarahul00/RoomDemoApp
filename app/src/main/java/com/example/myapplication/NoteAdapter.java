package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private Context context;
    private Notes[] notes;
    private LayoutInflater layoutInflater;
    public NoteAdapter(Context context) {
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(layoutInflater.inflate(R.layout.rvlist_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.tv_name.setText(notes[position].getName());
    }

    @Override
    public int getItemCount() {
        if (notes!=null)
        return notes.length;
        return 0;
    }

    public void setUpdatedDataList(Notes[] notesList) {
        this.notes=notesList;
        notifyDataSetChanged();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        protected AppCompatTextView tv_name;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.tv_name);
        }
    }
}
