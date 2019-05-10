package v1.dev.anichartunoficial;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

public class SpringFragmentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Context mContext;
    private LinearLayout linearlayout;
    private UtilityTools utility = new UtilityTools();

    private CheckBox genre_checkbox;
    private CheckBox type_checkbox;
    private CheckBox name_checkbox;
    private LinearLayout name_field;
    private LinearLayout type_field;
    private LinearLayout genre_field;
    private Button search_btn;
    private EditText filter_name_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring_fragment);
        mContext = getApplicationContext();
        linearlayout = findViewById(R.id.mainLinear);

        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        */

        genre_checkbox =  findViewById(R.id.genre_checkbox);
        type_checkbox =  findViewById(R.id.type_checkbox);
        name_checkbox =  findViewById(R.id.name_checkbox);
        search_btn =  findViewById(R.id.search_filter);
        filter_name_input =  findViewById(R.id.filter_name_input);

        genre_field =  findViewById(R.id.genre_field);
        type_field =  findViewById(R.id.type_field);
        name_field =  findViewById(R.id.name_field);

        genre_field.setVisibility(View.GONE);
        type_field.setVisibility(View.GONE);
        name_field.setVisibility(View.GONE);

        utility.createNewViewCard(mContext, linearlayout, "toto", getResources().getDrawable(R.drawable.homepage));
        utility.createNewViewCard(mContext, linearlayout, "toto", getResources().getDrawable(R.drawable.homepage));
        utility.createNewViewCard(mContext, linearlayout, "toto", getResources().getDrawable(R.drawable.homepage));
        utility.createNewViewCard(mContext, linearlayout, "toto", getResources().getDrawable(R.drawable.homepage));
        utility.createNewViewCard(mContext, linearlayout, "toto", getResources().getDrawable(R.drawable.homepage));
        utility.createNewViewCard(mContext, linearlayout, "toto", getResources().getDrawable(R.drawable.homepage));
        utility.createNewViewCard(mContext, linearlayout, "toto", getResources().getDrawable(R.drawable.homepage));


        genre_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                    genre_field.setVisibility(View.VISIBLE);
                else
                    genre_field.setVisibility(View.GONE);

            }
        });
        type_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    type_field.setVisibility(View.VISIBLE);
                else
                    type_field.setVisibility(View.GONE);

            }
        });
        name_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    name_field.setVisibility(View.VISIBLE);
                else
                    name_field.setVisibility(View.GONE);
            }
        });

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name_checkbox.isChecked())
                    Log.d("WinterFragmentActivity", filter_name_input.getText().toString());
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        });


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        /*
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.spring, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        // TODO: utiliser ces 2 lignes pour fermer le fragment après avoir appyer sur search
        /*
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        */
        return true;
    }

    public void toHomePage(View view) {
        Intent homeIntent = new Intent(this, MainActivity.class);
        startActivity(homeIntent);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
