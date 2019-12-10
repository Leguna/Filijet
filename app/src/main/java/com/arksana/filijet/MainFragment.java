package com.arksana.filijet;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arksana.filijet.adapter.CardViewFilmAdapter;
import com.arksana.filijet.adapter.PagedAdapter;
import com.arksana.filijet.data.Film;
import com.arksana.filijet.main.MainViewModel;
import com.arksana.filijet.utils.EspressoIdlingResource;

import java.util.ArrayList;
import java.util.Objects;


public class MainFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private ArrayList<Film> films = new ArrayList<>();
    private ProgressBar progressBar;
    private View progressBarBG;
    private TextView checkConnection;
    private MainViewModel viewModel;

    private boolean isLoading;
    int scrollPos;
    private int type;
    private boolean isLoadMore;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    public static MainFragment newInstance(int index) {

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

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            if (!isLoadMore) {

            }
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            type = getArguments().getInt(ARG_SECTION_NUMBER);
            ((MainActivity) Objects.requireNonNull(getActivity())).fragments[type - 1] = this;
        }

        setUI(view);
        setAdapter();

        observe();

        refreshData();

    }

    private void observe() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        if (type != 3) {
            viewModel.getFilms().observe(this, films -> {
                if (films != null) {
                    Toast.makeText(getContext(), "Data Loaded...", Toast.LENGTH_LONG).show();
                    this.films = films;
                    isLoadMore = false;
                    showLoading(false);
                    ((CardViewFilmAdapter) Objects.requireNonNull(recyclerView.getAdapter())).setListFilm(films);
                }
            });
        } else {
            viewModel.getAllFavorite().observe(this, films -> {
                if (films != null) {
                    this.films = new ArrayList<>(films.snapshot());
                    isLoadMore = false;
                    showLoading(false);
//                    System.out.println("CEK: "+ films.snapshot().get(0).getJudul());
//                    ((PagedAdapter) Objects.requireNonNull(recyclerView.getAdapter())).setListFilm(this.films);
                    System.out.println("CEK: " + films.snapshot().size());
                    ((PagedAdapter) Objects.requireNonNull(recyclerView.getAdapter())).submitList(films);
                }
                System.out.println("Observe favorite.");
            });
        }
    }

    void refreshData() {
//        showLoading(true);
        if (type != 3)
            viewModel.setData(type);
        else {
//            setAdapter();
            observe();
            viewModel.getAllFavorite();
        }
    }

    private void showSelectedFilm(int pos) {
        Intent moveWithDataIntent = new Intent(getContext(), DetailActivity.class);
        moveWithDataIntent.putExtra(DetailActivity.EXTRA_FILMS, films);
        moveWithDataIntent.putExtra(DetailActivity.EXTRA_ID, pos);
        startActivity(moveWithDataIntent);
    }

    private void showLoading(Boolean state) {
        if (state && !isLoading) {
            isLoading = true;
            EspressoIdlingResource.increment();
            recyclerView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            progressBarBG.setVisibility(View.VISIBLE);
            checkConnection.setVisibility(View.GONE);
        } else if (!state && isLoading) {
            isLoading = false;
            EspressoIdlingResource.decrement();
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            progressBarBG.setVisibility(View.GONE);
            checkConnection.setVisibility(View.GONE);
        }
    }

    private void setAdapter() {
        if (type != 3) {
            CardViewFilmAdapter cardViewFilmAdapter = new CardViewFilmAdapter();
            cardViewFilmAdapter.setOnItemClickCallback(this::showSelectedFilm);
            cardViewFilmAdapter.setOnBottomReachedListener(position -> {
                boolean canLoad = (layoutManager.findFirstCompletelyVisibleItemPosition() + layoutManager.getChildCount()) == layoutManager.getItemCount();
                if (canLoad) {
                    viewModel.moreData();
                    scrollPos = layoutManager.findLastCompletelyVisibleItemPosition();
                }
            });
            recyclerView.setAdapter(cardViewFilmAdapter);
        } else {
            PagedAdapter pagedAdapter = new PagedAdapter();
            pagedAdapter.setOnItemClickCallback(this::showSelectedFilm);
//            pagedAdapter.setOnBottomReachedListener(position -> {
//                boolean canLoad = (layoutManager.findFirstCompletelyVisibleItemPosition() + layoutManager.getChildCount()) == layoutManager.getItemCount();
//                if (canLoad) {
//                    viewModel.getAllFavorite();
//                    scrollPos = layoutManager.findLastCompletelyVisibleItemPosition();
//                }
//            });
            recyclerView.setAdapter(pagedAdapter);
        }
    }

    private void setUI(View view) {

        recyclerView = view.findViewById(R.id.rv_category);
        progressBar = view.findViewById(R.id.progressBar);
        progressBarBG = view.findViewById(R.id.progressBarBG);
        checkConnection = view.findViewById(R.id.tv_check);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(recyclerViewOnScrollListener);

        showLoading(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (type == 3) {
            refreshData();
        }
    }
}
