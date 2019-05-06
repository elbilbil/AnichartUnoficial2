package v1.dev.anichartunoficial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import v1.dev.anichartunoficial.R;

public class fallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fall);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
