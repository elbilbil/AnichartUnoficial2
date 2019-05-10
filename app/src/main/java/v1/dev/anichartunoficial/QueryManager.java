package v1.dev.anichartunoficial;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Size;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.simple.SeasonImageQuery;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import org.jetbrains.annotations.NotNull;

import java.security.AccessController;

import okhttp3.OkHttpClient;

import static java.security.AccessController.*;

class QueryManager {

    UtilityTools utilityTools = new UtilityTools();

    private ApolloClient apolloClient;
    boolean winter_progress = false;
    boolean spring_progress = false;
    boolean fall_progress = false;
    boolean summer_progress = false;

    QueryManager(){
        String BASE_URL = "https://graphql.anilist.co/";
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        apolloClient = ApolloClient.builder()
                .serverUrl(BASE_URL)
                .okHttpClient(okHttpClient)
                .build();
    }

    void setImageSeasonQuery(SeasonImageQuery seasonImageQuery, final ImageButton imageButton, final MainActivity mainActivity) {
        apolloClient.query(seasonImageQuery).enqueue(new ApolloCall.Callback<SeasonImageQuery.Data>() {
            @Override
            public void onResponse(@NotNull final Response<SeasonImageQuery.Data> response) {
                assert response.data() != null;
                final String uri = response.data().Media().coverImage().large();
                Thread loader_thread = new Thread(new Runnable() {
                    public void run() {
                        utilityTools.glideFormat(mainActivity, uri);

                        mainActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageButton.setImageBitmap(utilityTools.responses.get(uri));
                                imageButton.setAdjustViewBounds(true);
                                imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                switch (imageButton.getId()) {
                                    case R.id.winterButton:
                                        winter_progress = true;
                                    case R.id.springButton:
                                        spring_progress = true;
                                    case R.id.fallButton:
                                        fall_progress = true;
                                    case R.id.summerButton:
                                        summer_progress = true;
                                }
                            }
                        });
                        Thread.currentThread().interrupt();
                    }
                });
                loader_thread.start();
            }
            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.e("Query Error: ", e.getMessage());
            }
        });
    }

    void getAnimyWithSeason() {

    }
}
