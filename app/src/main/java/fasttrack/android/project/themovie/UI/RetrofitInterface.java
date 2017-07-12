package fasttrack.android.project.themovie.UI;

import fasttrack.android.project.themovie.Model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Fauziyyah Faturahma on 7/11/2017.
 */

public interface RetrofitInterface {

    public static final String BASE_IMAGE = "https://image.tmdb.org/t/p/w185";

    @GET("movie/popular")
    Call<Movie> getMoviePopular(@Query("api_key") String apikey);

    @GET("movie/top_rated")
    Call<Movie> getMovieTopRated(@Query("api_key") String apikey);
}
