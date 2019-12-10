package com.arksana.filijet.data;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.arksana.filijet.BuildConfig;
import com.arksana.filijet.EspressoIdlingResource;

import org.json.JSONArray;

import java.util.ArrayList;

public class DataRepository {
    private String API_KEY = BuildConfig.TMDB_API_KEY;

    private RequestQueue queue;
    private ArrayList<Film> listItems = new ArrayList<>();
    private Application application;

    public MutableLiveData<ArrayList<Film>> films = new MutableLiveData<>();
    public MutableLiveData<Film> film = new MutableLiveData<>();

    public DataRepository(Application application) {
        this.application = application;
    }

    public LiveData<Film> getFilm() {
        return film;
    }

    public LiveData<ArrayList<Film>> getFilms() {
        return films;
    }

    public void setFilms(ArrayList<Film> newFilms) {
        films.setValue(newFilms);
    }

    public void getFilmDetail(final ArrayList<Film> newFilms, int pos) {
        queue = Volley.newRequestQueue(application);
        setFilms(newFilms);
        final Film newFilm = newFilms.get(pos);
        listItems = new ArrayList<>();
        film.setValue(newFilm);
        int jenis = newFilm.getJenis();
        final String url = BuildConfig.BASE_URL +
                (jenis == 1 ? "movie/" : "tv/") + newFilm.getId() + "?api_key=" + API_KEY;
        System.out.println(url);
        EspressoIdlingResource.increment();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                        newFilm.setFilmDetailFromJSON(response, newFilm.getJenis());
                        film.postValue(newFilm);
                        EspressoIdlingResource.decrement();
                    } catch (Exception ignored) {
                    }
                }, error -> films.postValue(FilmData.getListData(newFilm.getJenis())));

        queue.add(jsonObjectRequest);
    }

    public MutableLiveData<ArrayList<Film>> getAllFilms(final int type) {
        queue = Volley.newRequestQueue(application);
        listItems = new ArrayList<>();

        final String url = BuildConfig.BASE_URL + "discover/" + ((type == 1) ? "movie" : "tv") + "?api_key=" + API_KEY;

        System.out.println(url);
        EspressoIdlingResource.increment();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                        JSONArray list = response.getJSONArray("results");
                        for (int i = 0; i < list.length(); i++) {
                            Film newFilm = new Film().setFilmDetailFromJSON(list.getJSONObject(i), type);
                            if (!newFilm.getPhoto().contains("null"))
                                listItems.add(newFilm);
                        }
                        films.postValue(listItems);
                        EspressoIdlingResource.decrement();
                    } catch (Exception ignored) {
                    }
                }, error -> films.postValue(FilmData.getListData(type)));

        queue.add(jsonObjectRequest);
        return films;
    }

    public void getAllFilmsOffline(final int type) {
        films.setValue(FilmData.getListData(type));
    }

}
