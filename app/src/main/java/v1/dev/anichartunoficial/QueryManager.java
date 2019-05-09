package v1.dev.anichartunoficial;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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

import okhttp3.OkHttpClient;

class QueryManager {

    private ApolloClient apolloClient;
    public boolean winter_progress = false;
    public boolean spring_progress = false;
    public boolean fall_progress = false;
    public boolean summer_progress = false;

    QueryManager(){
        String BASE_URL = "https://graphql.anilist.co/";
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        apolloClient = ApolloClient.builder()
                .serverUrl(BASE_URL)
                .okHttpClient(okHttpClient)
                .build();
    }


    public void setImageSeasonQuery(SeasonImageQuery seasonImageQuery, final ImageButton imageButton, final int idButton, final MainActivity mainActivity) {
        apolloClient.query(seasonImageQuery).enqueue(new ApolloCall.Callback<SeasonImageQuery.Data>() {
            @Override
            public void onResponse(@NotNull final Response<SeasonImageQuery.Data> response) {
                assert response.data() != null;

                mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Glide.with(mainActivity)
                                .asBitmap()
                                .load(response.data().Media().coverImage().large())
                                .into(new CustomTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        imageButton.setImageBitmap(resource);
                                        imageButton.setAdjustViewBounds(true);
                                        imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);

                                        switch (imageButton.getId()){
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
                                    @Override
                                    public void onLoadCleared(@Nullable Drawable placeholder) {
                                    }
                                });
                    }
                });
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.e("Query Error: ", e.getMessage());
            }
        });
    }
}
