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

    private ArrayList<Film> listFilm;

    public CardViewFilmAdapter(ArrayList<Film> list) {
        this.listFilm = list;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_film, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder holder, int position) {
        if (position == listFilm.size() / 2 /* calculate middle element position */) {
            holder.setIsInTheMiddle(true);
        } else {
            holder.setIsInTheMiddle(false);
        }
        Film film = listFilm.get(position);
        Glide.with(holder.itemView.getContext())
                .load(film.getPhoto())
                .apply(new RequestOptions().override(200, 300))
                .into(holder.imgPhoto);
        holder.tvJudul.setText(film.getJudul());
        holder.tvTanggal.setText(film.getTanggal());
        holder.tvOverview.setText(film.getOverview());
        holder.tvRating.setText(film.getRating());
        if(film.getRating()==null) film.setRating("0");
        holder.pbRating.setProgress(Integer.parseInt(film.getRating()));
        ProgressBarAnimation anim = new ProgressBarAnimation(holder.pbRating, 0, Integer.parseInt(film.getRating()));
        anim.setDuration(1000);
        holder.pbRating.startAnimation(anim);

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFilm.size();
    }

    class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvJudul, tvTanggal, tvOverview, tvRating;
        Button btnDetail;
        ProgressBar pbRating;

        // We'll use this field to showcase matching the holder from the test.
        private boolean mIsInTheMiddle = false;

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

        boolean getIsInTheMiddle() {
            return mIsInTheMiddle;
        }

        void setIsInTheMiddle(boolean isInTheMiddle) {
            mIsInTheMiddle = isInTheMiddle;
        }
    }

    private CardViewFilmAdapter.OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(CardViewFilmAdapter.OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(int postition);
    }
}
