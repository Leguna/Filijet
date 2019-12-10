package com.arksana.filijet.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.JsonObject;

import org.json.JSONObject;

@Entity(tableName = Film.TABLE_NAME)
public class Film implements Parcelable {

    public static final String TABLE_NAME = "films";
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

    public Film setFilmDetailFromJSON(JSONObject detailJSON, int type) {
        try {
            id = detailJSON.getInt("id");
            photo = "https://image.tmdb.org/t/p/w342" + detailJSON.getString("poster_path");
            rating = Double.toString(detailJSON.getDouble("vote_average") * 10);
            rating = rating.substring(0, rating.length() - 2);

            if (detailJSON.getString("overview").equals(""))
                overview = "-";
            else
                overview = detailJSON.getString("overview") + "\n";

            if (type == 1) {
                judul = detailJSON.getString("title");
                tanggal = detailJSON.getString("release_date");
                url = "https://www.themoviedb.org/movie/" + id;
            } else {
                judul = detailJSON.getString("name");
                tanggal = detailJSON.getString("first_air_date");
                url = "https://www.themoviedb.org/tv/" + id;
            }
            this.jenis = type;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public Film setFilmDetailFromJSON(JsonObject detailJSON, int type) {
        try {
            id = detailJSON.get("id").getAsInt();
            photo = "https://image.tmdb.org/t/p/w342" + detailJSON.get("poster_path").getAsString();
            rating = Double.toString(detailJSON.get("vote_average").getAsDouble() * 10);
            rating = rating.substring(0, rating.length() - 2);

            if (detailJSON.get("overview").equals(""))
                overview = "-";
            else
                overview = detailJSON.get("overview") + "\n";

            if (type == 1) {
                judul = detailJSON.get("title").getAsString();
                tanggal = detailJSON.get("release_date").getAsString();
                url = "https://www.themoviedb.org/movie/" + id;
            } else {
                judul = detailJSON.get("name").getAsString();
                tanggal = detailJSON.get("first_air_date").getAsString();
                url = "https://www.themoviedb.org/tv/" + id;
            }
            this.jenis = type;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
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

    void setUrl(String url) {
        this.url = url;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTanggal() {
        return tanggal;
    }

    void setTanggal(String tanggal) {
        this.tanggal = tanggal;
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

    void setJudul(String judul) {
        this.judul = judul;
    }

    public String getOverview() {
        return overview;
    }

    void setOverview(String overview) {
        this.overview = overview;
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

    @NonNull
    @Override
    public String toString() {
        return getJudul();
    }
}