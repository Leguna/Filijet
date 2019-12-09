package com.arksana.filijet.adapter;

import android.content.Context;
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

public class ListFilmAdapter extends RecyclerView.Adapter<ListFilmAdapter.CategoryViewHolder> {

    private Context context;
    private ArrayList<Film> listFilm;

    private ArrayList<Film> getListFilm() {
        return listFilm;
    }

    public void setListFilm(ArrayList<Film> listFilm) {
        this.listFilm = listFilm;
    }

    public int getItemCount() {
        return getListFilm().size();
    }

    public ListFilmAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_film, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryViewHolder holder, final int position) {
        holder.tvJudul.setText(getListFilm().get(position).getJudul());
        holder.tvOverview.setText(getListFilm().get(position).getOverview());

        Glide.with(context)
                .load(getListFilm().get(position).getPhoto())
                .apply(new RequestOptions().override(200, 300))
                .into(holder.imgPhoto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(holder.getAdapterPosition());
            }
        });

    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul, tvOverview;
        ImageView imgPhoto;

        CategoryViewHolder(View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvOverview = itemView.findViewById(R.id.tv_overview);
            imgPhoto = itemView.findViewById(R.id.photo);
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