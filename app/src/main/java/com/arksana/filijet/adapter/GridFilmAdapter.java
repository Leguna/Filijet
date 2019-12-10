package com.arksana.filijet.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arksana.filijet.R;
import com.arksana.filijet.data.Film;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class GridFilmAdapter extends RecyclerView.Adapter<GridFilmAdapter.GridViewHolder> {

    private ArrayList<Film> listFilm;

    public GridFilmAdapter(ArrayList<Film> list) {
        this.listFilm = list;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_film, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return listFilm.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final GridViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(listFilm.get(position).getPhoto())
                .apply(new RequestOptions().override(200, 300))
                .into(holder.imgPhoto);
        holder.text.setText(listFilm.get(position).getJudul());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(holder.getAdapterPosition());
            }
        });
        
    }

    class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView text;
        GridViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.photo);
            text = itemView.findViewById(R.id.tv_judul);
        }
    }

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(int position);
    }

}