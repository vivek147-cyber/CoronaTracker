package com.example.covidtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    ActionBar actionBar;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar =getSupportActionBar();

      bottomNavigationView= findViewById(R.id.nav);
          collapsingToolbarLayout=findViewById(R.id.collapse);
           toolbar=findViewById(R.id.toolbar);






        toolbarsetup();
        bottomsetup();


    }



    private void toolbarsetup() {

        getSupportActionBar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"menu button clicked",Toast.LENGTH_LONG).show();
            }
        });
    }


    private void bottomsetup()
    {
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case R.id.nav_home:


                        Home_Fragment fragment1= new Home_Fragment();
                        FragmentTransaction ft1= getSupportFragmentManager().beginTransaction();

                        ft1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        ft1.replace(R.id.content, fragment1, "");
                        ft1.commit();
                        return true;


                    case R.id.nav_world:


                        World_Fragment fragment2 = new World_Fragment();
                        FragmentTransaction ft2= getSupportFragmentManager().beginTransaction();
                        ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        ft2.replace(R.id.content, fragment2, "");
                        ft2.commit();
                        return true;


                    case R.id.nav_aware:


                        Aware_Fragment fragment3 = new Aware_Fragment();
                        FragmentTransaction ft3= getSupportFragmentManager().beginTransaction();
                        ft3.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        ft3.replace(R.id.content, fragment3, "");
                        ft3.commit();
                        return true;


                    case R.id.nav_dash:


                        Dash_Fragment fragment4 = new Dash_Fragment();
                        FragmentTransaction ft4= getSupportFragmentManager().beginTransaction();
                        ft4.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        ft4.replace(R.id.content, fragment4, "");
                        ft4.commit();
                        return true;


                }

                return false;
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

        Home_Fragment fragment1= new Home_Fragment();
        FragmentTransaction ft1= getSupportFragmentManager().beginTransaction();

        ft1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft1.replace(R.id.content, fragment1, "");
        ft1.commit();


    }
}
