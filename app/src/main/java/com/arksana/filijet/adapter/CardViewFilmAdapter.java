package com.arksana.filijet.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arksana.filijet.R;
import com.arksana.filijet.anim.ProgressBarAnimation;
import com.arksana.filijet.data.Film;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class CardViewFilmAdapter extends RecyclerView.Adapter<CardViewFilmAdapter.CardViewViewHolder> {


    public ArrayList<Film> getListFilm() {
        return listFilm;
    }

    public void setListFilm(ArrayList<Film> listFilm) {
        this.listFilm = listFilm;
        notifyDataSetChanged();
    }

    private ArrayList<Film> listFilm;
    private OnBottomReachedListener onBottomReachedListener;
    private CardViewFilmAdapter.OnItemClickCallback onItemClickCallback;

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_cardview_film, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return listFilm.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder holder, int position) {
        if (position == listFilm.size() - 2)
            onBottomReachedListener.onBottomReached(position);

        Film film = listFilm.get(position);
        Glide.with(holder.itemView.getContext())
                .load(film.getPhoto())
                .apply(new RequestOptions().override(200, 300))
                .into(holder.imgPhoto);
        holder.tvJudul.setText(film.getJudul());
        holder.tvTanggal.setText(film.getTanggal());
        holder.tvOverview.setText(film.getOverview());
        holder.tvRating.setText(film.getRating());
        if (film.getRating() == null) film.setRating("0");
        holder.pbRating.setProgress(Integer.parseInt(film.getRating()));
        ProgressBarAnimation anim = new ProgressBarAnimation(holder.pbRating, 0, Integer.parseInt(film.getRating()));
        anim.setDuration(1000);
        holder.pbRating.startAnimation(anim);
        holder.btnDetail.setOnClickListener(v -> onItemClickCallback.onItemClicked(holder.getAdapterPosition()));
    }

    public void setOnBottomReachedListener(OnBottomReachedListener onBottomReachedListener) {
        this.onBottomReachedListener = onBottomReachedListener;
    }

    public interface OnBottomReachedListener {
        void onBottomReached(int position);
    }

    public void setOnItemClickCallback(CardViewFilmAdapter.OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(int postition);
    }


    class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvJudul, tvTanggal, tvOverview, tvRating;
        Button btnDetail;
        ProgressBar pbRating;


        CardViewViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.photo);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
            tvOverview = itemView.findViewById(R.id.tv_overview);
            tvRating = itemView.findViewById(R.id.tv_rating);
            btnDetail = itemView.findViewById(R.id.btn_detail);
            pbRating = itemView.findViewById(R.id.pb_rating);
        }

    }

}
