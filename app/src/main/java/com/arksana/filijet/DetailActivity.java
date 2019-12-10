package com.arksana.filijet;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.arksana.filijet.anim.ProgressBarAnimation;
import com.arksana.filijet.data.Film;
import com.arksana.filijet.main.DetailViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    public static String EXTRA_FILMS = "extra_films";
    public static String EXTRA_ID = "id";

    private DetailViewModel viewModel;
    private Film film;

    private TextView judul, tanggal, overview, rating;
    private ProgressBar progressBar;
    private View bgProgress;
    private ProgressBar pbrating;
    private ImageView photo;
    private Button btnPrev, btnNext, moviedb;

    private Menu menu;
    private boolean heartCondition;

    private void showLoading(boolean state) {
        btnNext.setEnabled(!state);
        btnPrev.setEnabled(!state);
        moviedb.setEnabled(!state);
        if (state) {
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
        rating = findViewById(R.id.tv_rating);

        pbrating = findViewById(R.id.pb_rating);
        photo = findViewById(R.id.photo);

        btnPrev = findViewById(R.id.btn_prev);
        btnNext = findViewById(R.id.btn_next);
        moviedb = findViewById(R.id.btn_link);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        prepareUI();
        showLoading(true);


        viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        viewModel.getFilm().observe(this, this::setUI);
        if (savedInstanceState == null) {
            viewModel.selectedId = getIntent().getIntExtra(EXTRA_ID, 0);
            viewModel.films = getIntent().getParcelableArrayListExtra(EXTRA_FILMS);
            viewModel.setFilm();
        }
        btnPrev.setOnClickListener(view -> {
            showLoading(true);
            viewModel.prev();
//            heartCheck();
        });
        btnNext.setOnClickListener(view -> {
            showLoading(true);
            viewModel.next();
//            heartCheck();
        });
        moviedb.setOnClickListener(view -> {
            String url = film.getUrl();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });

    }

    private void setUI(Film film) {
        this.film = film;
        heartCheck();
        setTitle(film.getJudul());
        judul.setText(film.getJudul());
        Glide.with(this)
                .load(film.getPhoto())
                .apply(new RequestOptions().override(200, 300))
                .into(photo);
        overview.setText(film.getOverview());
        tanggal.setText(film.getTanggal());
        rating.setText(String.format("%s%%", film.getRating()));
        pbrating.setProgress(Integer.parseInt(film.getRating()));
        ProgressBarAnimation anim = new ProgressBarAnimation(pbrating, 0, Integer.parseInt(film.getRating()));
        anim.setDuration(1000);
        pbrating.startAnimation(anim);
        showLoading(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fav, menu);
        this.menu = menu;
//        heartCheck();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (item.getItemId() == R.id.action_favorite) {
            changeLoveColor(item);
            return true;
        }
        return false;
    }

    private void changeLoveColor(MenuItem item) {
        heartCondition = !heartCondition;
        if (heartCondition) {
            item.setIcon(R.drawable.ic_favorite);
            viewModel.dataRepository.filmDAO.insert(film);
        } else {
            item.setIcon(R.drawable.ic_favorite_border);
            viewModel.dataRepository.filmDAO.delete(film);
        }
    }

    private void heartCheck() {
        MenuItem menuItem = menu.findItem(R.id.action_favorite);
        heartCondition = viewModel.dataRepository.filmDAO.getFilmWithID(film.getId()) != null;
        int icon = (heartCondition) ? R.drawable.ic_favorite : R.drawable.ic_favorite_border;
        menuItem.setIcon(icon);
    }
}
