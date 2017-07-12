package fasttrack.android.project.themovie;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fasttrack.android.project.themovie.Adapter.MovieAdapter;
import fasttrack.android.project.themovie.Model.Movie;
import fasttrack.android.project.themovie.Model.Result;
import fasttrack.android.project.themovie.UI.RetrofitInterface;
import fasttrack.android.project.themovie.Utils.NetworkUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.movieList)
    RecyclerView MovieList;
    private List<Result> movieList = new ArrayList<>();
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MovieList.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        movieAdapter = new MovieAdapter(movieList);
        MovieList.setAdapter(movieAdapter);
        MovieList.setHasFixedSize(true);
        actionMoviePopular();
    }

    //showing top rated movies
    private void actionMovieTopRated() {
        new AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... params) {
                RetrofitInterface retrofitInterface = NetworkUtils.getRetrofit().create(RetrofitInterface.class);
                Call<Movie> movie = retrofitInterface.getMovieTopRated(BuildConfig.MOVIE_API_KEY);
                movie.enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        Log.d(MainActivity.class.getSimpleName(), "onResponse: ");
                        movieList.clear();
                        movieList.addAll(response.body().getResults());
                        movieAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        Log.e(MainActivity.class.getSimpleName(), "onFailure: ");
                    }
                });
                return "";
            }
        }.execute();
    }


    //showing most popular movies
    private void actionMoviePopular() {
        new AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... params) {
                RetrofitInterface retrofitInterface = NetworkUtils.getRetrofit().create(RetrofitInterface.class);
                Call<Movie> movie = retrofitInterface.getMoviePopular(BuildConfig.MOVIE_API_KEY);
                movie.enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        Log.d(MainActivity.class.getSimpleName(), "onResponse: ");
                        movieList.clear();
                        movieList.addAll(response.body().getResults());
                        movieAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        Log.e(MainActivity.class.getSimpleName(), "onFailure: ");
                    }
                });
                return "";
            }
        }.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_most_movies:
                actionMoviePopular();
                break;
            case R.id.action_sort_by_top_rated:
                actionMovieTopRated();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
