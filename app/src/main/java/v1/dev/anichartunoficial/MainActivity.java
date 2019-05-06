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
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBarWinter = findViewById(R.id.progressBar_winter);
        progressBarSpring = findViewById(R.id.progressBar_spring);
        progressBarFall = findViewById(R.id.progressBar_fall);
        progressBarSummer = findViewById(R.id.progressBar_summer);

        Thread winter_wait = new Thread(new Runnable() {
            public void run() {
                while (!queryManager.winter_progress) {
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("STOP", "thread stoped");
                progressBarWinter.setVisibility(View.INVISIBLE);
                Thread.currentThread().interrupt();
            }
        });
        winter_wait.start();
        Thread spring_wait = new Thread(new Runnable() {
            public void run() {
                while (!queryManager.spring_progress) {
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("STOP", "thread stoped");
                progressBarSpring.setVisibility(View.INVISIBLE);
                Thread.currentThread().interrupt();
            }
        });
        spring_wait.start();
        Thread fall_wait = new Thread(new Runnable() {
            public void run() {
                while (!queryManager.fall_progress) {
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("STOP", "thread stoped");
                progressBarFall.setVisibility(View.INVISIBLE);
                Thread.currentThread().interrupt();
            }
        });
        fall_wait.start();
        Thread summer_wait = new Thread(new Runnable() {
            public void run() {
                while (!queryManager.summer_progress) {
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("STOP", "thread stoped");
                progressBarSummer.setVisibility(View.INVISIBLE);
                Thread.currentThread().interrupt();
            }
        });
        summer_wait.start();



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
        queryManager.setImageSeasonQuery(seasonImageQuery, imageButton, idButton, this);
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
