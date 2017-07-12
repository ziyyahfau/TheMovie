package fasttrack.android.project.themovie.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.GsonBuilder;

import fasttrack.android.project.themovie.Model.Result;
import fasttrack.android.project.themovie.R;

/**
 * Created by Fauziyyah Faturahma on 7/11/2017.
 */

public class DetailMovie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_movie);


        Result data = new GsonBuilder().create().fromJson(this.getIntent().getStringExtra("movie"), Result.class);

        TextView titleMovie = (TextView) findViewById(R.id.textTitleMovie);
        ImageView detailImage = (ImageView) findViewById(R.id.detailImage);
        TextView dateReleaseMovie = (TextView) findViewById(R.id.textDateRelease);
        TextView ratingMovie = (TextView) findViewById(R.id.textRatingMovie);
        TextView synopsisMovie = (TextView) findViewById(R.id.textDetailSynopsis);

        titleMovie.setText(data.getTitle());
        dateReleaseMovie.setText(data.getReleaseDate());
        ratingMovie.setText(""+data.getVoteAverage());
        synopsisMovie.setText(data.getOverview());
        Glide.with(this)
                .load(RetrofitInterface.BASE_IMAGE + data.getPosterPath())
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(detailImage);

    }

    //back button to home
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            super.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
