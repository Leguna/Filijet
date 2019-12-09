package com.arksana.filijet.main;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arksana.filijet.DetailActivity;
import com.arksana.filijet.MainActivity;
import com.arksana.filijet.R;
import com.arksana.filijet.adapter.CardViewFilmAdapter;
import com.arksana.filijet.adapter.GridFilmAdapter;
import com.arksana.filijet.adapter.ListFilmAdapter;
import com.arksana.filijet.data.Film;

import java.util.ArrayList;
import java.util.Objects;

import static com.arksana.filijet.MainActivity.mode;


public class MainFragment extends Fragment {

    private ArrayList<Film> films = new ArrayList<>();
    private int spanCount = 2;

    public RecyclerView rvCategoryMode;
    private ProgressBar progressBar;
    private View progressBarBG;
    private TextView checkConnection;
    private int scrollPos;

    private MainViewModel viewModel;

    private static final String ARG_SECTION_NUMBER = "section_number";
    private int index;


    static MainFragment newInstance(int index) {

        MainFragment fragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvCategoryMode = view.findViewById(R.id.rv_category);
        progressBar = view.findViewById(R.id.progressBar);
        progressBarBG = view.findViewById(R.id.progressBarBG);
        checkConnection = view.findViewById(R.id.tv_check);


        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
            if (index == 1) {
                ((MainActivity) Objects.requireNonNull(getActivity())).moviesFragment = this;
            } else if (index == 2) {
                ((MainActivity) Objects.requireNonNull(getActivity())).tvshowFragment = this;
            } else if (index == 3) {
                ((MainActivity) Objects.requireNonNull(getActivity())).favoriteFragment = this;
            }
        }

        films = viewModel.getFilms(index);
        scrollPos = rvCategoryMode.getVerticalScrollbarPosition();
        rvCategoryMode.setVerticalScrollbarPosition(scrollPos);
        refreshData();
    }

    public void refreshData() {
        showLoading(false);
        rvCategoryMode.setVisibility(View.VISIBLE);
        ((MainActivity) Objects.requireNonNull(getActivity())).setMode(MainActivity.mode, this);
    }

    private void showSelectedFilm(int pos) {
        Intent moveWithDataIntent = new Intent(getContext(), DetailActivity.class);
        moveWithDataIntent.putExtra(DetailActivity.EXTRA_FILMS, films);
        moveWithDataIntent.putExtra(DetailActivity.EXTRA_ID, pos);
        startActivity(moveWithDataIntent);
    }

    public void showRecyclerList() {

        rvCategoryMode.setLayoutManager(new LinearLayoutManager(getContext()));
        ListFilmAdapter listFilmAdapter = new ListFilmAdapter(getContext());
        listFilmAdapter.setListFilm(films);
        rvCategoryMode.setAdapter(listFilmAdapter);
        listFilmAdapter.setOnItemClickCallback(new ListFilmAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(int position) {
                showSelectedFilm(position);
            }
        });
    }

    public void showRecyclerCardView() {
        rvCategoryMode.setLayoutManager(new LinearLayoutManager(getContext()));
        CardViewFilmAdapter cardViewFilmAdapter = new CardViewFilmAdapter(films);
        rvCategoryMode.setAdapter(cardViewFilmAdapter);
        cardViewFilmAdapter.setOnItemClickCallback(new CardViewFilmAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(int position) {
                showSelectedFilm(position);
            }
        });
    }

    public void showRecyclerGrid() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = 4;
        }
        rvCategoryMode.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
        GridFilmAdapter gridFilmAdapter = new GridFilmAdapter(films);
        rvCategoryMode.setAdapter(gridFilmAdapter);
        gridFilmAdapter.setOnItemClickCallback(new GridFilmAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(int position) {
                showSelectedFilm(position);
            }
        });
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (MainActivity.mode == R.id.action_grid) {
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                spanCount = 4;
                showRecyclerGrid();
            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                spanCount = 2;
                showRecyclerGrid();
            }
        }
        ((MainActivity) Objects.requireNonNull(getActivity())).setMode(mode, this);
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
            progressBarBG.setVisibility(View.VISIBLE);
            checkConnection.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            progressBarBG.setVisibility(View.GONE);
            checkConnection.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (index == 3) {
            refreshData();
        }
    }


}
