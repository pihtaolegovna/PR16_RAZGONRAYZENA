package com.example.perfectmovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottomNav);
        setFragment(new WaitListFragment());
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.wait) {
                setFragment(new WaitListFragment());
            }
            if (id == R.id.rating) {
                setFragment(new RatingFragment());
            }
            return false;
        });
    }

    private void setFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.forward,  // enter
                        R.anim.back,  // exit
                        R.anim.forward,  // enter
                        R.anim.back  // exit
                )
                .replace(R.id.frameLayout, fragment)
                .addToBackStack(null)
                .commit();
    }
}