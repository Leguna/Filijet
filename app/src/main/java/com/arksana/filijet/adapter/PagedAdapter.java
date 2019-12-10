package com.arksana.filijet.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.arksana.filijet.R;
import com.arksana.filijet.anim.ProgressBarAnimation;
import com.arksana.filijet.data.Film;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class PagedAdapter extends PagedListAdapter<Film, PagedAdapter.PagedViewHolder> {

    // DiffCallback
    private static DiffUtil.ItemCallback<Film> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Film>() {
                @Override
                public boolean areItemsTheSame(Film oldFilm, Film newFilm) {
                    return oldFilm.getId() == newFilm.getId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(Film oldFilm, @NonNull Film newFilm) {
                    return oldFilm.equals(newFilm);
                }
            };

    private ArrayList<Film> listFilm = new ArrayList<>();
    private OnBottomReachedListener onBottomReachedListener;
    private OnItemClickCallback onItemClickCallback;

    public PagedAdapter() {
        super(DIFF_CALLBACK);
    }

    public ArrayList<Film> getListFilm() {
        return listFilm;
    }

    public void setListFilm(ArrayList<Film> listFilm) {
        this.listFilm = listFilm;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PagedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_cardview_film, parent, false);
        return new PagedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PagedViewHolder holder, int position) {
        Film film = getItem(position);
        if (film != null) {
            if (position + 1 == listFilm.size())
                onBottomReachedListener.onBottomReached(position);
            holder.bindTo(film);
        } else {
            holder.clear();
        }
    }

    public void setOnBottomReachedListener(OnBottomReachedListener onBottomReachedListener) {
        this.onBottomReachedListener = onBottomReachedListener;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnBottomReachedListener {
        void onBottomReached(int position);
    }

    public interface OnItemClickCallback {
        void onItemClicked(int postition);
    }

    class PagedViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvJudul, tvTanggal, tvOverview, tvRating;
        Button btnDetail;
        ProgressBar pbRating;


        PagedViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.photo);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
            tvOverview = itemView.findViewById(R.id.tv_overview);
            tvRating = itemView.findViewById(R.id.tv_rating);
            btnDetail = itemView.findViewById(R.id.btn_detail);
            pbRating = itemView.findViewById(R.id.pb_rating);
        }

        void clear() {
            Glide.with(itemView.getContext())
                    .load(R.drawable.poster_gadispohonketapang)
                    .apply(new RequestOptions().override(200, 300))
                    .into(imgPhoto);
            tvJudul.setText("-");
            tvTanggal.setText("-");
            tvOverview.setText("-");
            tvRating.setText(0);
            pbRating.setProgress(0);
            ProgressBarAnimation anim = new ProgressBarAnimation(pbRating, 0, 0);
            anim.setDuration(1000);
            pbRating.startAnimation(anim);
            btnDetail.setOnClickListener(v -> onItemClickCallback.onItemClicked(getAdapterPosition()));
        }

        void bindTo(Film film) {
            Glide.with(itemView.getContext())
                    .load(film.getPhoto())
                    .apply(new RequestOptions().override(200, 300))
                    .into(imgPhoto);
            tvJudul.setText(film.getJudul());
            tvTanggal.setText(film.getTanggal());
            tvOverview.setText(film.getOverview());
            tvRating.setText(film.getRating());
            if (film.getRating() == null) film.setRating("0");
            pbRating.setProgress(Integer.parseInt(film.getRating()));
            ProgressBarAnimation anim = new ProgressBarAnimation(pbRating, 0, Integer.parseInt(film.getRating()));
            anim.setDuration(1000);
            pbRating.startAnimation(anim);
            btnDetail.setOnClickListener(v -> onItemClickCallback.onItemClicked(getAdapterPosition()));
        }
    }


}

