package v1.dev.anichartunoficial;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import v1.dev.anichartunoficial.R;

public class winterActivity extends AppCompatActivity {

    private Context mContext;
    private LinearLayout linearlayout;

    UtilityTools utility = new UtilityTools();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winter);
        mContext = getApplicationContext();
        linearlayout = findViewById(R.id.mainLinear);


        utility.createNewViewCard(mContext, linearlayout, "toto", getResources().getDrawable(R.drawable.homepage));
        utility.createNewViewCard(mContext, linearlayout, "toto", getResources().getDrawable(R.drawable.homepage));
        utility.createNewViewCard(mContext, linearlayout, "toto", getResources().getDrawable(R.drawable.homepage));
        utility.createNewViewCard(mContext, linearlayout, "toto", getResources().getDrawable(R.drawable.homepage));
        utility.createNewViewCard(mContext, linearlayout, "toto", getResources().getDrawable(R.drawable.homepage));
        utility.createNewViewCard(mContext, linearlayout, "toto", getResources().getDrawable(R.drawable.homepage));
        utility.createNewViewCard(mContext, linearlayout, "toto", getResources().getDrawable(R.drawable.homepage));


    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    public void toHomePage(View view) {
        Intent homeIntent = new Intent(this, MainActivity.class);
        startActivity(homeIntent);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

}
