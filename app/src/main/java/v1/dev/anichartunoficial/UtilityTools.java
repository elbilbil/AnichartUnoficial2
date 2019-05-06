package v1.dev.anichartunoficial;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

class UtilityTools {
    void createNewViewCard(Context mContext, LinearLayout linearlayout, String text, Drawable image) {

        int radius = 35;
        int textSize = 30;

        CardView cardView = new CardView(mContext);

        LinearLayout.LayoutParams cardViewLayout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                Math.round(mContext.getResources().getDimension(R.dimen.cardViewLayout))
        );
        cardView.setLayoutParams(cardViewLayout);
        cardView.setRadius(radius);


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
        imageInCard.setBackground(image);
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
}
