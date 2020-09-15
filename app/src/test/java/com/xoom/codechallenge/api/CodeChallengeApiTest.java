package com.xoom.codechallenge.api;


import com.xoom.codechallenge.model.CountriesResponse;
import com.xoom.codechallenge.model.Country;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.testng.annotations.AfterTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

class CodeChallengeApiTest {

    CodeChallengeApi serviceApi = new CodeChallengeApi();

    @Test
    void testGetCountries() throws IOException {
        Call<CountriesResponse> responseCall =  serviceApi.getCountries(1,250);
        Response<CountriesResponse> response = responseCall.execute();
        CountriesResponse countries = response.body();
        List<Country> countryList = countries.getCountries();
        assertFalse(countryList.isEmpty());
    }

    @Test
    void testGetFirstCountry_FirstApiCountry_HasCodeandName() throws IOException {
        Call<CountriesResponse> responseCall =  serviceApi.getCountries(1,250);
        Response<CountriesResponse> response = responseCall.execute();
        CountriesResponse countries = response.body();
        List<Country> countryList = countries.getCountries();

        assertThat(countryList.get(0).getCode(), is(IsNull.notNullValue()));
        assertThat(countryList.get(0).getName(), is(IsNull.notNullValue()));
    }
}