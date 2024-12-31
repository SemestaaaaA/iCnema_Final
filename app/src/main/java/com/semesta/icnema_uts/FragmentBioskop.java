package com.semesta.icnema_uts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBioskop extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private MovieAdapter adapter;

    public FragmentBioskop() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bioskop, container, false);

        recyclerView = view.findViewById(R.id.recyclerView_movies);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2)); // 2 columns

        fetchTopMovies();

        return view;
    }

    public void fetchTopMovies() {
        MovieApi movieApi = ApiClient.getClient().create(MovieApi.class);
        Call<MovieResponse> call = movieApi.getPopularMovies();

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movie> movies = response.body().getResults();
                    adapter = new MovieAdapter(getContext(), movies, false); // Pass false for FragmentBioskop
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void searchMovies(String query) {
        MovieApi movieApi = ApiClient.getClient().create(MovieApi.class);
        Call<MovieResponse> call = movieApi.searchMovies(query);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movie> movies = response.body().getResults();
                    adapter = new MovieAdapter(getContext(), movies, false); // Pass false for FragmentBioskop
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