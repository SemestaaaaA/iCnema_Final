package com.semesta.icnema_uts;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface MovieApi {
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1MmNhNTVlNGYxNGU4ZWFiNTRhMjk5YzJjNGRhYTdmZCIsIm5iZiI6MTczMzcxNzE1NS4wOTYsInN1YiI6IjY3NTY2Y2EzOWNjMGI2ZmMzMTlhNjU0ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.P9mAV79eL1QJdOKv7-77vbLfu4l-Q7oYbdpa0DHV1cc")
    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlayingMovies(@Query("page") int page);

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1MmNhNTVlNGYxNGU4ZWFiNTRhMjk5YzJjNGRhYTdmZCIsIm5iZiI6MTczMzcxNzE1NS4wOTYsInN1YiI6IjY3NTY2Y2EzOWNjMGI2ZmMzMTlhNjU0ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.P9mAV79eL1QJdOKv7-77vbLfu4l-Q7oYbdpa0DHV1cc")
    @GET("movie/top_rated")
    Call<MovieResponse> getPopularMovies();

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1MmNhNTVlNGYxNGU4ZWFiNTRhMjk5YzJjNGRhYTdmZCIsIm5iZiI6MTczMzcxNzE1NS4wOTYsInN1YiI6IjY3NTY2Y2EzOWNjMGI2ZmMzMTlhNjU0ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.P9mAV79eL1QJdOKv7-77vbLfu4l-Q7oYbdpa0DHV1cc")
    @GET("search/movie")
    Call<MovieResponse> searchMovies(@Query("query") String query);
}