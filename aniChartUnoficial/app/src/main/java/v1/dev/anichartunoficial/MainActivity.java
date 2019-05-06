package v1.dev.anichartunoficial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.simple.GetAnimeByIdQuery;
import com.apollographql.apollo.simple.SeasonImageQuery;
import com.apollographql.apollo.simple.type.MediaSeason;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import v1.dev.anichartunoficial.R;

public class MainActivity extends AppCompatActivity {

    Integer year = 2019;
    QueryManager queryManager = new QueryManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setImageSeason(MediaSeason.WINTER, R.id.winterButton);
        setImageSeason(MediaSeason.SPRING, R.id.springButton);
        setImageSeason(MediaSeason.FALL, R.id.fallButton);
        setImageSeason(MediaSeason.SUMMER, R.id.summerButton);
    }

    private void setImageSeason(MediaSeason season, int idButton) {
        ImageButton imageButton = findViewById(idButton);
        SeasonImageQuery seasonImageQuery = SeasonImageQuery
                .builder()
                .seasonYear(year)
                .season(season)
                .build();
        queryManager.setImageSeasonQuery(seasonImageQuery, imageButton, this);
    }


    public void winterController(View view) {
        Intent winterIntent = new Intent(this, winterActivity.class);
        startActivity(winterIntent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void springController(View view) {
        Intent springIntent = new Intent(this, springActivity.class);
        startActivity(springIntent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void summerController(View view) {
        Intent summerIntent = new Intent(this, summerActivity.class);
        startActivity(summerIntent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void fallController(View view) {
        Intent fallIntent = new Intent(this, fallActivity.class);
        startActivity(fallIntent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}
