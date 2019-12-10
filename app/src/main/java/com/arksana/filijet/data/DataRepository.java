package com.arksana.filijet.data;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.room.Room;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.arksana.filijet.BuildConfig;
import com.arksana.filijet.utils.EspressoIdlingResource;

import org.json.JSONArray;

import java.util.ArrayList;

public class DataRepository {
    public MutableLiveData<ArrayList<Film>> films = new MutableLiveData<>();
    public MutableLiveData<Film> film = new MutableLiveData<>();
    public FilmDAO filmDAO;
    private String API_KEY = BuildConfig.TMDB_API_KEY;
    private RequestQueue queue;
    private ArrayList<Film> listItems = new ArrayList<>();
    private Application application;

    public DataRepository(Application application) {
        this.application = application;
        AppDatabase database = Room.databaseBuilder(application, AppDatabase.class, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build();
        filmDAO = database.getFilmDAO();

    }

    public LiveData<Film> getFilm() {
        return film;
    }

    public LiveData<ArrayList<Film>> getFilms() {
        return films;
    }


    public void getAllFilms(final int type, int page) {
        queue = Volley.newRequestQueue(application);
        if (type != 3) {
            final String url = BuildConfig.BASE_URL + "discover/" + ((type == 1) ? "movie" : "tv") + "?api_key=" + API_KEY + "&page=" + page;
            System.out.println(url);
            EspressoIdlingResource.increment();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, response -> {
                        try {
                            JSONArray list = response.getJSONArray("results");
                            for (int i = 0; i < list.length(); i++) {
                                if (!list.getJSONObject(i).getString("poster_path").contains("null")) {
                                    Film newFilm = new Film().setFilmDetailFromJSON(list.getJSONObject(i), type);
                                    listItems.add(newFilm);
                                }
                            }
                            films.postValue(listItems);
                            EspressoIdlingResource.decrement();
                        } catch (Exception ignored) {
                        }
                    }, System.out::println);

            queue.add(jsonObjectRequest);
        } else {
            listItems = new ArrayList<>(filmDAO.getFilms());
            films.postValue(listItems);
        }
    }

    public void getFilmDetail(final ArrayList<Film> newFilms, int pos) {
        queue = Volley.newRequestQueue(application);
        final Film newFilm = newFilms.get(pos);
        listItems = new ArrayList<>();
        int type = newFilm.getJenis();
        final String url = BuildConfig.BASE_URL +
                (type == 1 ? "movie/" : "tv/") + newFilm.getId() + "?api_key=" + API_KEY;
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


    public DataSource.Factory<Integer, Film> getAllFavorite() {
        return filmDAO.getFilmsPaged();
    }

}
