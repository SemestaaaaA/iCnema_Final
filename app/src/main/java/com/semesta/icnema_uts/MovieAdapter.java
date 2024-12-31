package com.semesta.icnema_uts;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context context;
    private List<Movie> movieList;
    private boolean isFromBeranda;

    public MovieAdapter(Context context, List<Movie> movieList, boolean isFromBeranda) {
        this.context = context;
        this.movieList = movieList;
        this.isFromBeranda = isFromBeranda;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.movieTitle.setText(movie.getTitle());

        String imageUrl = "https://image.tmdb.org/t/p/w500" + movie.getPosterPath();
        Glide.with(context).load(imageUrl).into(holder.movieImage);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("title", movie.getTitle());
            intent.putExtra("overview", movie.getOverview());
            intent.putExtra("release_date", movie.getReleaseDate());
            intent.putExtra("poster_path", imageUrl);
            intent.putExtra("is_from_beranda", isFromBeranda); // Pass the flag
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitle;
        ImageView movieImage;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movie_title);
            movieImage = itemView.findViewById(R.id.movie_image);
        }
    }
}