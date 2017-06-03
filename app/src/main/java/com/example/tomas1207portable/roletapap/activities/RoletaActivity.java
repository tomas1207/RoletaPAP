package com.example.tomas1207portable.roletapap.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tomas1207portable.roletapap.CustomRoleta;
import com.example.tomas1207portable.roletapap.R;
import com.example.tomas1207portable.roletapap.Settings;
import com.example.tomas1207portable.roletapap.business.Roleta;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RoletaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        Settings.OnFragmentInteractionListener,
        CustomRoleta.OnFragmentInteractionListener {

    SharedPreferences sharedPreferences;
    public static TextView TextUp;
    public static TextView TextLeft;
    public static TextView TextDown;
    public static TextView TextRight;
private Roleta roleta;
    private ArrayList<String> players;
    private int lapCount;
    private ImageView pointer;

    @Override
    public void onFragmentInteraction(String string) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final RoletaActivity _this = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roleta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextUp = (TextView)findViewById(R.id.Textup);
        TextRight = (TextView)findViewById(R.id.TextRight);
        TextLeft = (TextView)findViewById(R.id.TextLeft);
        TextDown = (TextView)findViewById(R.id.TextDown);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        sharedPreferences = getSharedPreferences("cenas",MODE_PRIVATE);

        this.players = new ArrayList();
        this.players.add(sharedPreferences.getString("text1", "Player 1"));
        this.players.add(sharedPreferences.getString("text2", "Player 2"));
        this.players.add(sharedPreferences.getString("text3", "Player 3"));
        this.players.add(sharedPreferences.getString("text4", "Player 4"));

        this.pointer = (ImageView) this.findViewById(R.id.ImageArrow);
        this.lapCount = 4;
        roleta = new Roleta(players);

        Toast.makeText(this, "Tudo em ordem", Toast.LENGTH_SHORT).show();
        TextLeft.setText(this.players.get(0));
        TextRight.setText(this.players.get(1));
        TextDown.setText(this.players.get(2));
        TextUp.setText(this.players.get(3));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _this.clickedStart();
            }

        });

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        toolbar.setNavigationIcon(R.drawable.menu);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            drawer.openDrawer(GravityCompat.START);
            }
        });
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.roleta, menu);
        return true;
    }

    private void clickedStart() {
        reset();
        roleta = new Roleta(players);
        roleta.run();
        animate(roleta.getDirection());
        announceWinner(roleta);
        DataBaseWrite(roleta);
    }

    private void DataBaseWrite(Roleta roleta){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Data");
        myRef.child("Jogo").setValue(roleta.getWinningPlayer());
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
        int id = item.getItemId();

        if (id == R.id.Roleta) {
            new Intent(this, RoletaActivity.class);
        }
        else if (id == R.id.Settings) {
            Settings fr = new Settings();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.frag,fr).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void animate(Integer direction) {
        this.pointer.animate().
                setDuration(3000).
                rotationBy(direction + this.lapCount * 360).
                setInterpolator(new AccelerateDecelerateInterpolator()).
                start();
    }

    private void reset() {
        this.pointer.setRotation(0);
    }

    private void announceWinner(Roleta roleta) {
        Toast.makeText(RoletaActivity.this, "O ponteiro aponta para: " + roleta.getDirection(), Toast.LENGTH_SHORT).show();
        Toast.makeText(RoletaActivity.this, "O vencedor e': " + roleta.getWinningPlayer(), Toast.LENGTH_SHORT).show();
    }
}