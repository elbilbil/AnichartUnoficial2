package v1.dev.anichartunoficial;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
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
    private ProgressBar progressBarWinter;
    private ProgressBar progressBarSpring;
    private ProgressBar progressBarFall;
    private ProgressBar progressBarSummer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBarWinter = findViewById(R.id.progressBar_winter);
        progressBarSpring = findViewById(R.id.progressBar_spring);
        progressBarFall = findViewById(R.id.progressBar_fall);
        progressBarSummer = findViewById(R.id.progressBar_summer);

        loaderThread();

        setImageSeason(MediaSeason.WINTER, R.id.winterButton);
        setImageSeason(MediaSeason.SPRING, R.id.springButton);
        setImageSeason(MediaSeason.FALL, R.id.fallButton);
        setImageSeason(MediaSeason.SUMMER, R.id.summerButton);
    }

    private void loaderThread() {
        Thread loader_thread = new Thread(new Runnable() {
            public void run() {
                while (!queryManager.winter_progress ||
                        !queryManager.spring_progress ||
                        !queryManager.fall_progress ||
                        !queryManager.summer_progress) {
                    if (queryManager.winter_progress)
                        progressBarWinter.setVisibility(View.INVISIBLE);
                    if (queryManager.spring_progress)
                        progressBarSpring.setVisibility(View.INVISIBLE);
                    if (queryManager.fall_progress)
                        progressBarFall.setVisibility(View.INVISIBLE);
                    if (queryManager.summer_progress)
                        progressBarSummer.setVisibility(View.INVISIBLE);

                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("STOP", "thread stoped");
                progressBarWinter.setVisibility(View.INVISIBLE);
                progressBarSpring.setVisibility(View.INVISIBLE);
                progressBarFall.setVisibility(View.INVISIBLE);
                progressBarSummer.setVisibility(View.INVISIBLE);
                Thread.currentThread().interrupt();
            }
        });
        loader_thread.start();
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
        Intent winterIntent = new Intent(this, WinterFragmentActivity.class);
        startActivity(winterIntent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void springController(View view) {
        Intent springIntent = new Intent(this, SpringFragmentActivity.class);
        startActivity(springIntent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void summerController(View view) {
        Intent summerIntent = new Intent(this, SummerFragmentActivity.class);
        startActivity(summerIntent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void fallController(View view) {
        Intent fallIntent = new Intent(this, FallFragmentActivity.class);
        startActivity(fallIntent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}
