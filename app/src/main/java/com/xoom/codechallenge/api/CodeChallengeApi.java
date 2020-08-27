package com.xoom.codechallenge.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xoom.codechallenge.model.CountriesResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.google.gson.FieldNamingPolicy.IDENTITY;

public class CodeChallengeApi {

    private final CodeChallengeService service;

    public CodeChallengeApi() {
        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(IDENTITY)
                .create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mobile.xoom.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(CodeChallengeService.class);
    }

    public Call<CountriesResponse> getCountries(int page, int pageSize) {
        return service.getCountries(page, pageSize);
    }
}