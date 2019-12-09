package com.arksana.filijet;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.arksana.filijet.anim.ProgressBarAnimation;
import com.arksana.filijet.main.DetailViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    public static String EXTRA_FILMS = "extra_films";
    public static String EXTRA_ID = "id";

    private DetailViewModel viewModel;

    TextView judul, tanggal, overview, tokoh, rating;
    ProgressBar progressBar;
    View bgProgress;
    ProgressBar pbrating;
    ImageView photo;
    Button btnPrev, btnNext, moviedb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        prepareUI();

        viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        if (savedInstanceState == null) {
            viewModel.films = getIntent().getParcelableArrayListExtra(EXTRA_FILMS);
            viewModel.selectedId = getIntent().getIntExtra(EXTRA_ID, 0);
        }

        setUI();

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.prev();
                setUI();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.next();
                setUI();
            }
        });
        moviedb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = viewModel.getFilm().getUrl();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

    }

    private void showLoading(boolean kondisi) {
        btnNext.setEnabled(!kondisi);
        btnPrev.setEnabled(!kondisi);
        if (kondisi) {
            progressBar.setVisibility(View.VISIBLE);
            bgProgress.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
            bgProgress.setVisibility(View.GONE);
        }
    }

    private void prepareUI() {
        bgProgress = findViewById(R.id.progressBarBG);
        progressBar = findViewById(R.id.progressBar);

        judul = findViewById(R.id.tv_judul);
        tanggal = findViewById(R.id.tv_tanggal);

        overview = findViewById(R.id.tv_overview);
        tokoh = findViewById(R.id.tv_tokoh);
        rating = findViewById(R.id.tv_rating);

        pbrating = findViewById(R.id.pb_rating);
        photo = findViewById(R.id.photo);

        btnPrev = findViewById(R.id.btn_prev);
        btnNext = findViewById(R.id.btn_next);
        moviedb = findViewById(R.id.btn_link);
        showLoading(false);
    }

    private void setUI() {
        setTitle(viewModel.getFilm().getJudul());
        judul.setText(viewModel.getFilm().getJudul());
        Glide.with(this)
                .load(viewModel.getFilm().getPhoto())
                .apply(new RequestOptions().override(200, 300))
                .into(photo);
        overview.setText(viewModel.getFilm().getOverview());
        String teksCrew;
        if (viewModel.getFilm().getCrew() == null) teksCrew = "-";
        else teksCrew = viewModel.getFilm().getCrew();
        tokoh.setText(teksCrew);
        tanggal.setText(viewModel.getFilm().getTanggal());
        rating.setText(String.format("%s%%", viewModel.getFilm().getRating()));
        pbrating.setProgress(Integer.parseInt(viewModel.getFilm().getRating()));
        ProgressBarAnimation anim = new ProgressBarAnimation(pbrating, 0, Integer.parseInt(viewModel.getFilm().getRating()));
        anim.setDuration(1000);
        pbrating.startAnimation(anim);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

}
