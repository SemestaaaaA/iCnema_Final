package com.semesta.icnema_uts;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private ImageView movieImage;
    private TextView movieTitle, movieOverview, movieReleaseDate;
    private Button buttonBookTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize views
        movieImage = findViewById(R.id.detail_movie_image);
        movieTitle = findViewById(R.id.detail_movie_title);
        movieOverview = findViewById(R.id.detail_movie_overview);
        movieReleaseDate = findViewById(R.id.detail_movie_release_date);
        buttonBookTicket = findViewById(R.id.button_book_ticket);

        // Get data from intent
        String title = getIntent().getStringExtra("title");
        String overview = getIntent().getStringExtra("overview");
        String releaseDate = getIntent().getStringExtra("release_date");
        String imageUrl = getIntent().getStringExtra("poster_path");
        boolean isFromBeranda = getIntent().getBooleanExtra("is_from_beranda", false);

        // Set data to views
        movieTitle.setText(title);
        movieOverview.setText(overview);
        movieReleaseDate.setText("Release Date: " + releaseDate);
        Glide.with(this).load(imageUrl).into(movieImage);

        // Show or hide the button based on the flag
        if (isFromBeranda) {
            buttonBookTicket.setVisibility(View.VISIBLE);
            buttonBookTicket.setOnClickListener(v -> {
                BookingDialogFragment bookingDialog = BookingDialogFragment.newInstance(
                        title, overview, releaseDate, imageUrl);
                bookingDialog.show(getSupportFragmentManager(), "BookingDialog");
            });
        } else {
            buttonBookTicket.setVisibility(View.GONE);
        }
    }
}