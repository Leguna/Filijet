package com.arksana.filijet.data;

import android.database.Cursor;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface FilmDAO {

    @Insert
    void insert(Film... films);

    @Update
    void update(Film... films);

    @Delete
    void delete(Film film);

    @Query("SELECT * FROM films")
    List<Film> getFilms();

    @Query("SELECT * FROM films")
    DataSource.Factory<Integer, Film> getFilmsPaged();

    @Query("SELECT * FROM films WHERE filmid = :id")
    Film getFilmWithID(int id);

    @Query("SELECT * FROM " + Film.TABLE_NAME)
    Cursor getAllCursor();
}