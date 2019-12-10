package com.arksana.filijet.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONObject;

@Entity(tableName = Film.TABLE_NAME)
public class Film implements Parcelable {


    public static final String TABLE_NAME = "films";


    @PrimaryKey
    @ColumnInfo(name = "filmid")
    private int id;

    @ColumnInfo(name = "judul")
    private String judul;

    @ColumnInfo(name = "rating")
    private String rating;
    @ColumnInfo(name = "jenis")
    private int jenis;
    @ColumnInfo(name = "overview")
    private String overview;
    @ColumnInfo(name = "photo")
    private String photo;
    @ColumnInfo(name = "tanggal-rilis")
    private String tanggal;
    @ColumnInfo(name = "url")
    private String url;

    public Film setFilmDetailFromJSON(JSONObject detailJSON, int jenis) {
        try {
            int id;
            String photo, rating, name, overview, tanggal, url;

            id = detailJSON.getInt("id");

            photo = "https://image.tmdb.org/t/p/w342" + detailJSON.getString("poster_path");
            rating = Double.toString(detailJSON.getDouble("vote_average") * 10);
            rating = rating.substring(0, rating.length() - 2);
            if (detailJSON.getString("overview").equals(""))
                overview = "-";
            else
                overview = detailJSON.getString("overview") + "\n";

            if (jenis == 1) {
                name = detailJSON.getString("title");
                tanggal = detailJSON.getString("release_date");
                url = "https://www.themoviedb.org/movie/" + id;
            } else {
                name = detailJSON.getString("name");
                tanggal = detailJSON.getString("first_air_date");
                url = "https://www.themoviedb.org/tv/" + id;
            }
            this.jenis = jenis;
            this.photo = photo;
            this.rating = rating;
            this.judul = name;
            this.overview = overview;
            this.tanggal = tanggal;
            this.url = url;
            this.id = id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    void setUrl(String url) {
        this.url = url;
    }

    void setJudul(String judul) {
        this.judul = judul;
    }

    void setOverview(String overview) {
        this.overview = overview;
    }


    int getJenis() {
        return jenis;
    }

    void setJenis(int jenis) {
        this.jenis = jenis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getPhoto() {
        return photo;
    }

    void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getJudul() {
        return judul;
    }

    public String getOverview() {
        return overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.photo);
        dest.writeString(this.rating);
        dest.writeString(this.judul);
        dest.writeString(this.overview);
        dest.writeString(this.tanggal);
        dest.writeString(this.url);
        dest.writeInt(this.id);
        dest.writeInt(this.jenis);
    }

    public Film() {
        judul = "";
    }

    private Film(Parcel in) {
        this.photo = in.readString();
        this.rating = in.readString();
        this.judul = in.readString();
        this.overview = in.readString();
        this.tanggal = in.readString();
        this.url = in.readString();
        this.id = in.readInt();
        this.jenis = in.readInt();
    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel source) {
            return new Film(source);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };


}