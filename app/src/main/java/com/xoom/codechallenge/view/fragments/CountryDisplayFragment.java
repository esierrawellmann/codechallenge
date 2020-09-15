package com.xoom.codechallenge.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xoom.codechallenge.R;
import com.xoom.codechallenge.api.CodeChallengeApi;
import com.xoom.codechallenge.api.CodeChallengeService;
import com.xoom.codechallenge.databinding.CountryListItemBinding;
import com.xoom.codechallenge.databinding.FragmentCountryDisplayBinding;
import com.xoom.codechallenge.model.CountriesResponse;
import com.xoom.codechallenge.model.Country;
import com.xoom.codechallenge.view.adapters.CountryListAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryDisplayFragment extends CodeChallengeBaseFragment {

    public static final String TAG = "cc_cdf";

    CodeChallengeApi serviceApi = new CodeChallengeApi();
    FragmentCountryDisplayBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCountryDisplayBinding.inflate(inflater,container,false);
        RecyclerView recyclerView = binding.rvCountries;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Country> countries = new ArrayList<Country>();

        serviceApi.getCountries(1,250).enqueue(new Callback<CountriesResponse>() {
            @Override
            public void onResponse(Call<CountriesResponse> call, Response<CountriesResponse> response) {
                countries.addAll(response.body().getCountries());
                CountryListAdapter adapter = new CountryListAdapter(getActivity(), countries);

                recyclerView.setAdapter(adapter);
                adapter.setClickListener((view, position) -> {
                    Toast.makeText(getActivity(), "You clicked " + adapter.getItem(position).getName() + " on row number " + position, Toast.LENGTH_SHORT).show();
                });
            }
            @Override
            public void onFailure(Call<CountriesResponse> call, Throwable t) {
                Log.e(getActivity().getPackageName(),t.getMessage(),t);
            }
        });
        return binding.getRoot();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
