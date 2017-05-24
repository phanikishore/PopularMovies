package com.udacity.kishore.popularmovies.dashboard.model;

import com.google.gson.annotations.SerializedName;
import com.udacity.kishore.popularmovies.base.BaseReposnse;

import java.util.List;

/**
 * Created by kishorea on 23/05/17.
 */

public class MovieDetailResponse extends BaseReposnse {
    @SerializedName("adult")
    public boolean adult;
    @SerializedName("backdrop_path")
    public String backdropPath;
    @SerializedName("belongs_to_collection")
    public Object belongsToCollection;
    @SerializedName("budget")
    public int budget;
    @SerializedName("genres")
    public List<Genre> genres = null;
    @SerializedName("homepage")
    public String homepage;
    @SerializedName("id")
    public int id;
    @SerializedName("imdb_id")
    public String imdbId;
    @SerializedName("original_language")
    public String originalLanguage;
    @SerializedName("original_title")
    public String originalTitle;
    @SerializedName("overview")
    public String overview;
    @SerializedName("popularity")
    public double popularity;
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("production_companies")
    public List<ProductionCompany> productionCompanies = null;
    @SerializedName("production_countries")
    public List<ProductionCountry> productionCountries = null;
    @SerializedName("release_date")
    public String releaseDate;
    @SerializedName("revenue")
    public int revenue;
    @SerializedName("runtime")
    public int runtime;
    @SerializedName("spoken_languages")
    public List<SpokenLanguage> spokenLanguages = null;
    @SerializedName("status")
    public String status;
    @SerializedName("tagline")
    public String tagline;
    @SerializedName("title")
    public String title;
    @SerializedName("video")
    public boolean video;
    @SerializedName("vote_average")
    public double voteAverage;
    @SerializedName("vote_count")
    public int voteCount;

    public class Genre {

        @SerializedName("id")
        public int id;
        @SerializedName("name")
        public String name;

    }

    public class ProductionCompany {

        @SerializedName("name")
        public String name;
        @SerializedName("id")
        public int id;

    }

    public class ProductionCountry {

        @SerializedName("iso_3166_1")
        public String iso31661;
        @SerializedName("name")
        public String name;

    }

    public class SpokenLanguage {

        @SerializedName("iso_639_1")
        public String iso6391;
        @SerializedName("name")
        public String name;

    }
}
