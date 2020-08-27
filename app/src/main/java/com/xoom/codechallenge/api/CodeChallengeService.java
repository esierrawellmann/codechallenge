package com.xoom.codechallenge.api;

import com.xoom.codechallenge.model.CountriesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CodeChallengeService  {

    @GET("/catalog/v2/countries")
    Call<CountriesResponse> getCountries(@Query("page") int page, @Query("page_size") int pageSize);
}
