package com.example.bcareapplication.ui.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.bcareapplication.R;
import com.example.bcareapplication.ui.fragment.ConfirmationFragment;
import com.example.bcareapplication.ui.fragment.ReviewsSpecialFragment;
import com.example.bcareapplication.ui.fragment.bottom_nav.HomeFragment;
import com.example.bcareapplication.ui.fragment.bottom_nav.MyEventFragment;
import com.example.bcareapplication.ui.fragment.bottom_nav.ProfileFragment;

import static com.example.bcareapplication.HelperMethod.replaceFragments;

public class BottomNavActivity extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        Fragment fragment = null;


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    break;

                case R.id.navigation_time:

                    fragment = new ConfirmationFragment();
                    //fragment = new MyEventFragment();
                    break;

                case R.id.navigation_account:

                    fragment = new ReviewsSpecialFragment();
                    //fragment = new ConfirmationFragment();
                    //fragment = new ProfileFragment();
                    break;
            }
            replaceFragments(fragment, getSupportFragmentManager(), R.id.FrameLayoutFragment_Container);

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.navigation_account);
    }

}
