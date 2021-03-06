package v1.dev.anichartunoficial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.simple.AnimeBySeasonQuery;
import com.apollographql.apollo.simple.SeasonImageQuery;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

class UtilityTools {

    HashMap<String, Bitmap> responses = new HashMap<String, Bitmap>();
    HashMap<Integer, AnimeBySeasonQuery.Medium> map = new HashMap<Integer, AnimeBySeasonQuery.Medium>();

    void createNewViewCard(final Context mContext, LinearLayout linearlayout, String text, Bitmap image, final int id, final SpringFragmentActivity springFragmentActivity, final AnimeBySeasonQuery.Medium medium) {

        int radius = 35;
        int textSize = 30;



        map.put(id, medium);
        CardView cardView = new CardView(mContext);

        LinearLayout.LayoutParams cardViewLayout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                Math.round(mContext.getResources().getDimension(R.dimen.cardViewLayout))
        );
        cardView.setLayoutParams(cardViewLayout);
        cardView.setRadius(radius);
        cardView.setId(id);
        cardView.isClickable();
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(springFragmentActivity, DetailsActivity.class);
//                detailIntent.putExtra("anime", map);
//                detailIntent.putExtra("image", medium.coverImage().large());
//                detailIntent.putExtra("episode", medium.episodes().intValue());
//                detailIntent.putExtra("id", id);
                springFragmentActivity.startActivity(detailIntent);
                springFragmentActivity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        LinearLayout linearlayoutInCard = new LinearLayout(mContext);

        LinearLayout.LayoutParams layoutInCard = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        linearlayoutInCard.setLayoutParams(layoutInCard);
        linearlayoutInCard.setOrientation(LinearLayout.VERTICAL);

        ImageView imageInCard = new ImageView(mContext);

        LinearLayout.LayoutParams imageLayout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                Math.round(mContext.getResources().getDimension(R.dimen.zeroDP)),
                1
        );

        imageInCard.setLayoutParams(imageLayout);
        imageInCard.setImageBitmap(image);
        imageInCard.setScaleType(ImageView.ScaleType.CENTER_CROP);


        TextView textInCard = new TextView(mContext);

        LinearLayout.LayoutParams textLayout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        textInCard.setLayoutParams(textLayout);

        textInCard.setText(text);

        textInCard.setTextColor(mContext.getResources().getColor(R.color.backgroundColor));
        textInCard.setTextSize(textSize);


        linearlayoutInCard.addView(imageInCard);
        linearlayoutInCard.addView(textInCard);

        cardView.addView(linearlayoutInCard);

        linearlayout.addView(cardView);

    }

    void glideFormat(Activity activity, final String url) {
        Glide.with(activity)
            .asBitmap()
                .load(url)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        responses.put(url, resource);
                    }
                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
        threadReader(url);
    }

    private void threadReader(@NotNull String uri) {
        while (responses.get(uri) == null) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
