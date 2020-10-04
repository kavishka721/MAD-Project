package com.example.madproject.bottomNavbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.madproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class bottomanavbar extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FrameLayout myMainFrame;
    Fragment homeFrag,cartFrag,configfrag,profileFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottomanavbar);

        bottomNavigationView = findViewById(R.id.mainNav);
        myMainFrame = findViewById(R.id.mainFrame);
        homeFrag = new HomeFragment();
        cartFrag = new CartFragment();
        configfrag = new ConfigFragment();
        profileFrag = new ProfileFragment();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.navhome:
                        setFragment(homeFrag);
                        return true;

                    case R.id.navcart:
                        setFragment(cartFrag);
                        return true;

                    case R.id.navconfg:
                        setFragment(configfrag);
                        return true;

                    case R.id.navprofile:
                        setFragment(profileFrag);
                        return  true;

                    default:
                        break;
                }
                return false;
            }
        });

    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, fragment);
        fragmentTransaction.commit();
    }
}