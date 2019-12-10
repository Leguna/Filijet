package com.arksana.filijet.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Film.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    static public String DATABASE_NAME = "db-filijet";

    public abstract FilmDAO getFilmDAO();
}
