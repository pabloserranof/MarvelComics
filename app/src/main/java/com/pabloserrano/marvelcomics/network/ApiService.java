package com.pabloserrano.marvelcomics.network;

import com.pabloserrano.marvelcomics.data.model.MarvelComics;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    String MARVEL_API_BASE_URL = "http://gateway.marvel.com/v1/public/";
    String privateKey = "e7abc3aeb6e72537c50b8dabd87db72d41d42c4e";
    String publicKey = "04fadbf06e9bc083627b39bdd6b82400";

    @GET("comics")
    Call<MarvelComics> getComics(
            @Query("format") String format,
            @Query ("limit") int limit
    );
}