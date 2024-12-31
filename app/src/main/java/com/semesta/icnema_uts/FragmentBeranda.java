package com.semesta.icnema_uts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBeranda extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private String selectedBioskop;
    private String selectedLocation;
    private Spinner locationSpinner;
    private Spinner bioskopSpinner;

    public FragmentBeranda() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.beranda, container, false);

        recyclerView = view.findViewById(R.id.recyclerView_movies);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2)); // 2 columns

        locationSpinner = view.findViewById(R.id.location_spinner);
        bioskopSpinner = view.findViewById(R.id.bioskop_spinner);

        String[] locations = {"Palu", "Makassar", "Manado", "Surabaya"};
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, locations);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);

        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLocation = locations[position];
                updateBioskopSpinner(selectedLocation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        bioskopSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedBioskop = (String) parent.getItemAtPosition(position);
                fetchMovies();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        return view;
    }

    private void updateBioskopSpinner(String location) {
        // Example data, replace with actual data fetching logic
        String[] bioskops;
        switch (location) {
            case "Palu":
                bioskops = new String[]{"Bioskop Palu 1", "Bioskop Palu 2"};
                break;
            case "Makassar":
                bioskops = new String[]{"Bioskop Makassar 1", "Bioskop Makassar 2"};
                break;
            case "Manado":
                bioskops = new String[]{"Bioskop Manado 1", "Bioskop Manado 2"};
                break;
            case "Surabaya":
                bioskops = new String[]{"Bioskop Surabaya 1", "Bioskop Surabaya 2"};
                break;
            default:
                bioskops = new String[]{};
                break;
        }
        ArrayAdapter<String> bioskopAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, bioskops);
        bioskopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bioskopSpinner.setAdapter(bioskopAdapter);
    }

    private void fetchMovies() {
        if (selectedBioskop == null || selectedLocation == null) {
            return;
        }

        int page = 1; // Default page
        switch (selectedLocation) {
            case "Palu":
                page = selectedBioskop.equals("Bioskop Palu 1") ? 1 : 2;
                break;
            case "Makassar":
                page = selectedBioskop.equals("Bioskop Makassar 1") ? 3 : 4;
                break;
            case "Manado":
                page = selectedBioskop.equals("Bioskop Manado 1") ? 5 : 6;
                break;
            case "Surabaya":
                page = selectedBioskop.equals("Bioskop Surabaya 1") ? 7 : 8;
                break;
        }

        // Fetch movies based on selectedBioskop and page
        MovieApi movieApi = ApiClient.getClient().create(MovieApi.class);
        Call<MovieResponse> call = movieApi.getNowPlayingMovies(page);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movie> movies = response.body().getResults();
                    adapter = new MovieAdapter(getContext(), movies, true); // Pass true for FragmentBeranda
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}